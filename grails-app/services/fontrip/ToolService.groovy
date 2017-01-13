package fontrip

import grails.transaction.Transactional

@Transactional
class ToolService {

    public static void copyFile(File srFile, String dtFilePath){
        try{
            File f1 = srFile;
            File f2 = new File(dtFilePath);
            InputStream inStream = new FileInputStream(f1);

            //For Append the file.
//      OutputStream out = new FileOutputStream(f2,true);

            //For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = inStream.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            inStream.close();
            out.close();
            System.out.println("File copied.");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage() + " in the specified directory.");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
