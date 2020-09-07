package allClasses;

public class teamMember {

    private int dmCode;
    private String dmName;
    private String dmMail;

    public teamMember(int dmCode, String dmName, String dmMail) {
        this.dmCode = dmCode;
        this.dmName = dmName.trim();
        if(dmMail.contains("@")&&!dmMail.startsWith("@")&&!dmMail.endsWith("@"))
            this.dmMail = dmMail;
        else{
            this.dmMail="";
            System.out.println("You entered not valid email!");
        }
    }

    public teamMember() {

    }

    public int getDmCode() {
        return dmCode;
    }

    public String getDmName() {
        return dmName;
    }

    public String getDmMail() {
        return dmMail;
    }

    public void setDmCode(int dmCode) {
        this.dmCode = dmCode;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName.trim();
    }

    public void setDmMail(String dmMail) {
        if(dmMail.contains("@")&&!dmMail.startsWith("@")&&!dmMail.endsWith("@"))
            this.dmMail = dmMail;
    }

    public String toString(){
        return "The name is: "+this.dmName+" and the email is: "+this.dmMail;
    }
}
