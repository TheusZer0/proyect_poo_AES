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

public class Encrypt_and_Decrypt {
    private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";

    private static final String SALT = "ssshhhhhhhhhhh!!!!";
    public static void encrypt(String strToEncrypt,File archivo)
    {
        try {
            // Create default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec
                    = new IvParameterSpec(iv);
            // Create SecretKeyFactory object
            SecretKeyFactory factory
                    = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                    SECRET_KEY.toCharArray(), SALT.getBytes(),
                    65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                    tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance(
                    "AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,
                    ivspec);
            // Return encrypted string

            //escritura del archivo hex
            //File archivo=new File("Imagen_result.encode");
            FileWriter fw=new FileWriter(archivo+".TeloEncodeo");
            BufferedWriter bw=new BufferedWriter(fw);

            String encode =  Base64.getEncoder().encodeToString(
                    cipher.doFinal(strToEncrypt.getBytes(
                            StandardCharsets.UTF_8)));
            bw.write(""+encode);
            bw.flush();
            archivo.delete();
        }
        catch (Exception e) {
            System.out.println("Error while encrypting: "
                    + e.toString());
        }
    }

    public static String decrypt(File file_tmp)
    {
        try {
            // Default byte array
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0 };
            // Create IvParameterSpec object and assign with
            // constructor
            IvParameterSpec ivspec
                    = new IvParameterSpec(iv);
            // Create SecretKeyFactory Object
            SecretKeyFactory factory
                    = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(
                    SECRET_KEY.toCharArray(), SALT.getBytes(),
                    65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(
                    tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance(
                    "AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey,
                    ivspec);
            // Return decrypted string

            String cadena;
            String tmp_tmp = "";
            FileReader file = new FileReader(file_tmp);
            BufferedReader b = new BufferedReader(file);
            while((cadena = b.readLine())!=null) {
                tmp_tmp += cadena.replaceAll("\n", "");
            }

            //File archivo=new File("Imagen_result.encode");
            FileWriter fw=new FileWriter(file_tmp);
            BufferedWriter bw=new BufferedWriter(fw);

            String decode = new String(cipher.doFinal(Base64.getDecoder().decode(tmp_tmp)));

            System.out.println("El archivo fue decodeado correctamente, este tiene el valor de: "+decode);

            bw.write(""+decode);
            bw.flush();

            return decode;

        }
        catch (Exception e) {
            System.out.println("Error while decrypting: "
                    + e.toString());
        }
        return null;
    }

}
