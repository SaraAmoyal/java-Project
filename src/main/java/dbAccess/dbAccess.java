package dbAccess;

import allClasses.statusClass;
import  allClasses.task;
import allClasses.programmer;
import allClasses.statusClass.taskStatus;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class dbAccess {

    static final String db_URL = "jdbc:sqlserver://SQL-KITOTBB\\TICHNUT;DatabaseName=seajava";
    static final String User = "tichnut";
    static final String PASS = "tichnut400";
    static Connection connection = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    //*****************CASH
    static List<programmer> programmerList= Arrays.asList(
            new programmer[]{new programmer(206, "sara amoyal", "saraa@gmail.com", 100),
            new programmer(333, "efrat kindil", "efratk@gmail.com", 110),
            new programmer(207, "avital meshulam", "avitalm@gmail.com", 120)
    });
    static List<task> taskList=Arrays.asList(
            new task[]{new task(1234, "aaa", "aaaaaa", taskStatus.DONE, 206, 3, new GregorianCalendar(2020, 9, 4).getTime(), new GregorianCalendar(2014, 2, 13).getTime()),
            new task(2345, "bbb", "bbbbbb", taskStatus.INPROGRESS, 333, 2, new GregorianCalendar(2014, 9, 4).getTime(), null),
            new task(3456, "ccc", "cccccc", taskStatus.NEW, 207, 1, new GregorianCalendar(2014, 9, 5).getTime(), null)});


    public static List<programmer> getAllDataMembers() {
        if (programmerList!=null){
            return programmerList;
        }
        String sql = "select * from teamMembers";
        try {
            connection = DriverManager.getConnection(db_URL, User, PASS);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            List<programmer> dataMembers = new ArrayList<programmer>();
            while (rs.next()) {
                dataMembers.add(new programmer(rs.getInt("dmCode"), rs.getString("dmName"), rs.getString("dmMail"),
                        rs.getInt("grading")));
            }
            programmerList.addAll(dataMembers);
            return dataMembers;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public static task getTask(int taskCode) {
        if(taskList==null){
        String selectSql = "select * from tasks";
        try {
            connection = DriverManager.getConnection(db_URL, User, PASS);
            ps = connection.prepareStatement(selectSql);
            rs = ps.executeQuery();
            List<task> tasks=new ArrayList<task>();
            while(rs.next()) {
                tasks.add(new task(rs.getInt("taskCode"),
                        rs.getString("taskTitle"), rs.getString("taskDesc"),
                        statusClass.getstatusByNum(rs.getInt("taskStatus")), rs.getInt("taskBelongsTo"),
                        rs.getInt("taskDays"), rs.getDate("startDate"),
                        rs.getDate("endDate"))) ;
            }
            taskList.addAll(tasks);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } }
        for (task task:
                taskList) {
            if(task.getTaskCode()==taskCode)
                return task;
        }
        return null;
    }

    public static List<task> getTasksByDM(int dmCode) {
        List<task> retTaskList = new ArrayList<task>();
        if(taskList!=null){
            if(dmCode==0)
                retTaskList.addAll(taskList);
            else
                for (task task:
                     taskList) {
                 if(task.getTaskBelongsTo()==dmCode)
                       retTaskList.add(task);
                }
            return retTaskList;
        }
        String sql = "select * from tasks where taskBelongsTo=" + dmCode;
        if (dmCode == 0) {
            sql = "select * from tasks";
        }
        try {
            connection = DriverManager.getConnection(db_URL, User, PASS);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                retTaskList.add(new task(rs.getInt("taskCode"),
                        rs.getString("taskTitle"), rs.getString("taskDesc"),
                        statusClass.getstatusByNum(rs.getInt("taskStatus")), rs.getInt("taskBelongsTo"),
                        rs.getInt("taskDays"), rs.getDate("startDate"),
                        rs.getDate("endDate"))) ;
            }
            return retTaskList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public static boolean insertTask(task task) {
        if(taskList!=null){
            taskList.add(task);
        }

        String sql = "insert into tasks (taskCode, taskTitle, taskDesc, taskStatus, taskBelongsTo, taskHours, startDate, endDate)" +
                "values (" + task.getTaskCode() + ", " + task.getTaskTitle() + ", " + task.getTaskDesc() + ", " +
                task.getTaskStatus() + ", " + task.getTaskBelongsTo() + ", " + task.getTaskDays() + ", " + task.getStartDate() + ", " + task.getEndDate() + ")";
        try {
            connection = DriverManager.getConnection(db_URL, User, PASS);
            ps = connection.prepareStatement(sql);
            int numOfEffectedRows = ps.executeUpdate();
            if (numOfEffectedRows == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return false;
    }

    public static boolean updateStatus(int taskCode, taskStatus status) {
        if(taskList!=null){
            for (task task:
                 taskList) {
                if (task.getTaskCode() == taskCode) {
                    task.setTaskStatus(status);
                    break;
                }
            }
        }
        String sql ="update tasks set taskStatus= "+status+" where taskCode="+taskCode;
        try {
            connection = DriverManager.getConnection(db_URL, User, PASS);
            ps = connection.prepareStatement(sql);
            int numOfEffectedRows = ps.executeUpdate();
            if (numOfEffectedRows == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return false;
    }

    public static boolean updateGrading(int pCode, int points)  {
        if(programmerList!=null){
            for (programmer programmer:
                    programmerList) {
                if(programmer.getDmCode()==pCode){
                    programmer.setGrading(programmer.getGrading()+points);
                    break;
                }
            }
        }
        String sql ="update teamMembers set grading+= "+points+" where dmCode="+pCode;
        try {
            connection = DriverManager.getConnection(db_URL, User, PASS);
            ps = connection.prepareStatement(sql);
            int numOfEffectedRows = ps.executeUpdate();
            if (numOfEffectedRows == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return false;
    }

    public static boolean updateTaskEndDate(int taskCode) {
        if(taskList!=null){
            for (task task:
                    taskList) {
                if(task.getTaskCode()==taskCode){
                    task.setEndDate(new Date());
                }
            }
        }
        String updateSql="update tasks set endDate="+new Date()+"where taskCode="+taskCode;
        try {
            connection = DriverManager.getConnection(db_URL, User, PASS);
            ps = connection.prepareStatement(updateSql);
            int numOfEffectedRows = ps.executeUpdate();
            if (numOfEffectedRows == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        return false;
    }
}



