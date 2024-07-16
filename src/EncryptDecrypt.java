import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptDecrypt {
	
	KeyGenerator keyGen = KeyGenerator.getInstance("AES");
	SecretKey key = keyGen.generateKey();
	Cipher cipher;
	
	public EncryptDecrypt() throws Exception {
		
	}
	
	public String stringEncryption(String text) throws Exception {
		cipher = Cipher.getInstance("AES");
		byte[] byteText = text.getBytes();
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encrypted = cipher.doFinal(byteText);
		String encryptedText = new String(encrypted);
		System.out.println(encryptedText);
		return encryptedText;
	
	}
	
	public String stringDecryption(String encryptedText) throws Exception {
		cipher = Cipher.getInstance("AES");
		byte[] byteText = encryptedText.getBytes();
		
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrypted = cipher.doFinal(byteText);
		String text = new String(decrypted);
		System.out.println(text);
		return text;
	}
	
}
