import java.io.*;

public class Hex_Functions {
    public static String file_to_hex(File is) throws IOException {
        StringBuilder builder = new StringBuilder();

        try {
            FileInputStream fin = new FileInputStream(String.valueOf(is));
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while((bytesRead = fin.read(buffer)) > -1)
                for(int i = 0; i < bytesRead; i++){
                    String tmp = String.format("%02x", buffer[i] & 0xFF);
                    tmp = tmp.replaceAll("\n", "");
                    builder.append(tmp);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String final_tmp = builder.toString();
        return final_tmp;
    }

    public static void hexToBinary(File is, OutputStream os) throws FileNotFoundException {
        FileReader fr =new FileReader(is);
        BufferedReader br =new BufferedReader(fr);

        try {
            char buffer[] = new char[2];

            while (br.read(buffer) != -1) {
                os.write((Character.digit(buffer[0], 16) << 4) + Character.digit(buffer[1], 16));
            }

        } catch (IOException e) {
            System.err.println("An error occurred");
        }
    }
}
