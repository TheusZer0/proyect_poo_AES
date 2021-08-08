// Java program to demonstrate the creation
// of Encryption and Decryption with Java AES

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class main {
    public static void encrypt_function(File file) throws IOException {
        String tmp_string_hex = Hex_Functions.file_to_hex(file); //se pasa el archivo a hex
        Encrypt_and_Decrypt.encrypt(tmp_string_hex,file); //se realiza el encrypt del archivo hex
    }

    public static void decrypt_function(String archive) throws IOException {
        File out_hex = new File(archive+".TeloEncodeo"); //archivo que esta encryptado en AES
        File out_img = new File(archive.replace(".TeloEncodeo","")); //archivo desencriptado de hex a file
        OutputStream os_two = new FileOutputStream(out_img); //archivo de salida que ya fue desencriptado de hex a archivo
        Encrypt_and_Decrypt.decrypt(out_hex);
        Hex_Functions.hexToBinary(out_hex,os_two); //no lo hace debido a que le estoy pasando el archivo que esta encodeado
        out_hex.delete();
    }

    public static void main(String[] args) {
        try {
            String archive = "/home/theuszero/IdeaProjects/testint_proyect_poo/files/ControlGlobal.pdf";
            File file = new File(archive); //hace referencia al archivo que sera encodeado
            //encrypt_function(file);//aqui termina el encrypt
            //decrypt_function(archive);//aqui comienza el decrypt
        } catch (IOException ex) {
            System.err.println("An IOException was caught!");
        }
    }
}