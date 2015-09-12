/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.englishvocabulary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author LuanDT
 */
public class ValidatorUtils {
    public static boolean isUsername(String value) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{1,40}$");        
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
