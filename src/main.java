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

    public static void main(String[] args) throws IOException {
        File file = new File("/home/theuszero/IdeaProjects/testint_proyect_poo/files/pngegg.png");

        String tmp_string_hex = Hex_Functions.file_to_hex(file); //file in hex
        Encrypt_and_Decrypt.encrypt(tmp_string_hex);

        File out_hex = new File("/home/theuszero/IdeaProjects/testint_proyect_poo/Imagen_result.encode"); //archivo desencriptado de hex a file
        InputStream is = new FileInputStream(out_hex);  //Archivo de entrada, archivo que sera pasado a hex

        File out_img = new File("/home/theuszero/IdeaProjects/testint_proyect_poo/imagen.png"); //archivo desencriptado de hex a file
        OutputStream os_two = new FileOutputStream(out_img); //archivo de salida que ya fue desencriptado de hex a archivo

        File file_tmp = new File("/home/theuszero/IdeaProjects/testint_proyect_poo/Imagen_result.encode");
        String result = Encrypt_and_Decrypt.decrypt(file_tmp);

        System.out.println(result);
        Hex_Functions.hexToBinary(is,os_two); //no lo hace debido a que le estoy pasando el archivo que esta encodeado

    }
}