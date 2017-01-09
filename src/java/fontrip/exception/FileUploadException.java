package fontrip.exception;

/**
 * Created by jci-001 on 2016/1/26.
 */
public class FileUploadException extends Exception {

    String code;
    //i18n fileUpload.error.
    final public static String UPLOAD_ERROR_FILE_SIZE = "UPLOAD_ERROR_FILE_SIZE";
    final public static String UPLOAD_ERROR_FILE_EXTENSION = "UPLOAD_ERROR_FILE_EXTENSION";
    final public static String UPLOAD_ERROR_CANT_WRITE = "UPLOAD_ERROR_CANT_WRITE";
    final public static  String UPLOAD_ERROR_CREATE_FOLDER = "UPLOAD_ERROR_CREATE_FOLDER";
    final public static String UPLOAD_ERROR_UNKNOWN = "UPLOAD_ERROR_UNKNOWN";

    public FileUploadException( String message , String code) {
        super(message);
        this.code = code;

    }

}
