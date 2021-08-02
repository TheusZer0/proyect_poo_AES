package Arreglar;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class main {

    public static String AES_HEX_encrypt(File src){
        File[] files = src.listFiles();
        try {
            for (File f : files) {

                String tmp = (String.valueOf(f));
                String tmp_2 = "Teloencripto";

                boolean found = tmp.contains(tmp_2);
                if (found){
                    System.out.println("Ya existe el archivo encriptado, no es necesario volver a encriptarlo, este es: "+f);
                }else{

                    File out_img = new File(f + ".Teloencripto"); //archivo desencriptado de hex a file
                    InputStream is = new FileInputStream(f);  //Archivo de entrada, archivo que sera pasado a hex

                    OutputStream os_two = new FileOutputStream(out_img); //archivo de salida que ya fue desencriptado de hex a archivo

                    StringBuilder testing = binaryToHex(is);

                    String encrypt = AES.encrypt(String.valueOf(testing), os_two);
                    return encrypt;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
                System.out.println("FINAL ENCRYPT");
            }
        return null;
    }

    public static void AES_HEX_decrypt(File src,String a){
        File[] files = src.listFiles(); //vector de archivos

        try {
            for (File f : files) { //recorre los archivos

                String tmp = (String.valueOf(f));
                String tmp_2 = "Teloencripto";

                boolean found = tmp.contains(tmp_2);

                //si el archivo tiene la extension .TeloEncripto entonces se debe desencriptar
                if (found){

                    //String tmp_tmp = AES.decrypt(a);

                    String cadena;
                    String tmp_tmp = "";
                    System.out.println("Existe el archivo encriptado y es: "+f);
                    FileReader file = new FileReader(f);
                    BufferedReader b = new BufferedReader(file);
                    while((cadena = b.readLine())!=null) {
                        tmp_tmp += cadena.replaceAll("\n", "");
                    }


                    if(tmp_tmp.equals(a)) {
                        System.out.println("SON IGUALES LAS WEAS");
                    }

                    //String this_is = AES.decrypt(tmp_tmp);
                    //System.out.println(this_is);

                }else{
                    System.out.println("El archivo "+f+" No esta encriptado");
                }
            }
        }
        //catch (FileNotFoundException e) {
        //    e.printStackTrace();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            System.out.println("FINAL DECRYPT");
        }
    }

    public static StringBuilder binaryToHex(InputStream is) {
        StringBuilder outing = new StringBuilder();

        try {
            int value;
            while ((value = is.read()) != -1) {
                outing.append(String.format("%02X", value));
            }
        } catch (IOException e) {
            System.err.println("An error occurred");
        }
        return outing;
    }

    public static void hexToBinary(InputStream is, OutputStream os) {
        Reader reader = new BufferedReader(new InputStreamReader(is));

        try {
            char buffer[] = new char[2];

            while (reader.read(buffer) != -1) {
                os.write((Character.digit(buffer[0], 16) << 4)
                        + Character.digit(buffer[1], 16));
            }
        } catch (IOException e) {
            System.err.println("An error occurred");
        }
    }

    public static void main(String[] args) throws IOException {

        File src = new File("/home/theuszero/IdeaProjects/testint_proyect_poo/files"); // directorio de archivos

        String final_tmp = AES_HEX_encrypt(src);
        AES_HEX_decrypt(src,final_tmp);

        //se debe crear el archivo encryptedString

        //Aqui Comienza el Decrypt

        /*
         * El decrypt debe recibir el archivo que fue encriptado, leerlo de manera normal y realizar el decrypt asocidado
         * */

        //String decryptedString = AES.decrypt(encryptedString); //se debe pasar por parametro el archivo que fue encriptado

//                System.out.println(decryptedString);

        //hexToBinary(is_two,os_two);


        // Create String variables
        //String originalString = "GeeksforGeeks";

        //// Call encryption method
        //String encryptedString = AES.encrypt(originalString);

        //// Call decryption method
        //String decryptedString = AES.decrypt(encryptedString);
        //}

    }
}