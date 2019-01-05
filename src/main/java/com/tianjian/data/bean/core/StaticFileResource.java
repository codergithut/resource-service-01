package com.tianjian.data.bean.core;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATIC_FILE_RESOURCE")
public class StaticFileResource {

    /**
     * 静态资源文件
     */
    @Id
    /**
     * 资源id 实际文件名称 + 外部关联ID MD5数字签名得到
     */
    private String resourceCode;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件名称
     */
    private String name;

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
