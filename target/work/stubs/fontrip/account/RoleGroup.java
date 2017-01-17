package fontrip.account;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

@groovy.transform.EqualsAndHashCode(includes="name") @groovy.transform.ToString(includeNames=true, includes="name", includePackage=false) public class RoleGroup
  extends java.lang.Object  implements
    java.io.Serializable,    groovy.lang.GroovyObject {
;
public RoleGroup
(java.lang.String name) {
super ();
}
public RoleGroup
(java.lang.String name, boolean defaultRoleGroup) {}
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public static final  java.lang.String getNAMECODE_PREFIX() { return (java.lang.String)null;}
public static final  java.lang.String getNAME_PREFIX() { return (java.lang.String)null;}
public  java.lang.Object getStringService() { return null;}
public  void setStringService(java.lang.Object value) { }
public static  java.lang.Object getTransients() { return null;}
public static  void setTransients(java.lang.Object value) { }
public  java.lang.Long getId() { return (java.lang.Long)null;}
public  void setId(java.lang.Long value) { }
public  java.lang.Long getVersion() { return (java.lang.Long)null;}
public  void setVersion(java.lang.Long value) { }
public  java.lang.String getName() { return (java.lang.String)null;}
public  void setName(java.lang.String value) { }
public  boolean getDefaultRoleGroup() { return false;}
public  boolean isDefaultRoleGroup() { return false;}
public  void setDefaultRoleGroup(boolean value) { }
public  java.util.Date getDateCreated() { return (java.util.Date)null;}
public  void setDateCreated(java.util.Date value) { }
public  java.util.Date getLastUpdated() { return (java.util.Date)null;}
public  void setLastUpdated(java.util.Date value) { }
public static  java.lang.Object getConstraints() { return null;}
public static  void setConstraints(java.lang.Object value) { }
public static  java.lang.Object getMapping() { return null;}
public static  void setMapping(java.lang.Object value) { }
public  java.util.Set<fontrip.account.Role> getAuthorities() { return (java.util.Set<fontrip.account.Role>)null;}
public  java.lang.String getNameCode() { return (java.lang.String)null;}
}
