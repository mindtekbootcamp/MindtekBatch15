package utils;

import java.util.Random;
import java.util.UUID;

public class CommonMethods {

    public static String generatePhoneNumber(){
        Random random=new Random();
        long first3digits=random.nextLong(100,999);
        long second3Digits=random.nextLong(100,999);
        long last4Digits=random.nextLong(1000,9999);
        return "("+first3digits+")"+" "+second3Digits+"-"+last4Digits;
    }

    public static String generateRamdomEmail(){
        return  "user_" + UUID.randomUUID().toString().substring(0,8) + "@test.com";
    }
}
