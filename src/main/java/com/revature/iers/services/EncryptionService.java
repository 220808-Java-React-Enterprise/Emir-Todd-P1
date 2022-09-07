package com.revature.iers.services;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncryptionService {

    private static MessageDigest md;

    public static String encryption(String pw){

        try{
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pw.getBytes();
            md.reset();
            byte[] byteDigest = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();

            for(int i=0; i < byteDigest.length; i++){
                sb.append(Integer.toHexString(0xff & byteDigest[i]));
            }
            return sb.toString();

        }catch(NoSuchAlgorithmException e){
            Logger.getLogger(EncryptionService.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;

    }
}
