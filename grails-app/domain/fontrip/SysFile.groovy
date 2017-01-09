package fontrip

import fontrip.exception.FileUploadException
import grails.util.Holders
import org.apache.commons.io.FileUtils
//import org.apache.commons.net.ftp.FTP
//import org.apache.commons.net.ftp.FTPClient
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

//import javax.imageio.ImageIO
//import java.awt.image.BufferedImage
import java.nio.file.Path
import java.nio.file.Paths

class SysFile implements Serializable {

    transient stringService,httpService
    /* Default (injected) attributes of GORM */
    Long	id

    String name
    String description;
    Long size
    String path
    String extension
    Date dateUploaded
    Integer downloads = 0
    boolean isPhoto = false;
    boolean deleted=false;
    String folder
    String fileName
    boolean cloudSync = false
    boolean isDefaultPhoto = false
    String cloudFolder
    int imageWidth=-1
    int imageHeight=-1

    static transients = [ 'deleted','messageSource' ]
    static constraints = {
        size(min:0L)
        path()
        name()
        extension()
        dateUploaded()
        downloads()
        description()
        cloudFolder(nullable:true)
    }
    public String getHashid(){
        return stringService.encodeHashid(SysFile,id)
    }

    public String getCloudPath(){
        return "/"+cloudFolder+"?legacyId="+this.hashid
    }

    public boolean checkFile(){
        Path p = Paths.get(path)
        try{
            //檢查檔案是否存在
            if (!new File(path).exists() && cloudSync) {
                //找不到圖片,從遠端取圖
                String sourceUrl = Holders.config.fontrip.cloudFile.download+cloudPath
                if(HttpService.checkUrlExist(sourceUrl)){
                    //確認目錄的有效性
                    try {
                        new File(folder).mkdirs()
                        if (!new File(folder).isDirectory()) {
                            String errorMsg = "create folder error=" + folder;
                            throw new FileUploadException(errorMsg, FileUploadException.UPLOAD_ERROR_CREATE_FOLDER)
                        }
                    } catch (Exception e) {
                        String errorMsg = "create folder error=" + folder + ",exception=" + e.toString();
                        throw new FileUploadException(errorMsg, FileUploadException.UPLOAD_ERROR_CREATE_FOLDER)
                    }

                    //搬移檔案
                    HttpGet req = new HttpGet(sourceUrl);
                    HttpClient client = new DefaultHttpClient();
                    HttpResponse response = client.execute(req);
                    // validate response code, etc.
                    InputStream inputStream = response.getEntity().getContent();
                    if(inputStream.available()>0){
                        def destinationFile = new File(path)
                        FileUtils.copyInputStreamToFile(inputStream, destinationFile)
                        return true
                    }
                }
//                FTPClient ftpClient = httpService.ftpConnect()
//                //確認FTP連線
//                if(ftpClient) {
//                    log.warn "Cannot find local file from ${path}"
//
//
//                    ftpClient.enterLocalPassiveMode();
//                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                    ftpClient.changeWorkingDirectory(cloudFolder);
//                    p.parent.toFile().mkdirs()
//                    String remoteFile = cloudFolder + fileName
//
//                    //如果遠端有檔案,則從遠端下載檔案
//                    File downloadFile = new File(path);
//                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
//                    InputStream inputStream = ftpClient.retrieveFileStream(remoteFile);
//
//                    def returnCode = ftpClient.getReplyCode();
//                    if (inputStream == null || returnCode == 550) {
//                        //找不到檔案
//                    }else{
//                        byte[] bytesArray = new byte[4096];
//                        int bytesRead = -1;
//                        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
//                            outputStream.write(bytesArray, 0, bytesRead);
//                        }
//
//                        boolean success = ftpClient.completePendingCommand();
//                        if (success) {
//                            System.out.println("File has been downloaded successfully.");
//                        }
//
//                    }
//                    outputStream.close();
//                    inputStream.close();
//
//
//                }
//                httpService.ftpDisconnect(ftpClient)
            }


        }catch (Exception ex){
            log.error(ex.toString())
        }
        return false
    }

    def afterInsert = {
        //marsk ftp upload by become on 20161003
//		def config = Holders.config
//		String envMode = config.fontrip.envMode;
//		if (envMode != "localMode") {
//			FTPClient ftpClient = httpService.ftpConnect()
//
//			if (ftpClient != null) {
//				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//				if (!httpService.ftpDirectoryExists(ftpClient, cloudFolder)) {
//					httpService.ftpMakeDirs(ftpClient, cloudFolder)
//				}
//				ftpClient.changeWorkingDirectory(cloudFolder);
//				InputStream inputStream = new FileInputStream(new File(path));
//				cloudSync = ftpClient.storeFile(fileName, inputStream);
//				inputStream.close();
//				httpService.ftpDisconnect(ftpClient)
//			}
//		}
    }
}
