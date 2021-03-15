package no.hvl.dat110.util;

/**
 * project 3
 *
 * @author tdoy
 */

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash {

    private static BigInteger hashint;

    public static BigInteger hashOf(String entity) {

        // DONE: Hash a given string using MD5 and return the result as a BigInteger.
        // we use MD5 with 128 bits digest
        // compute the hash of the input 'entity'
        // convert the hash into hex format
        // convert the hex into BigInteger
        // return the BigInteger

        MessageDigest msg = null;
        try {
            msg = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        msg.update(entity.getBytes());
        byte[] digest = msg.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        hashint = new BigInteger(myHash, 16);

        return hashint;
    }

    public static BigInteger addressSize() {

        // Task: compute the address size of MD5
        // get the digest length
        // compute the number of bits = digest length * 8
        // compute the address size = 2 ^ number of bits
        // return the address size
       BigInteger out = null;

        int length = hashint.bitLength();


        length *=8;

        length = (int) java.lang.Math.pow(2,length);
        out = BigInteger.valueOf(length);


        return out;

    }

    public static int bitSize() {



        // find the digest length
        int digestlen =hashint.bitLength();

        return digestlen * 8;
    }

    public static String toHex(byte[] digest) {
        StringBuilder strbuilder = new StringBuilder();
        for (byte b : digest) {
            strbuilder.append(String.format("%02x", b & 0xff));
        }
        return strbuilder.toString();
    }

}
