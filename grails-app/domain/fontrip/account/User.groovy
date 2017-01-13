package fontrip.account

import fontrip.SysFile
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService
    transient stringService

    /* Default (injected) attributes of GORM */
    Long id
    Long version

    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated

    String username
    String password
    boolean enabled = false
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    boolean backendUser = false
    boolean emailValidate = false
    String name
    String idNumber //身分證字號或護照號碼

    // 20160127 add by Rachel
    boolean gender = true//true:boy false: girl
    Date birthday
    String phone
    String mobilePhone
    String address
    String lang="zh_TW"
    String email
    String tempEmail
    String firstName
    String lastName
    Date emailValidateDate
    String emailValidateCode
    String nationality="TW" //2016/03/08 add by Katie
    boolean infoCompleted = false
    SysFile avatar
    String legacyId//20160711新增,用來存員工編號

	User(String username, String password) {
		this.username = username
		this.password = password
	}

//	Set<Role> getAuthorities() {
//		UserRole.findAllByUser(this)*.role
//	}

    Set<RoleGroup> getRoleGroup() {
        return UserRoleGroup.findAllByUser(this)*.roleGroup
    }

	def beforeInsert() {
		encodePassword()
        if(!name || name=="" || name=="null null"){
            if(firstName && lastName){
                name = firstName + " " +lastName
            }else{
                if(username.indexOf('@'))name = username.substring(0,username.indexOf('@'))
                else name = username
            }
        }
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
        if(!name || name=="" || name=="null null"){
            if(firstName && lastName){
                name = firstName + " " +lastName
            }else{
                if(username.indexOf('@'))name = username.substring(0,username.indexOf('@'))
                else name = username
            }
        }
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService', 'stringService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
        name nullable:true
        idNumber nullable:true
        birthday nullable:true
        phone blank: true,nullable:true
        mobilePhone blank: true,nullable:true
        address nullable:true
        email nullable:true
        tempEmail nullable:true
        firstName nullable:true
        lastName nullable:true
        emailValidateDate nullable:true
        emailValidateCode nullable:true
        nationality nullable: true
        avatar nullable: true
        legacyId(nullable:true)
	}

	static mapping = {
		password column: '`password`'
	}

    public String getHashid(){
        return stringService.encodeHashid(User,id)
    }
}
