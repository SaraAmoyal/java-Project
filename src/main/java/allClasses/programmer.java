package allClasses;

public class programmer extends teamMember {
    private int grading;

    public programmer(int dmCode, String dmName, String dmMail, int grading) {
        super(dmCode, dmName, dmMail);
        this.grading = grading;
    }

    public int getGrading() {
        return grading;
    }

    public void setGrading(int grading) {
        this.grading = grading;
    }
}
