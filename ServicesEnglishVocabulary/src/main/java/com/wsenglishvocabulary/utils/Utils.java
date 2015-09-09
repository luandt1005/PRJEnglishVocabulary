/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wsenglishvocabulary.utils;

import java.security.MessageDigest;

/**
 *
 * @author LuanDT
 */
public class Utils {
    //generate password to md5
    public static String generateMD5(String pass){
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte byteData[] = md.digest();

            for (byte b : byteData) {
                sb.append(String.format("%02x", b & 0xff));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + sb;
    }
}
