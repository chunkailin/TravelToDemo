package fontrip.account;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

@groovy.transform.EqualsAndHashCode(includes="username") @groovy.transform.ToString(includeNames=true, includes="username", includePackage=false) public class User
  extends java.lang.Object  implements
    java.io.Serializable,    groovy.lang.GroovyObject {
;
public User
(java.lang.String username, java.lang.String password) {}
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public  java.lang.Object getSpringSecurityService() { return null;}
public  void setSpringSecurityService(java.lang.Object value) { }
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
public  java.lang.String getUsername() { return (java.lang.String)null;}
public  void setUsername(java.lang.String value) { }
public  java.lang.String getPassword() { return (java.lang.String)null;}
public  void setPassword(java.lang.String value) { }
public  boolean getEnabled() { return false;}
public  boolean isEnabled() { return false;}
public  void setEnabled(boolean value) { }
public  boolean getAccountExpired() { return false;}
public  boolean isAccountExpired() { return false;}
public  void setAccountExpired(boolean value) { }
public  boolean getAccountLocked() { return false;}
public  boolean isAccountLocked() { return false;}
public  void setAccountLocked(boolean value) { }
public  boolean getPasswordExpired() { return false;}
public  boolean isPasswordExpired() { return false;}
public  void setPasswordExpired(boolean value) { }
public  boolean getBackendUser() { return false;}
public  boolean isBackendUser() { return false;}
public  void setBackendUser(boolean value) { }
public  boolean getEmailValidate() { return false;}
public  boolean isEmailValidate() { return false;}
public  void setEmailValidate(boolean value) { }
public  java.lang.String getName() { return (java.lang.String)null;}
public  void setName(java.lang.String value) { }
public  java.lang.String getIdNumber() { return (java.lang.String)null;}
public  void setIdNumber(java.lang.String value) { }
public  boolean getGender() { return false;}
public  boolean isGender() { return false;}
public  void setGender(boolean value) { }
public  java.util.Date getBirthday() { return (java.util.Date)null;}
public  void setBirthday(java.util.Date value) { }
public  java.lang.String getPhone() { return (java.lang.String)null;}
public  void setPhone(java.lang.String value) { }
public  java.lang.String getMobilePhone() { return (java.lang.String)null;}
public  void setMobilePhone(java.lang.String value) { }
public  java.lang.String getAddress() { return (java.lang.String)null;}
public  void setAddress(java.lang.String value) { }
public  java.lang.String getLang() { return (java.lang.String)null;}
public  void setLang(java.lang.String value) { }
public  java.lang.String getEmail() { return (java.lang.String)null;}
public  void setEmail(java.lang.String value) { }
public  java.lang.String getTempEmail() { return (java.lang.String)null;}
public  void setTempEmail(java.lang.String value) { }
public  java.lang.String getFirstName() { return (java.lang.String)null;}
public  void setFirstName(java.lang.String value) { }
public  java.lang.String getLastName() { return (java.lang.String)null;}
public  void setLastName(java.lang.String value) { }
public  java.util.Date getEmailValidateDate() { return (java.util.Date)null;}
public  void setEmailValidateDate(java.util.Date value) { }
public  java.lang.String getEmailValidateCode() { return (java.lang.String)null;}
public  void setEmailValidateCode(java.lang.String value) { }
public  java.lang.String getNationality() { return (java.lang.String)null;}
public  void setNationality(java.lang.String value) { }
public  boolean getInfoCompleted() { return false;}
public  boolean isInfoCompleted() { return false;}
public  void setInfoCompleted(boolean value) { }
public  fontrip.SysFile getAvatar() { return (fontrip.SysFile)null;}
public  void setAvatar(fontrip.SysFile value) { }
public  java.lang.String getLegacyId() { return (java.lang.String)null;}
public  void setLegacyId(java.lang.String value) { }
public static  java.lang.Object getTransients() { return null;}
public static  void setTransients(java.lang.Object value) { }
public static  java.lang.Object getConstraints() { return null;}
public static  void setConstraints(java.lang.Object value) { }
public static  java.lang.Object getMapping() { return null;}
public static  void setMapping(java.lang.Object value) { }
public  java.util.Set<fontrip.account.RoleGroup> getRoleGroup() { return (java.util.Set<fontrip.account.RoleGroup>)null;}
public  java.lang.Object beforeInsert() { return null;}
public  java.lang.Object beforeUpdate() { return null;}
protected  void encodePassword() { }
public  java.lang.String getHashid() { return (java.lang.String)null;}
}
