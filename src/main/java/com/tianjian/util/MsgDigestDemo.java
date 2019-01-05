package com.tianjian.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MsgDigestDemo{
    public static void main(String args[]) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(UUID.randomUUID().toString());
        System.out.println("MD5摘要: " + getMsgDigestByMD5(UUID.randomUUID().toString()));
        System.out.println("SHA摘要: " + getMsgDigestBySHA("Hello World"));
    }


    public static String getMsgDigestByMD5(String message) throws NoSuchAlgorithmException {
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        // 更新要计算的内容
        md5Digest.update(message.getBytes());
        // 完成哈希计算，得到摘要
        byte[] md5Encoded = md5Digest.digest();
        return Base64.encodeBase64URLSafeString(md5Encoded);
    }

    public static String getMsgDigestBySHA(String message) throws NoSuchAlgorithmException {
        MessageDigest shaDigest = MessageDigest.getInstance("SHA");
        // 更新要计算的内容
        shaDigest.update(message.getBytes());
        // 完成哈希计算，得到摘要
        byte[] shaEncoded = shaDigest.digest();
        return Base64.encodeBase64URLSafeString(shaEncoded);
    }
}