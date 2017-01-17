package fontrip.account;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

@groovy.transform.ToString(cache=true, includeNames=true, includePackage=false) public class UserRoleGroup
  extends java.lang.Object  implements
    java.io.Serializable,    groovy.lang.GroovyObject {
;
public UserRoleGroup
(fontrip.account.User u, fontrip.account.RoleGroup rg) {}
public static  fontrip.account.UserRoleGroup create(fontrip.account.User user, fontrip.account.RoleGroup roleGroup) { return (fontrip.account.UserRoleGroup)null;}
public static  boolean remove(fontrip.account.User u, fontrip.account.RoleGroup r) { return false;}
public static  void removeAll(fontrip.account.User u) { }
public static  void removeAll(fontrip.account.RoleGroup r) { }
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public  fontrip.account.User getUser() { return (fontrip.account.User)null;}
public  void setUser(fontrip.account.User value) { }
public  fontrip.account.RoleGroup getRoleGroup() { return (fontrip.account.RoleGroup)null;}
public  void setRoleGroup(fontrip.account.RoleGroup value) { }
public static  java.lang.Object getConstraints() { return null;}
public static  void setConstraints(java.lang.Object value) { }
public static  java.lang.Object getMapping() { return null;}
public static  void setMapping(java.lang.Object value) { }
@java.lang.Override() public  boolean equals(java.lang.Object other) { return false;}
@java.lang.Override() public  int hashCode() { return (int)0;}
public static  fontrip.account.UserRoleGroup get(long userId, long roleGroupId) { return (fontrip.account.UserRoleGroup)null;}
public static  boolean exists(long userId, long roleGroupId) { return false;}
public static  fontrip.account.UserRoleGroup create(fontrip.account.User user, fontrip.account.RoleGroup roleGroup, boolean flush) { return (fontrip.account.UserRoleGroup)null;}
public static  boolean remove(fontrip.account.User u, fontrip.account.RoleGroup r, boolean flush) { return false;}
public static  void removeAll(fontrip.account.User u, boolean flush) { }
public static  void removeAll(fontrip.account.RoleGroup r, boolean flush) { }
}
