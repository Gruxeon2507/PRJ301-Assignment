-- View Weekly Time Table From 20/02/2023 to 26/02/2023 of lecturer SonNT5
SET DATEFORMAT dmy 
SELECT i.instructorId, ses.date, ses.slotId, t.startTime, t.endTime, g.courseId, g.groupName, ses.roomId  
FROM Instructor i  JOIN [Session] ses ON i.instructorId = ses.lecturerId
				   JOIN [Group] g ON g.groupId = ses.groupId 
				   JOIN TimeSlot t ON ses.slotId = t.slotId
WHERE i.instructorId = 'sonnt5'
AND ses.date BETWEEN '20/02/2023' AND '26/02/2023' ORDER BY ses.date

--	Attendance for PRJ301 SE1723 with lecturer SonNT5 at slot 3 on Friday 02/03/2023.
SELECT c.groupName [Group], c.studentId Code, c.studentName [Name], c.studentImage [Image],
	   att.[status] [Status], att.comment Comment, ses.lecturerId Taker, 
	   att.recordTime AS [Record Time], ses.roomId, c.courseId
FROM (SELECT s.studentId, s.studentName, s.studentImage, g.groupId, g.groupName, g.courseId
			FROM Student s JOIN Participate p ON s.studentId = p.studentId
			JOIN [Group] g ON p.groupId = g.groupId WHERE g.groupId = 15 ) as c 
JOIN [Session] ses ON ses.groupId = c.groupId 
JOIN Attend att ON att.studentId = c.studentId  AND ses.sessionId = att.sessionId  
WHERE ses.date = '02/03/2023'

--  Get Attendance Report For Class SE1723 in PRJ301 Course
SELECT att.studentId,st.studentImage,st.studentName, [status], att.recordTime,att.comment,s.lecturerId
FROM Attend att join [Session] s on att.sessionId=s.sessionId
	join [Group] g on s.groupId = g.groupId
	join student st on s.groupId=g.groupId
where s.lecturerId = 'sonnt5' and g.courseId = 'PRJ301' and g.groupName='SE1723'