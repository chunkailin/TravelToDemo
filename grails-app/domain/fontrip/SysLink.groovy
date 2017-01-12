package fontrip

class SysLink {
    transient stringService
    Long id;
    String name;
    String link;

    boolean youtubeLink = false;
    boolean deleted=false;
    static transients = ['stringService']

    static constraints = {
        //link(url: true,blank:false)

    }
    def String toString() {
        if(name.length()>0)		return name+":"+link
        else return link
    }
    def String toHTML() {
        if(name.length()>0)	return "<a target='_blank' href='"+link+"'>"+name+"</a>"
        else return "<a target='_blank' href='"+link+"'>"+link+"</a>"
    }
    public String getHashid(){
        return stringService.encodeHashid(SysLink,id)
    }
}
