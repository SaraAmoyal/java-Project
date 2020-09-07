package allClasses;

import allClasses.statusClass.taskStatus;

public class updateStatusThread implements Runnable{
    int taskCode;
    taskStatus status;
    boolean succeed=false;
    public updateStatusThread(int taskCode, taskStatus status){
        this.taskCode=taskCode;
        this.status=status;
    }

    @Override
    public void run(){
        succeed= dbAccess.dbAccess.updateStatus(this.taskCode,this.status);
        return;
    }

    public boolean getResponse(){
        return succeed;
    }
}
