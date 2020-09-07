package allClasses;

public class statusClass{
public enum taskStatus {
    NEW,
    DONE,
    INPROGRESS

}
public static taskStatus getstatusByNum(int number){
    switch (number){
        case 1:return taskStatus.NEW;
        case 2:return taskStatus.DONE;
        case 3:return taskStatus.INPROGRESS;
    }
    return null;
}
}