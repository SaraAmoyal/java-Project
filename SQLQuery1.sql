create database seajava
go
use seajava
go


create table teamMembers(
dmCode int primary key,
dmName varchar(20),
dmMail varchar(20),
grading int
)

alter table teamMembers add constraint limitCode check (dmCode between 100 and 4194303);

create table tasks(
taskCode int primary key,
taskTitle varchar(30),
taskDesc varchar(100),
taskStatus int,
taskBelongsTo int ,
taskDays int,
startDate datetime,
endDate datetime
)

create table statuses(
statusCode int primary key,
statusName varchar(15)
)




insert into tasks (taskCode, taskTitle, taskDesc, taskStatus, taskBelongsTo, taskDays, startDate, endDate) values(1234, 'task a', 'desc task a', 2, 206, 3, CONVERT(DATETIME, '2020-03-05', 102), CONVERT(DATETIME, '2020-03-06', 102))
insert into tasks (taskCode, taskTitle, taskDesc, taskStatus, taskBelongsTo, taskDays, startDate, endDate) values(2345, 'task b', 'desc task b', 3, 207, 4, CONVERT(DATETIME, '2020-09-09', 102), null)
insert into tasks (taskCode, taskTitle, taskDesc, taskStatus, taskBelongsTo, taskDays, startDate, endDate) values(3456, 'task c', 'desc task c', 1, 208, 3, CONVERT(DATETIME, '2020-03-05', 102), null)



insert into teamMembers (dmCode, dmName,dmMail,grading) values(206,'SARA','bbb@gmail',12)
insert into teamMembers (dmCode, dmName,dmMail,grading) values(207,'AVITAL','ccc@gmail',13)
insert into teamMembers (dmCode, dmName,dmMail,grading) values(208,'EFRAT','ddd@gmail',14) 
 
 

 insert into statuses (statusCode,statusName) values(1, 'NEW')
 insert into statuses (statusCode,statusName) values(2, 'DONE')
 insert into statuses (statusCode,statusName) values(3,'INPROGRESS')
 
 
 
 