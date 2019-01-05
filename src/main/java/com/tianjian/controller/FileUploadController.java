package com.tianjian.controller;

import com.tianjian.data.bean.core.StaticFileResource;
import com.tianjian.data.bean.relation.RealtionFile;
import com.tianjian.data.service.core.StaticFileResourceDao;
import com.tianjian.data.service.relation.RealtionFileDao;
import com.tianjian.storage.StorageFileNotFoundException;
import com.tianjian.storage.StorageService;
import com.tianjian.util.MsgDigestDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    RealtionFileDao relationFileDao;

    @Autowired
    StaticFileResourceDao staticFileResourceDao;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile[] file,
            @RequestParam("relation_id") String relation_id,
            RedirectAttributes redirectAttributes
    ) throws NoSuchAlgorithmException {
        for(MultipartFile detail : file) {

            String filename = StringUtils.cleanPath(detail.getOriginalFilename());
            String digestName = MsgDigestDemo.getMsgDigestByMD5(relation_id + ":" + filename);
            StaticFileResource staticFileResource = new StaticFileResource();
            staticFileResource.setResourceCode(digestName);
            staticFileResource.setName(filename);
            if(staticFileResourceDao.findByResourceCode(digestName).size() == 0) {
                staticFileResourceDao.save(staticFileResource);
                storageService.store(detail,digestName);
            }

            if(relationFileDao.findByRealtionIdAndResourceCode(relation_id, digestName).size() == 0) {
                RealtionFile realtionFile = new RealtionFile();
                realtionFile.setRelationFileId(UUID.randomUUID().toString());
                realtionFile.setRealtionId(relation_id);
                realtionFile.setResourceCode(digestName);
                relationFileDao.save(realtionFile);
            }
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded !");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    @ResponseBody
    public Object getDataSource() {
        List<Object> data= new ArrayList<Object>();
        data.add(relationFileDao.findAll());
        data.add(staticFileResourceDao.findAll());
        return data;
    }

}
