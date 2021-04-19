package no.hvl.dat110.util;

/**
 * project 3
 *
 * @author tdoy
 */

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hash
{
    private static int mbit;
    private static BigInteger hashint;

    public static BigInteger hashOf(String entity)
    {

        // DONE: Hash a given string using MD5 and return the result as a BigInteger.
        // we use MD5 with 128 bits digest
        // compute the hash of the input 'entity'
        // convert the hash into hex format
        // convert the hex into BigInteger
        // return the BigInteger

        MessageDigest msg = null;
        try
        {
            msg = MessageDigest.getInstance("MD5");
            msg.update(entity.getBytes("utf8"));
            byte[] digest = msg.digest();

            mbit = digest.length*8;

            String myHash = DatatypeConverter.printHexBinary(digest);

            hashint = new BigInteger(myHash, 16);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }




        return hashint;
    }

    public static BigInteger addressSize()
    {

        // Task: compute the address size of MD5
        // get the digest length
        // compute the number of bits = digest length * 8
        // compute the address size = 2 ^ number of bits
        // return the address size

        return new BigInteger("2").pow(bitSize());
    }

    public static int bitSize()
    {
        // find the digest length
        return mbit;
    }

    public static String toHex(byte[] digest)
    {
        StringBuilder strbuilder = new StringBuilder();
        for (byte b : digest)
        {
            strbuilder.append(String.format("%02x", b & 0xff));
        }
        return strbuilder.toString();
    }

}
