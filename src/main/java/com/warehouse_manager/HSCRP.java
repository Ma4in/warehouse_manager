package com.warehouse_manager;

public class HSCRP {
    
    public static String setHesh(String password, String keyWord){

        char[] array = password.toCharArray();

        for (int j = 0; j < keyWord.length(); j ++){
            for (int i = 0; i < array.length / 2; i++) {
                char temp = (char) ((int)array[i]);
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }

            for (int i = 0; i < array.length; i++){
                array[i] = (char)(array[i]+1);
            }
        }
  
        return new String(array);
    }

    public static String getHesh(String password, String keyWord){

        char[] array = password.toCharArray();

        for (int j = 0; j < keyWord.length(); j ++){
            for (int i = 0; i < array.length / 2; i++) {
                char temp = (char) ((int)array[i]);
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }

            for (int i = 0; i < array.length; i++){
                array[i] = (char)(array[i]-1);
            }

        }
  
        return new String(array);
    }
}
