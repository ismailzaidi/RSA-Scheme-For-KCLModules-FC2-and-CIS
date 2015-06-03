package com.rsa.encryptionscheme;

import java.math.BigInteger;

public class HTMLGenerator {

	private RSA rsa;

	public HTMLGenerator(RSA rsa) {
		super();
		this.rsa = rsa;
	}

	public String Generate() {

		BigInteger n = rsa.getN();
		BigInteger m = rsa.getM();
		BigInteger e = rsa.getE();
		BigInteger d = rsa.getD();
		BigInteger message = rsa.getMessage();
		int encrypt = rsa.Encrypt();
		int decryption = rsa.Decrypt(new BigInteger(String.valueOf(encrypt)));
		String gcd = rsa.returnGCDSteps();
		String extendedEuclids = rsa.returnEuclidsSteps();

		String strEncrypt = "<p>Encrypt(" + e + "," + n + ") = " + message
				+ "<sup>" + e + "</sup> mod " + n + " = " + encrypt + " </p>\n";
		String strDecrypt = "<p>Decrypt(" + d + "," + encrypt + ") = "
				+ encrypt + "<sup>" + d + "</sup> mod " + n + " = "
				+ decryption + " </p>\n";
		String mainbody = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n"
				+ "\n"
				+ " \"http://www.w3.org/TR/html4/loose.dtd\">\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "<html lang=\"en\">\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "<head>\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n"
				+ "<link href=\'"
				+ getClass().getResource("application.css")
				+ "'  rel=\"stylesheet\">"
				+ "\t<title>Title Goes Here</title>\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "</head>\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "<body>\n"
				+ "\n"
				+ "<h4>Result</h4>\n"
				+ "<p>n= pxq = "
				+ n
				+ "</p>\n"
				+ "<p>\u03c6(n) = (p-1)x (q-1) = "
				+ m
				+ " </p>\n"
				+ "<p>e (Co prime)= " + e + "</p>\n";

		String FC2Version = gcd + "\n" + extendedEuclids;
		String CISVersion = rsa.returnCISVersion();

		String EndBody = "<h4>Finding D</h4>" + "<p> d = y mod \u03c6(n) = "
				+ d + "</p>\n" +

				"<h4> Encryption And Decryption stage </h4>" + strEncrypt
				+ strDecrypt + "\n" + "</body>\n" + "\n" + "\n" + "\n"
				+ "</html>\n" + "";
		String html = mainbody + FC2Version + CISVersion + EndBody;
		return html;
	}

}
