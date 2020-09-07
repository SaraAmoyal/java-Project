package allClasses;

import java.util.Date;
import allClasses.statusClass.taskStatus;
public class task {
    private int taskCode;
    private String taskTitle;
    private String taskDesc;
    private taskStatus taskStatus;
    private int taskBelongsTo;
    private int taskDays;
    private Date startDate;
    private Date endDate;


    public task(int taskCode, String taskTitle, String taskDesc, taskStatus taskStatus, int taskBelongsTo, int taskHours, Date startDate, Date endDate) {
        this.taskCode = taskCode;
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.taskStatus = taskStatus;
        this.taskBelongsTo = taskBelongsTo;
        this.taskDays = taskHours;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTaskCode() {
        return taskCode;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public taskStatus getTaskStatus() {
        return taskStatus;
    }

    public int getTaskBelongsTo() {
        return taskBelongsTo;
    }

    public float getTaskDays() {
        return taskDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public void setTaskStatus(taskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskBelongsTo(int taskBelongsTo) {
        this.taskBelongsTo = taskBelongsTo;
    }

    public void setTaskDays(int taskHours) {
        this.taskDays = taskHours;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}


