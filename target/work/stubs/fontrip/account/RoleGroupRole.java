package fontrip.account;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import groovy.lang.*;
import groovy.util.*;

@groovy.transform.ToString(cache=true, includeNames=true, includePackage=false) public class RoleGroupRole
  extends java.lang.Object  implements
    java.io.Serializable,    groovy.lang.GroovyObject {
;
public RoleGroupRole
(fontrip.account.RoleGroup g, fontrip.account.Role r) {}
public static  fontrip.account.RoleGroupRole create(fontrip.account.RoleGroup roleGroup, fontrip.account.Role role) { return (fontrip.account.RoleGroupRole)null;}
public static  boolean remove(fontrip.account.RoleGroup rg, fontrip.account.Role r) { return false;}
public static  void removeAll(fontrip.account.Role r) { }
public static  void removeAll(fontrip.account.RoleGroup rg) { }
public  groovy.lang.MetaClass getMetaClass() { return (groovy.lang.MetaClass)null;}
public  void setMetaClass(groovy.lang.MetaClass mc) { }
public  java.lang.Object invokeMethod(java.lang.String method, java.lang.Object arguments) { return null;}
public  java.lang.Object getProperty(java.lang.String property) { return null;}
public  void setProperty(java.lang.String property, java.lang.Object value) { }
public  fontrip.account.RoleGroup getRoleGroup() { return (fontrip.account.RoleGroup)null;}
public  void setRoleGroup(fontrip.account.RoleGroup value) { }
public  fontrip.account.Role getRole() { return (fontrip.account.Role)null;}
public  void setRole(fontrip.account.Role value) { }
public static  java.lang.Object getConstraints() { return null;}
public static  void setConstraints(java.lang.Object value) { }
public static  java.lang.Object getMapping() { return null;}
public static  void setMapping(java.lang.Object value) { }
@java.lang.Override() public  boolean equals(java.lang.Object other) { return false;}
@java.lang.Override() public  int hashCode() { return (int)0;}
public static  fontrip.account.RoleGroupRole get(long roleGroupId, long roleId) { return (fontrip.account.RoleGroupRole)null;}
public static  boolean exists(long roleGroupId, long roleId) { return false;}
public static  fontrip.account.RoleGroupRole create(fontrip.account.RoleGroup roleGroup, fontrip.account.Role role, boolean flush) { return (fontrip.account.RoleGroupRole)null;}
public static  boolean remove(fontrip.account.RoleGroup rg, fontrip.account.Role r, boolean flush) { return false;}
public static  void removeAll(fontrip.account.Role r, boolean flush) { }
public static  void removeAll(fontrip.account.RoleGroup rg, boolean flush) { }
}
