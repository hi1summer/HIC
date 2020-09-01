package tsinghua.hic.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author summer
 * @Email gonick@163.com
 * @Date 2020年8月31日
 * @Desc
 */
public class EncryptWithSHA256 {
    private static String algorithmString = "SHA-256";

    public String encrypt(String content) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithmString);
        md.update(content.getBytes());
        byte[] byteBuffer = md.digest();
        String encryptedContentString = byte2hex(byteBuffer);
        return encryptedContentString;
    }

    /**
     * @param b
     * @return
     * @Desc 二进制转字符串
     */
    private String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
//            if (n < b.length - 1) {
//                hs = hs + ":";
//            }
        }
        return hs.toUpperCase();
    }

}
