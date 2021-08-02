import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class main {

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

        File out_img = new File("outing"); //archivo desencriptado de hex a file

        File[] files = src.listFiles();

        try {
            for (File f : files) {

                System.out.println("este archivo es: " + f);
                InputStream is = new FileInputStream(f);  //Archivo de entrada, archivo que sera pasado a hex

                OutputStream os_two = new FileOutputStream(out_img); //archivo de salida que ya fue desencriptado de hex a archivo

                StringBuilder testing = binaryToHex(is);

                String encryptedString = AES.encrypt(String.valueOf(testing),os_two); //se debe crear el archivo encryptedString

                //Aqui Comienza el Decrypt
                String decryptedString = AES.decrypt(encryptedString); //se debe pasar por parametro el archivo que fue encriptado

//                System.out.println(decryptedString);

                //hexToBinary(is_two,os_two);

            }
            // Create String variables
            //String originalString = "GeeksforGeeks";

            //// Call encryption method
            //String encryptedString = AES.encrypt(originalString);

            //// Call decryption method
            //String decryptedString = AES.decrypt(encryptedString);

        } finally {
            System.out.println("FINAL");
        }
    }
}