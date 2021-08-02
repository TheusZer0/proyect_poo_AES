import java.io.File;

public class Main
{
	public static void main(String[] args)
	{
		Encryptor en = Encryptor.getEncrypter(true);
		Decryptor de = Decryptor.getDecrypter(true);
		
		File src = new File("/home/theuszero/IdeaProjects/testint_proyect_poo/files");
		
		//en.encrypt(src, src);
		de.decrypt(src, src);
	}
}
