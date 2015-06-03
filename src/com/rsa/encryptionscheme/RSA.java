package com.rsa.encryptionscheme;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
/**
 * This RSA scheme that shows the steps using both FC2 and CIS versions 
 * @author IsmailZD
 *
 */
public class RSA {
	private String LOG_CIS_VERSION = "CIS Method: ";
	private BigInteger p, q, n, m, message, d, e;
	private ArrayList<String> gcdlist, extendedEuclidslist, gcdListCIS,
			extendedEuclidsListCIS;

	public RSA(BigInteger p, BigInteger q, BigInteger message, BigInteger e) {
		super();
		this.p = p;
		this.q = q;
		this.message = message;
		this.e = e;
		gcdlist = new ArrayList<String>();
		extendedEuclidslist = new ArrayList<String>();
		gcdListCIS = new ArrayList<String>();
		extendedEuclidsListCIS = new ArrayList<String>();
		gcd(getM().intValue(), getE().intValue());
		gcdCISVersion(getM().intValue(), getE().intValue());
	}

	public RSA() {
	}

	public int[] gcd(int p, int q) {
		if (q == 0)
			return new int[] { p, 1, 0 };

		gcdlist.add("gcd(" + p + "," + q + ") = " + q + "*" + p / q + "+" + p
				% q);
		int[] vals = gcd(q, p % q);
		int d = vals[0];
		int a = vals[2];
		int b = vals[1] - (p / q) * vals[2];
		System.out.println("Value: " + b +  " Secondary Value: " + vals[1]);
		extendedEuclidslist.add(d + "= " + p + "(" + a + ") +  " + q + "(" + b
				+ ")");

		return new int[] { d, a, b };
	}

	public ArrayList<String> getGcd() {
		return gcdlist;
	}

	public int[] gcdCISVersion(int p, int q) {
		if (q == 0) {
			 gcdListCIS.add(p+","+0+","+","+"-");
			 extendedEuclidsListCIS.add(1 + "," + "0");
			return new int[] { p, 1, 0 };
		}
		gcdListCIS.add("" + p + "," + q + ", " + p / q + "," + p % q);
		System.out.println(LOG_CIS_VERSION + "44" + gcdListCIS.toString());
		int[] vals = gcdCISVersion(q, p % q);
		int d = vals[0];
		int a = vals[2];
		int b = vals[1] - (p / q) * vals[2];
		extendedEuclidsListCIS.add(a + "," + b);
		System.out.println(LOG_CIS_VERSION + a);

		return new int[] { d, a, b };
	}

	private int GCD(int a, int b) {
		BigInteger ba = new BigInteger(String.valueOf(a));
		BigInteger bb = new BigInteger(String.valueOf(b));
		BigInteger gcd = ba.gcd(bb);

		return gcd.intValue();
	}

	public BigInteger getE() {
		if (e.intValue() == 0) {
			for (int i = 1; i < n.intValue(); i += 2) {
				if ((m.doubleValue() / i) % 2 != 0) {
					e = new BigInteger(String.valueOf(i));

					int gcd = GCD(m.intValue(), e.intValue());
					if (gcd == 1) {
						break;
					} else {
						continue;

					}
				}
			}
		}
		return e;
	}

	public String returnEuclidsSteps() {
		String gcd = "<h4> Extended Euclids Algorithm D = gcd(A, B) = A × x + B × y :</h4>";
		for (String line : extendedEuclidslist) {
			gcd += "<p>" + line + "</p>";
		}
		return gcd;
	}

	public String returnGCDSteps() {
		String gcd = "<h3> FC2 Version </h3>" + "<h4> gcd(a,b) Steps: </h4> ";
		for (String line : gcdlist) {
			gcd += "<p>" + line + "</p>";
		}
		return gcd;
	}

	public String returnCISVersion() {
		String str = "<h3> CIS Version:</h3><h4>GCD and Extended Euclids Table</h4>";
		str += "<ol>\n"+
				"<li>if b = 0</li>\n"+
				"<li>then return (a, 1, 0)</li>\n"+
				"<li>(d\',x\', y\') \u2190 Extended-Euclid(b, a mod b)</li>\n"+
				"<li>(d, x, y) \u2190 (d\',x\', y\'\u2212 ([a/b] \u00d7 y\'))</li>\n"+
				"<li>return (d, x, y)</li>\n"+
				"</ol>";
		str += "<table border=\"1\" style=\"background-color:#FFFFFF;border-collapse:collapse;border:1px solid #000000;color:#000000;width:100%;text-align:center\" cellpadding=\"3\" cellspacing=\"10\">\n"
				+ "\t<tr>\n"
				+ "\t\t<td>a</td>\n"
				+ "\t\t<td>b</td>\n"
				+ "\t\t<td>d</td>\n"
				+ "\t\t<td>[a/b]</td>\n"
				+ "\t\t<td>x</td>\n" + "\t\t<td>y</td>\n" + "\t</tr>\n" + "";

		ArrayList<String> gcdList = getGcdListCIS();
		System.out.println("returnCISVersionMethod: " + gcdList.toString());
		ArrayList<String> extendedList = getExtendedEuclidsListCIS();

		Collections.reverse(extendedList);
		String d = String.valueOf(GCD(this.getM().intValue(), this.getE()
				.intValue()));
		System.out.println(gcdList.size());
		for (int i = 0; i < gcdList.size(); i++) {
			String a = gcdList.get(i).split(",")[0];
			String b = gcdList.get(i).split(",")[1];
			String adivb = gcdList.get(i).split(",")[3];
			String x = extendedList.get(i).split(",")[0];
			String y = extendedList.get(i).split(",")[1];
			str += "\t<tr>\n" + "\t\t<td>" + a + "</td>\n" + "\t\t<td>" + b
					+ "</td>\n" + "\t\t<td>" + d + "</td>\n" + "\t\t<td>"
					+ adivb + "</td>\n" + "\t\t<td>" + x + "</td>\n"
					+ "\t\t<td>" + y + "</td>\n" + "\t</tr>\n";
			// str += "<p>"+a+"\t"+b+"\t"+d+"\t" + adivb + "\t"+x+"\t"+y +
			// "</p>";

		}
		str += "</table>\n";
		System.out.println("ReturnCISVersion: " + str);
		return str;
	}

	public ArrayList<String> getGcdListCIS() {
		return gcdListCIS;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public void setM(BigInteger m) {
		this.m = m;
	}

	public BigInteger getMessage() {
		return message;
	}

	public void setMessage(BigInteger message) {
		this.message = message;
	}

	public void setD(BigInteger d) {
		this.d = d;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}

	public int Encrypt() {
		return this.message.modPow(e, n).intValue();
	}

	public int Decrypt(BigInteger ciphertext) {
		return ciphertext.modPow(d, n).intValue();
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getN() {
		n = p.multiply(q);
		return n;
	}

	public BigInteger getM() {
		m = p.subtract(BigInteger.valueOf(1)).multiply(
				q.subtract(BigInteger.valueOf(1)));
		return m;
	}

	public BigInteger getD() {
		d = e.modInverse(m);
		return d;
	}

	public void setGcd(ArrayList<String> gcd) {
		this.gcdlist = gcd;
	}

	public ArrayList<String> getExtendedEuclids() {
		return extendedEuclidslist;
	}

	public ArrayList<String> getExtendedEuclidsListCIS() {
		System.out
				.println(LOG_CIS_VERSION + this.extendedEuclidsListCIS.size());
		return extendedEuclidsListCIS;
	}

	public void setExtendedEuclids(ArrayList<String> extendedEuclids) {
		this.extendedEuclidslist = extendedEuclids;
	}
}
