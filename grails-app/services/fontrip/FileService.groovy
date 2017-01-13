package fontrip

import grails.transaction.Transactional

@Transactional
class FileService {

    def availableFileExtension = ['*']
    def availablePhotoExtension = ['*']
    def availableVideoExtension = ['*']
    def availableAudioExtension = ['*']

    final static String UPLOAD_FOLDER_TEMP = "temp";
    final static String UPLOAD_FOLDER_FILES = "files";
    final static String UPLOAD_FOLDER_PHOTO = "photo";
    final static String UPLOAD_FOLDER_AUDIO = "audio";
    final static String UPLOAD_FOLDER_VIDEO = "video";
    final static String UPLOAD_FOLDER_PHOTOCACHE = "photoCache";
    // 請款檔相關目錄
    final static String UPLOAD_FOLDER_PAYMENT_REQUEST= "paymentRequest";
    final static String UPLOAD_FOLDER_PAYMENT_RESPONSE= "paymentResponse";
    final static String UPLOAD_FOLDER_NCCC= "NCCC";
    final static String UPLOAD_FOLDER_ESUN= "ESUN";


    long fileUploadLimit = 10*1024 //設定檔是MB,這裡轉為KB
    long photoUploadLimit = 10*1024 //設定檔是MB,這裡轉為KB
    long audioUploadLimit = 30*1024//設定檔是MB,這裡轉為KB
    long videoUploadLimit = 100*1024//設定檔是MB,這裡轉為KB
    String uploadPath = "/home/FonTrip/"
    public static String cloudBaseFolder = "/backup/fontrip/cloudFile/"

    def init() {
        log.info "file service init...."

        def config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_EXTENSION)
        availableFileExtension = config?(config.configValue.indexOf(',')>0?config.configValue.split(','):[config.configValue]):['*']

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_UPLOADLIMIT)
        fileUploadLimit = config?Integer.parseInt(config.configValue)*1024 : 10*1024

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_EXTENSION)
        availablePhotoExtension = config?(config.configValue.indexOf(',')>0?config.configValue.split(','):[config.configValue]):["jpg","jpeg","gif","png","bmp"]

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_UPLOADLIMIT)
        photoUploadLimit = config?Integer.parseInt(config.configValue)*1024 : 20*1024

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_EXTENSION)
        availableVideoExtension = config?(config.configValue.indexOf(',')>0?config.configValue.split(','):[config.configValue]):["mp4"]

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_UPLOADLIMIT)
        videoUploadLimit = config?Integer.parseInt(config.configValue)*1024 : 100*1024

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_EXTENSION)
        availableAudioExtension = config?(config.configValue.indexOf(',')>0?config.configValue.split(','):[config.configValue]):["mp3"]

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_UPLOADLIMIT)
        audioUploadLimit = config?Integer.parseInt(config.configValue)*1024 : 30*1024

        config = SysConfig.findByConfigGroupAndConfigCode(SysConfig.CONFIG_GROUP_SYSTEM_FILEUPLOAD,SysConfig.CONFIG_CODE_SYSTEM_FILEUPLOAD_UPLOAD_PATH)
        uploadPath = config?config.configValue:"/home/FonTrip/"
        if (!uploadPath.endsWith('/')) uploadPath = uploadPath + "/"

        new File(uploadPath+UPLOAD_FOLDER_TEMP+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_FILES+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_PHOTO+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_AUDIO+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_VIDEO+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_PHOTOCACHE+"/").mkdirs()

        // 請款檔相關目錄
        new File(uploadPath+UPLOAD_FOLDER_PAYMENT_REQUEST+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_PAYMENT_RESPONSE+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_PAYMENT_REQUEST+"/"+UPLOAD_FOLDER_NCCC+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_PAYMENT_REQUEST+"/"+UPLOAD_FOLDER_ESUN+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_PAYMENT_RESPONSE+"/"+UPLOAD_FOLDER_NCCC+"/").mkdirs()
        new File(uploadPath+UPLOAD_FOLDER_PAYMENT_RESPONSE+"/"+UPLOAD_FOLDER_ESUN+"/").mkdirs()


        for(sysConfig in SysConfig.findAllByConfigGroup(SysConfig.CONFIG_GROUP_SYSTEM_FTP)){
            if(sysConfig.configCode==SysConfig.CONFIG_CODE_SYSTEM_FTP_BASEFOLDER){
                cloudBaseFolder = sysConfig.configValue
            }
        }
    }
}
