package fontrip.poi;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

public abstract class Poi
  extends java.lang.Object  implements
    java.io.Serializable,    groovy.lang.GroovyObject {
;
public  fontrip.poi.PoiDetail detail() { return (fontrip.poi.PoiDetail)null;}
public  java.lang.String getName() { return (java.lang.String)null;}
public  java.lang.String getDescription() { return (java.lang.String)null;}
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
public  java.lang.String getAccountCode() { return (java.lang.String)null;}
public  void setAccountCode(java.lang.String value) { }
public  fontrip.SysFile getMainPhoto() { return (fontrip.SysFile)null;}
public  void setMainPhoto(fontrip.SysFile value) { }
public  java.lang.String getVatNumber() { return (java.lang.String)null;}
public  void setVatNumber(java.lang.String value) { }
public  java.lang.String getStatus() { return (java.lang.String)null;}
public  void setStatus(java.lang.String value) { }
public static final  java.lang.String getSTATUS_EDIT() { return (java.lang.String)null;}
public static final  java.lang.String getSTATUS_ENABLE() { return (java.lang.String)null;}
public static final  java.lang.String getSTATUS_DISABLE() { return (java.lang.String)null;}
public  java.lang.Double getLatitude() { return (java.lang.Double)null;}
public  void setLatitude(java.lang.Double value) { }
public  java.lang.Double getLongitude() { return (java.lang.Double)null;}
public  void setLongitude(java.lang.Double value) { }
public  java.lang.Integer getViewCount() { return (java.lang.Integer)null;}
public  void setViewCount(java.lang.Integer value) { }
public  java.lang.String getLegacyId() { return (java.lang.String)null;}
public  void setLegacyId(java.lang.String value) { }
public  fontrip.location.Country getCountry() { return (fontrip.location.Country)null;}
public  void setCountry(fontrip.location.Country value) { }
public  fontrip.location.Location getLocation() { return (fontrip.location.Location)null;}
public  void setLocation(fontrip.location.Location value) { }
public  fontrip.location.Area getArea() { return (fontrip.location.Area)null;}
public  void setArea(fontrip.location.Area value) { }
public  java.lang.String getPhoneNumber() { return (java.lang.String)null;}
public  void setPhoneNumber(java.lang.String value) { }
public  java.lang.String getFaxNumber() { return (java.lang.String)null;}
public  void setFaxNumber(java.lang.String value) { }
public  java.lang.String getOpenTime() { return (java.lang.String)null;}
public  void setOpenTime(java.lang.String value) { }
public  java.lang.String getGooglePlaceId() { return (java.lang.String)null;}
public  void setGooglePlaceId(java.lang.String value) { }
public static final  java.lang.String getDATA_SOURCE_FONTRIP() { return (java.lang.String)null;}
public static final  java.lang.String getDATA_SOURCE_OPENDATA() { return (java.lang.String)null;}
public static final  java.lang.String getDATA_SOURCE_TRIPRESSO() { return (java.lang.String)null;}
public static final  java.lang.String getCOOPERATION_MODE_PRIVATE() { return (java.lang.String)null;}
public static final  java.lang.String getCOOPERATION_MODE_PROTECT() { return (java.lang.String)null;}
public static final  java.lang.String getCOOPERATION_MODE_PUBLIC() { return (java.lang.String)null;}
public  java.lang.String getDataFrom() { return (java.lang.String)null;}
public  void setDataFrom(java.lang.String value) { }
public  java.lang.String getCooperationMode() { return (java.lang.String)null;}
public  void setCooperationMode(java.lang.String value) { }
public static  java.lang.Object getTransients() { return null;}
public static  void setTransients(java.lang.Object value) { }
public  fontrip.account.User getOwner() { return (fontrip.account.User)null;}
public  void setOwner(fontrip.account.User value) { }
public  java.util.Map getDetails() { return (java.util.Map)null;}
public  void setDetails(java.util.Map value) { }
public  boolean getSearchable() { return false;}
public  boolean isSearchable() { return false;}
public  void setSearchable(boolean value) { }
public  java.lang.String getKeywordIndex() { return (java.lang.String)null;}
public  void setKeywordIndex(java.lang.String value) { }
public  fontrip.account.User getCreator() { return (fontrip.account.User)null;}
public  void setCreator(fontrip.account.User value) { }
public  int getRating() { return (int)0;}
public  void setRating(int value) { }
public static  java.lang.Object getMapping() { return null;}
public static  void setMapping(java.lang.Object value) { }
public static  java.lang.Object getConstraints() { return null;}
public static  void setConstraints(java.lang.Object value) { }
public  fontrip.poi.PoiDetail detail(java.lang.String lang) { return (fontrip.poi.PoiDetail)null;}
public  void loadDetail() { }
public  boolean isEnable() { return false;}
public  java.lang.Object setEnable(boolean enable) { return null;}
public  java.lang.String getName(java.lang.String locale) { return (java.lang.String)null;}
public  java.lang.String getDescription(java.lang.String locale) { return (java.lang.String)null;}
public  java.lang.String getHashid() { return (java.lang.String)null;}
public  java.lang.Object onLoad() { return null;}
public  boolean equals(java.lang.Object o) { return false;}
public  int hashCode() { return (int)0;}
public  java.lang.Object beforeUpdate() { return null;}
public  java.lang.Object beforeInsert() { return null;}
}
