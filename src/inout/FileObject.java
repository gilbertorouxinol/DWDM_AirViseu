package inout;

import java.io.*;
import java.lang.ClassNotFoundException;

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
 *  The FileObject class implements a stream file and connect it
 *  to an object stream to read or write objects from or for file.<br>
 *
 *  The FileObject class has the following methods:<br>
 *         (1) turnOnReader()  - open file in read mode;<br>
 *         (2) objectReader()  - read an object in an open file in read mode;<br>
 *         (3) turnOffReader() - close file in read mode;<br>
 *
 *         (4) turnOnWriter()  - open file in read mode;<br>
 *         (5) objectWriter()  - write an object in an open file in write mode;<br>
 *         (6) turnOffWriter() - close file in write mode.
 *
 *
 * @author Gilberto Rouxinol
 * @version 22.04.a
 *
 */
public class FileObject implements Serializable{


    private FileOutputStream fileOutput;
    private FileInputStream fileInput;


    private ObjectOutputStream out;
    private ObjectInputStream in;


    /**
     * Empty construct FileObject
     */
    public FileObject(){
    }


// READ MODE -----------------------------------------------------------------------------------------------------------
    /**
     * Open file in read mode
     * @param  fileName  the name of the file to read an object
     * @return true      the file exists and the stream is ok
     *      or false     the file doesn't exist or the stream has problems
     * @exception FileNotFoundException If a file not found exception occurred.
     * @exception IOException If an input output exception occurred.
     */
    public boolean turnOnReader(String fileName)
    {
        try
        {
            fileInput = new FileInputStream(fileName);
        }
        catch(FileNotFoundException error)
        {
            System.out.println(error.getMessage());
            return false;
        }

        try
        {
            in = new ObjectInputStream(fileInput);
            return true;
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
            return false;
        }
    }


    /**
     * Read an object in an open file in read mode
     * @return  Object the object itself
     *       or null   if an exception is launched
     * @exception ClassNotFoundException If a class not found exception occurred.
     * @exception IOException If an input output exception occurred.
     */
    public Object objectReader() // Don't forget the reserved word: cast
    {
        try
        {
            return in.readObject();
        }
        catch (ClassNotFoundException error)
        {
            System.out.println(error.getMessage());
            return null;
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
            return null;
        }
    }


    /**
     * Close file in read mode
     * @return true   file closed with success
     *      or false  file not closed
     * @exception IOException If an input outpu exception occurred.
     */
    public boolean turnOffReader()
    {
        try
        {
            in.close();
            fileInput.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }




// WRITE MODE ----------------------------------------------------------------------------------------------------------
    /**
     * Open file in write mode
     * @param  fileName  the name of the file to write an object
     * @return true      the file exists and the stream is ok
     *      or false     the file doesn't exist or the stream has problems
     * @exception FileNotFoundException If a file not found exception occurred.
     * @exception IOException If an input output exception occurred.
     */
    public boolean turnOnWriter(String fileName)
    {
        try
        {
            fileOutput = new FileOutputStream(fileName);
        }
        catch (FileNotFoundException error)
        {
            System.out.println(error.getMessage());
            return false;
        }

        try
        {
            out = new ObjectOutputStream(fileOutput);
            return true;
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
            return false;
        }
    }


    /**
     * Write an object in an open file in write mode
     * @param   objectName  the name of the object
     * @return  true        the object was successfully written
     *       or false       the object wasn't successfully written
     * @exception IOException If an input output exception occurred.
     */
    public boolean objectWriter(Object objectName)
    {
        try
        {
            out.writeObject(objectName);
            out.flush();
            return true;
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
            return false;
        }
    }


    /**
     * Close file in write mode
     * @return true   file closed with success
     *      or false  file not closed
     * @exception IOException If an input output exception occurred.
     */
    public boolean turnOffWriter()
    {
        try
        {
            out.close();
            fileOutput.close();
            return true;
        }
        catch (IOException error)
        {
            System.out.println(error.getMessage());
            return false;
        }
    }
}
