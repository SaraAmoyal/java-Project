package com;

import dbAccess.dbAccess;
import allClasses.*;
import allClasses.statusClass.taskStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@SpringBootApplication
@RestController
@RequestMapping(path = "seaJavaApplication")
public class SeajavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeajavaApplication.class, args);
    }

    @RequestMapping("/getTaskByDM")
    public List<task> getTaskByDM(@RequestParam() int dmCode) {
        return dbAccess.getTasksByDM(dmCode);
    }

    @RequestMapping("/getAllDataMember")
    public List<String> getAllDataMember() {
        List<programmer> allDM = dbAccess.getAllDataMembers();
        List<String> programmerString = new ArrayList<String>();
        for (programmer p :
                allDM) {
            programmerString.add(p.toString());
        }
        Collections.sort(programmerString, new programmerComparator());
        return programmerString;
    }


    @RequestMapping("/changeStatus")
    public String changeStatus(@RequestParam int taskCode, @RequestParam taskStatus status) {
        boolean usResponse=false;
        boolean cgResponse=false;
        String answer="";

        updateStatusThread updateStatusThrad=new updateStatusThread(taskCode,status);
        Thread usThread=new Thread(updateStatusThrad);
        usThread.start();
        try {
            usThread.join();
            usResponse=updateStatusThrad.getResponse();
        }catch (InterruptedException exception){
            System.out.println(exception.getMessage());
        }

        Runnable CalcGradeThred=new calcGradeThread(taskCode,status);
        Thread cgThread=new Thread(CalcGradeThred);
        cgThread.start();
        try {
            cgThread.join();
            cgResponse=updateStatusThrad.getResponse();
        }catch (InterruptedException exception){
            System.out.println(exception.getMessage());
        }
        if (usResponse == true)
            answer = "The status updated";
        else
            answer = "The status didn't updated";
        if (status==taskStatus.DONE) {
            if (cgResponse== true)
                answer += ", and the programmer greade updated.";
            else
                answer += ", and the programmer greade didn't updated.";
        }
        return answer;

    }

    @RequestMapping("/DMExists")
    public String DMExists(@RequestParam String dmName, @RequestParam String dmMail) {
        List<programmer> dmList=dbAccess.getAllDataMembers();
        Map<String,String> Map=new HashMap<String, String>();
        for (programmer p:
                dmList  ) {
            Map.put(p.getDmName(),p.getDmMail());
        }
        for (Map.Entry<String, String> entry : Map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if(k.equals(dmName)){
                if(v.equals(dmMail))
                    return "This user is exists.";
                else
                    return "This user is not exists.";
            }
        }
       return "error in DMExists";
    }
}
