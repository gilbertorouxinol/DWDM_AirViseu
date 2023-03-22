package inout;

import java.io.Serializable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.regex.Pattern;

/**
 *
 *  IntelliJ IDEA 2021.2.2 (Ultimate Edition)<br>
 *  Licensed to Gilberto Rouxinol<br>
 *  For educational use only.<br><br>
 *
 *  Polytechnic Institute of Viseu<br>
 *  School of Technology and Management of Viseu<br><br>
 *
 *  Class created by Gilberto Rouxinol on 2022<br>
 *  Copyright © 2022 Gilberto Rouxinol<br>
 *  All rights reserved<br><br>
 *
 *  The keyboard class implements a BufferedReader stream and connect it to an InputStreamReader stream
 *  to read line by line from the keyboard instead of using the class Scanner with System.in.<br><br>
 *
 *  The keyboard class has the following readers or methods:<br>
 *         (1) readString()  - read a string;<br>
 *         (2) readByte()    - read a byte number;<br>
 *         (3) readShort()   - read a short number;<br>
 *         (4) readInteger() - read an integer number;<br>
 *         (5) readLong()    - read a long number;<br>
 *         (6) readFloat()   - read a float number;<br>
 *         (7) readDouble()  - read a double number;<br>
 *         (8) readChar()    - read a char number or char character;<br>
 *         (9) readBoolean() - read a boolean value - "true" or "false".
 *
 * @author Gilberto Rouxinol
 * @version 2022.11.15
 *
 */
public class Keyboard implements Serializable {

    /**
     * Read a STRING.
     * @exception IOException If an input exception occurred.
     *                        The string cannot be delivered and instead an empty string is delivered.
     * @return newString, the string.
     */
    public static String readString() {

        String newString = null;

        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader entry = new BufferedReader(reader);
            newString = entry.readLine();
        } catch (IOException error) {
            System.out.println(error.getMessage());
            newString = "";
        }

        return newString;
    }

    /**
     * Read a BYTE
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into a byte number.
     * @return newByte, the byte number.
     */
    public static byte readByte() {

        byte newByte = 0;
        boolean flag = false;

        while (!flag) {
            try {
                String newString = readString();
                newByte = Byte.parseByte(newString);
                flag = true;
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                System.out.println("Input error - not a Byte. Try again.");
            }
        }

        return newByte;
    }

    /**
     * Read a SHORT
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into a short number.
     * @return newShort, the short number.
     */
    public static short readShort() {

        short newShort = 0;
        boolean flag = false;

        while(!flag) {
            try {
                String newString = readString();
                newShort = Short.parseShort(newString);
                flag = true;
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                System.out.println("Input error - not a Short. Try again.");
            }
        }

        return newShort;
    }

    /**
     * Read an INTEGER
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into an integer number.
     * @return newInteger, the integer number.
     */
    public static int readInteger() {

        int newInteger = 0;
        boolean flag = false;

        while (!flag) {
            try {
                String newString = readString();
                newInteger = Integer.parseInt(newString);
                flag = true;
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                System.out.println("Input error - not a Integer. Try again.");
            }
        }

        return newInteger;
    }

    /**
     * Read a LONG
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into a long number.
     * @return newLong, the long number.
     */
    public static long readLong() {

        long newLong = 0;
        boolean flag = false;

        while (!flag) {
            try {
                String newString = readString();
                newLong = Long.parseLong(newString);
                flag = true;
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                System.out.println("Input error - not a Long. Try again.");
            }
        }

        return newLong;
    }

    /**
     * Read a FLOAT.
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into a float number.
     * @return newFloat, the float number.
     */
    public static float readFloat() {

        float newFloat = 0;
        boolean flag = false;

        while (!flag) {
            try {
                String newString = readString();
                newFloat = Float.parseFloat(newString);
                flag = true;
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                System.out.println("Input error - not a Float. Try again.");
            }
        }

        return newFloat;
    }

    /**
     * Read a DOUBLE
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into a double number.
     * @return newDouble, the double number.
     */
    public static double readDouble() {

        double newDouble = 0.0;
        boolean flag = false;

        while (!flag) {
            try {
                String newString = readString();
                newDouble = Double.parseDouble(newString);
                flag = true;
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                System.out.println("Input error - not a Double. Try again.");
            }
        }

        return newDouble;
    }

    /**
     * Read a CHAR
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into a char value.
     *                                  The string follows also a regex rule, for char given by value.
     *                                  If the regex condition fails the keyboard first char of the string read is the char.
     * @return newChar, the char value.
     */
    public static char readChar() {

        char newChar = ' ';
        boolean jockey = false;
        boolean flag = false;

        while (!flag) {
            try {
                String newString = readString();
                if (Pattern.matches("([1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])", newString))
                    jockey = true;
                if (jockey)
                    newChar = (char) Short.parseShort(newString);
                else
                    newChar = newString.charAt(0);
                flag = true;
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                System.out.println("Input error - not a Char. Try again.");
            }
        }

        return newChar;
    }

    /**
     * Read a BOOLEAN
     * @exception NumberFormatException If an input exception occurred.
     *                                  The string read cannot be converted into a boolean value.
     *                                  The string follows also a regex rule.
     *                                  If the regex condition fails the keyboard string read is not returned.
     * @return newBoolean, the boolean value.
     */
    public static boolean readBoolean() {

        boolean newBoolean = false;
        boolean flag = false;
        boolean checkNewString;

        while (!flag) {

            String newString = readString();
            checkNewString = Pattern.matches("(true|false)", newString);

            if (checkNewString) {
                try {
                    newBoolean = Boolean.parseBoolean(newString);
                    flag = true;
                } catch (NumberFormatException error) {
                    System.out.println(error.getMessage());
                    System.out.println("Input error - not a Boolean. Try again (true or false).");
                }
            } else {
                System.out.println("For input string: \"" + newString + "\"");
                System.out.println("Input error - not a Boolean. Try again (true or false).");
            }

        }

        return newBoolean;
    }
}