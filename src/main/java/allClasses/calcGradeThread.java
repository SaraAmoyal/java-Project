package allClasses;

import java.util.Date;
import dbAccess.dbAccess;
import allClasses.statusClass.taskStatus;

public class calcGradeThread implements Runnable{

    int taskCode;
    taskStatus status;
    boolean cgRespponse=false;

    public calcGradeThread(int taskCode, taskStatus status){
        this.taskCode=taskCode;
        this.status=status;
    }

@Override
    public void run(){
        if(status==taskStatus.DONE){
            task task=dbAccess.getTask(this.taskCode);
            Date endDate = new Date();
            Date startDate = task.getStartDate();
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            long diffTime = startTime - endTime;
            long diffDays = diffTime / (1000 * 60 * 60 * 24*60);
            int doingDuration= (int) diffDays;

            int difference= (int) (task.getTaskDays()-doingDuration);
            cgRespponse=dbAccess.updateGrading(task.getTaskBelongsTo(), difference);
        }
        return ;
    }

    public boolean getResponse() {
        return cgRespponse;
    }
}
