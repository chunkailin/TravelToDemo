package fontrip;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

public class SysConfig
  extends java.lang.Object  implements
    groovy.lang.GroovyObject {
;
public static java.lang.Object configScopeList;
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public  java.lang.Object getStringService() { return null;}
public  void setStringService(java.lang.Object value) { }
public  java.lang.Long getId() { return (java.lang.Long)null;}
public  void setId(java.lang.Long value) { }
public  java.lang.Long getVersion() { return (java.lang.Long)null;}
public  void setVersion(java.lang.Long value) { }
public  java.util.Date getDateCreated() { return (java.util.Date)null;}
public  void setDateCreated(java.util.Date value) { }
public  java.util.Date getLastUpdated() { return (java.util.Date)null;}
public  void setLastUpdated(java.util.Date value) { }
public  java.lang.String getConfigGroup() { return (java.lang.String)null;}
public  void setConfigGroup(java.lang.String value) { }
public  java.lang.String getConfigCode() { return (java.lang.String)null;}
public  void setConfigCode(java.lang.String value) { }
public  java.lang.String getScope() { return (java.lang.String)null;}
public  void setScope(java.lang.String value) { }
public  boolean getEditable() { return false;}
public  boolean isEditable() { return false;}
public  void setEditable(boolean value) { }
public  boolean getRequired() { return false;}
public  boolean isRequired() { return false;}
public  void setRequired(boolean value) { }
public  java.lang.String getRegularExpression() { return (java.lang.String)null;}
public  void setRegularExpression(java.lang.String value) { }
public  java.lang.String getConfigValue() { return (java.lang.String)null;}
public  void setConfigValue(java.lang.String value) { }
public  boolean getEditByAdmin() { return false;}
public  boolean isEditByAdmin() { return false;}
public  void setEditByAdmin(boolean value) { }
public static final  java.lang.String getCONFIG_GROUP_SYSTEM_FILEUPLOAD() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_UPLOADLIMIT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_EXTENSION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_DEFAULTSIZE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_AVAILABLESIZE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_UPLOADLIMIT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_FILE_EXTENSION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_UPLOADLIMIT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_AUDIO_EXTENSION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_UPLOADLIMIT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_VIDEO_EXTENSION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_UPLOAD_PATH() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_WIDTH() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FILEUPLOAD_PHOTO_MIN_HEIGHT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_SYSTEM_CLOUD_LOG() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_CLOUD_LOG_APPID() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_CLOUD_LOG_SECRET() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_SYSTEM_VALIDATE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_VALIDATE_EMAIL_TIME() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_SYSTEM_GENERAL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_GENERAL_DEBUG() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_GENERAL_LOCALE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_SYSTEM_FTP() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FTP_SERVER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FTP_USERNAME() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FTP_PASSWORD() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_FTP_BASEFOLDER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_SYSTEM_GOOGLE_API() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_GOOGLE_API_KEY() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_SYSTEM_GOOGLE_SERVER_API_KEY() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_STORE_BOOKING_NOTIFY() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_BOOKING_NOTIFY_SMS_ENABLE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_BOOKING_NOTIFY_SMS_NUMBER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_BOOKING_NOTIFY_FAX_ENABLE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_BOOKING_NOTIFY_FAX_NUMBER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_STORE_GENERAL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_GENERAL_LOCALE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_STORE_MODULE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_MODULE_BOOKING() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_MODULE_BRANCH() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_STORE_BANK_ACCOUNT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_BANK_ACCOUNT_NAME() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_BANK_ACCOUNT_BANKID() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_STORE_BANK_ACCOUNT_NUMBER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_BRANCH_GENERAL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_BRANCH_GENERAL_LOCALE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_AGENT_GENERAL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_GENERAL_LOCALE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_CHANNEL_GENERAL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_GENERAL_LOCALE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_WEB_INDEX_URL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_WEB_REGISTER_SUCCESS_URL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_WEB_SALE_ITEM_URL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_WEB_USER_PROFILE_URL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_WEB_PAID_SUCCESS_URL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_WEB_PAID_FAILED_URL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CHANNEL_APP_REGISTER_SUCCESS_URL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_AGENT_MODULE_LION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_LION_KEY() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_LION_SECRET() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_LION_CHANNEL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_AGENT_MODULE_LION_ELECTRONIC() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_LION_ELECTRONIC_KEY() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_LION_ELECTRONIC_SECRET() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_AGENT_MODULE_17LIFE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_17LIFE_KEY() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_17LIFE_CHANNEL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_AGENT_MODULE_THSR() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_THSR_AGENT_ID() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_AGENT_BANK_ACCOUNT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_BANK_ACCOUNT_NAME() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_BANK_ACCOUNT_BANKID() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_BANK_ACCOUNT_NUMBER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_AGENT_MODULE_TRUST() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_TRUST_PLATFORM_LEGACYID() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_AGENT_MODULE_TRUST_AGENT_LEGACYID() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_CONTRACT_SETTING() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CONTRACT_SETTING_ETID_LION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CONTRACT_SETTING_ESEQ_LION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CONTRACT_SETTING_DEALGUID_17LIFE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CONTRACT_SETTING_STOREGUID_17LIFE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CONTRACT_SETTING_PARENT_DEALGUID_17LIFE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_CONTRACT_SETTING_GROUP_ID() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_GROUP_PACKAGETOUR_SETTING() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_LANGUAGE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_ENOUGHREGISTER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_DURATION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_REFUNDRULE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_TRANSPORTATION() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_DEPARTUREDAY() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_TRANSPORTATION_OWNTO() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_CODE_PACKAGETOUR_SETTING_TRANSPORTATION_SHUTTLE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_SYSTEM() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_STORE() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_CHANNEL() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_AGENT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_BRANCH() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_USER() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_SPOT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_CONTRACT() { return (java.lang.String)null;}
public static final  java.lang.String getCONFIG_SCOPE_PACKAGETOUR() { return (java.lang.String)null;}
public static  java.lang.Object getAuditable() { return null;}
public static  void setAuditable(java.lang.Object value) { }
public static  java.lang.Object getMapping() { return null;}
public static  void setMapping(java.lang.Object value) { }
public static  java.lang.Object getTransients() { return null;}
public static  void setTransients(java.lang.Object value) { }
public static  java.lang.Object getConstraints() { return null;}
public static  void setConstraints(java.lang.Object value) { }
public static  java.lang.String getConfig(java.lang.Object code) { return (java.lang.String)null;}
public  java.lang.String getHashid() { return (java.lang.String)null;}
}
