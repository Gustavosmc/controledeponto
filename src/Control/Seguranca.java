package Control;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguranca {
	
	public static String gerarMD5 (String senha) throws NoSuchAlgorithmException {  
	    MessageDigest md = MessageDigest.getInstance("MD5");  
	    BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
	    String crypto = hash.toString(16);  
	    if (crypto.length() %2 != 0)  
	        crypto = "0" + crypto;  
	    return crypto;  
	}
}
