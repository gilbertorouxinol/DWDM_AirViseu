package inout;

import java.io.*;
import java.util.ArrayList;

/**
 *
 *  IntelliJ IDEA 2021.2.2 (Ultimate Edition)<br>
 *  Licensed to Gilberto Rouxinol<br>
 *  For educational use only.<br>
 *
 *  Polytechnic Institute of Viseu<br>
 *  School of Technology and Management of Viseu<br>
 *
 *  Class created by Gilberto Rouxinol on 2022<br>
 *  Copyright © 2022 Gilberto Rouxinol<br>
 *  All rights reserved<br><br>
 *
 *  The FileText class implements a BufferedReader and BufferedWriter object
 *  to read and write from or for a text file.<br>
 *
 *  The FileText class has the following methods:<br>
 *         (1) turnOnReader()  - open file in read mode;<br>
 *         (2) textReader()    - read a line text in an open file in read mode;<br>
 *         (3) turnOffReader() - close file in read mode;<br>
 *
 *         (4) turnOnWriter()  - open file in read mode;<br>
 *         (5) textWriter()    - write a line text in an open file in write mode;<br>
 *         (6) turnOffWriter() - close file in write mode.
 *
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 *
 */
public class FileText implements Serializable {
    private File f;

    private FileReader fR;
    private FileWriter fW;

    private BufferedReader bR;
    private BufferedWriter bW;


    /**
     * Empty construct FileText
     */
    public FileText(){
    }


// READ MODE -----------------------------------------------------------------------------------------------------------
    /**
     * Open text file in read mode
     * @param  fileName  the name of the text file to read an object
     * @return true      the file exists
     *      or false     the file doesn't exist
     * @exception IOException If an input output exception occurred.
     */
    public boolean turnOnReader(String fileName) {
        try {
            f = new File(fileName);
            fR = new FileReader(f);
            bR = new BufferedReader(fR);
            return true;
        } catch (IOException error) {
            System.out.println("Can't turn on reader - " + error.getMessage());
            return false;
        }
    }

    /**
     * Read all line in an open text file in read mode.
     * @return  lineList  a ArrayList(String) with all
     *                    line read. Each line is one String.
     * @exception IOException If an input output exception occurred.
     */
    public ArrayList<String> textReader() {
        ArrayList<String> linesList = new ArrayList<>();
        String line = "";

        // Verify always if the previous line read is empty or not:
        // an empty file is checked;
        // the cursor position on the last line, full or empty, is checked.
        try {
            line = bR.readLine();
            if(line != null)
                linesList.add(line);
            else
                return linesList;
        } catch (IOException error) {
            System.out.println("Can't text reader - " + error.getMessage());
            return linesList;
        }

        try {
            while(line != null) {
                line = bR.readLine();
                if(line != null)
                    linesList.add(line);
                else
                    return linesList;
            }
        } catch (IOException error) {
            System.out.println("Can't text reader - " + error.getMessage());
            return linesList;
        }

        return linesList;
    }


    /**
     * Close text file in read mode
     * @return true   text file closed with success
     *      or false  text file not closed
     * @exception IOException If an input output exception occurred.
     */
    public boolean turnOffReader() {
        try {
            bR.close();
            fR.close();
            return true;
        } catch (IOException e) {
            System.out.println("Can't turn off reader - " + e.getMessage());
            return false;
        }
    }




// WRITE MODE ----------------------------------------------------------------------------------------------------------
    /**
     * Open text file in write mode
     * @param  fileName  the name of the text file to write
     * @return true      the text file was open successfuly
     *      or false     the text file wasn't open successfuly
     * @exception IOException If an input output exception occurred.
     */
    public boolean turnOnWriter(String fileName) {
        try {
            f = new File(fileName);
            fW = new FileWriter(f);
            bW = new BufferedWriter(fW);
            return true;
        } catch (IOException error) {
            System.out.println("Can't turn on writer - " + error.getMessage());
            return false;
        }
    }


    /**
     * Write linesList of type ArrayList(String) in an open text file
     * in write mode
     * @param   linesList   the name of the ArrayList(String) with
     *                      all the lines to write
     * @exception IOException If an input output exception occurred.
     */
    public void textWriter(ArrayList<String> linesList) {
        linesList.forEach((n) -> {
            try {
                bW.write(n, 0, n.length());
                bW.newLine();
            } catch (IOException error) {
                System.out.println("Can't text writer - " + error.getMessage());
            }
        });
    }


    /**
     * Close text file in write mode
     * @return true   text file closed with success
     *      or false  text file not closed
     * @exception IOException If an input output exception occurred.
     */
    public boolean turnOffWriter() {
        try {
            bW.close();
            fW.close();
            return true;
        } catch (IOException e) {
            System.out.println("Can't turn off writer - " + e.getMessage());
            return false;
        }
    }
}
