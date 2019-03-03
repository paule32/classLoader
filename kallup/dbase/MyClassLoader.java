package kallup.dbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader
{
    private static String filePath = "/config/Action.class";
    private static String fileURL  = "http://localhost/config/sample.txt";

    private static FileInputStream fileInputStream = null;
    private static int count = 0;

    @Override
    public Class findClass(String className)
    {
	URL urlObject = null;
	ReadableByteChannel rbcObject = null;

	Path filePathObject = Paths.get(filePath);
	boolean fileExists = Files.exists(filePathObject);

	if (fileExists) {
	    try {
		urlObject = new URL(fileURL);
		rbcObject = Channels.newChannel(urlObject.openStream());
		fileInputStream = new FileInputStream(filePath);

		fileInputStream.getChannel().transferFrom(rbcObject, 0, Long.MAX_VALUE);
		System.out.println("! File Successfully Downloaded From The Url !");
	    }
	    catch (IOException ex) {
		System.out.println("Problem during download !");
	    }
	    finally {
		try {
		    Class myClass  = null;
		    byte[] mybytes = new byte[2048];
		    fileInputStream.read(mybytes);

		    //System.out.println(new String(mybytes));
		    myClass = defineClass(className, mybytes, 0, mybytes.length);

		    if (fileInputStream != null) { fileInputStream.close(); }
		    if (rbcObject       != null) { rbcObject      .close(); }

		    return myClass;
		}
		catch (IOException ex) {
		    System.out.println("Problem during closing objects !");
		}
	    }
	}
	else {
	    System.out.println("File not present! Please Check!");
	}
	return null;
    }


    public MyClassLoader() {
    }
}