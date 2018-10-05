package com.jntua.ui.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jntua.dao.DatabaseHandler;
import com.jntua.dao.model.CourseTL;
import com.jntua.dao.model.DeptTL;
import com.jntua.dao.model.QuestionTl;
import com.jntua.dao.model.StaffSubjectTL;
import com.jntua.dao.model.StaffTL;
import com.jntua.dao.model.StudentAnsTL;
import com.jntua.dao.model.StudentTL;
import com.jntua.dao.model.SubjectTL;
import com.jntua.dao.model.UserTL;
import com.jntua.utility.MailService;

@Controller
public class ExamController {
	 private static final String FILENAME = "h:\\minute\\hall.txt";
	@Autowired
	private DatabaseHandler db;

	@RequestMapping("login")
	public String showLogin(HttpServletRequest request) {

		return "index";

	}

	@RequestMapping("forgot")
	public String showforgot(HttpServletRequest request) {

		return "forgot";

	}

	@RequestMapping("studentchange")
	public String studentchange(HttpServletRequest request) {

		return "studentchange";

	}

	@RequestMapping("staffchange")
	public String staffchange(HttpServletRequest request) {

		return "staffchange";

	}

	@RequestMapping("logout")
	public String showLogout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content ="";

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return "redirect:login.htm";

	}

	@RequestMapping("studentexamcomplete")
	public String studentexamcomplete(HttpServletRequest request) {

		return "studentexamcomplete";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String login(LoginForm loginForm, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		if (loginForm != null && loginForm.getUsername().trim().equalsIgnoreCase("charan")
				&& loginForm.getPassword().trim().equals("charan")) {
			return "redirect:adminhome.htm";
		} else {
			Map<String, Object> m = db.getUser(loginForm.getUsername(), loginForm.getPassword());
			if (m != null) {
				HttpSession session = request.getSession();
				session.setAttribute("AuthUser", m);
				return "redirect:staffhome.htm";
			} else {
				StudentTL student = db.getStudent(loginForm.getUsername(), loginForm.getPassword());
				if (student != null) {
					HttpSession session = request.getSession();
					session.setAttribute("AuthStudent", student);
					BufferedWriter bw = null;
					FileWriter fw = null;

					try {

						String content = student.getRegNo();

						fw = new FileWriter(FILENAME);
						bw = new BufferedWriter(fw);
						bw.write(content);

						System.out.println("Done");

					} catch (IOException e) {

						e.printStackTrace();

					} finally {

						try {

							if (bw != null)
								bw.close();

							if (fw != null)
								fw.close();

						} catch (IOException ex) {

							ex.printStackTrace();

						}

					}

					return "redirect:studenthome.htm";

				} else {
					request.setAttribute("errMsg", "Invalid username/password");
				}
			}
		}

		return "index";

	}

	@RequestMapping(value = "schangepassword", method = RequestMethod.POST)
	public String schnagepassword(HttpServletRequest response, HttpServletRequest request) {

		HttpSession session = request.getSession();

		Map<String, Object> m = (Map<String, Object>) session.getAttribute("AuthUser");
		String password = request.getParameter("password");
		m.put(password, password);
		db.updateUser((Integer) m.get("userId"), password);

		return "redirect:staffhome.htm?msg=successfully changed";

	}

	@RequestMapping(value = "changepassword", method = RequestMethod.POST)
	public String chnagepassword(HttpServletRequest response, HttpServletRequest request) {

		HttpSession session = request.getSession();

		StudentTL studentTL = (StudentTL) session.getAttribute("AuthStudent");
		String password = request.getParameter("password");
		studentTL.setPassword(password);
		db.updateStudent(studentTL.getStudentId(), password);

		return "redirect:studenthome.htm?msg=successfully changed";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/forgot")
	public String forgot(LoginForm loginForm, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> m = db.getUser(loginForm.getUsername());
		if (m != null) {
			try {
				new MailService().send(m.get("emailId").toString(), "Password", m.get("password").toString());
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			StudentTL student = db.getStudent(loginForm.getUsername());
			if (student != null) {
				try {
					new MailService().send(student.getEmailId(), "Password", student.getPassword());
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				request.setAttribute("errMsg", "Invalid username");
			}
		}

		return "forgot";

	}

	@RequestMapping("adminhome")
	public String showAdminHome(HttpServletRequest request) {

		return "adminhome";

	}

	@RequestMapping("studenthome")
	public String showstudenthome(HttpServletRequest request) {

		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		//List<SubjectTL> subjects = db.getSubjects(student.getCourseId(), student.getSemester());
		List<SubjectTL> subjects = db.getSubjects();
		
		request.setAttribute("subjects", subjects);
		return "studenthome";

	}

	@RequestMapping("staffhome")
	public String showStaffHome(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Map<String, Object> m = (Map<String, Object>) session.getAttribute("AuthUser");

		Integer userId = (Integer) m.get("userId");
		List<SubjectTL> sujects = db.getStaffSubjects(userId);
		request.setAttribute("subjects", sujects);

		return "staffhome";

	}

	@RequestMapping("admindept")
	public String showAdminDept(HttpServletRequest request) {
		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "admindept";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adddept")
	public String addDept(DeptTL dept, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		db.addDept(dept);

		return "redirect:admindept.htm";

	}

	@RequestMapping("adminaddfaculty")
	public String showAddFac(HttpServletRequest request) {

		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminaddfaculty";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addfaculty")
	public String addFaculty(UserTL userTL, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		Random r = new Random();

		String alphabet = "01234567890QWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuffer password = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			password.append(alphabet.charAt(r.nextInt(alphabet.length())));
		} //
		userTL.setPassword(password.toString());

		db.addUser(userTL);

		try {
			new MailService().send(userTL.getEmailId(), "Exam",
					"your username" + userTL.getUserName() + " password" + userTL.getPassword());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:adminfaculty.htm";

	}

	@RequestMapping("adminfaculty")
	public String showFac(HttpServletRequest request) {
		List<Map<String, Object>> users = db.getAllUsers();
		request.setAttribute("users", users);

		return "adminfaculty";

	}

	@RequestMapping("admincourses")
	public String showCourses(HttpServletRequest request) {
		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);

		return "admincourses";

	}

	@RequestMapping("adminaddcourses")
	public String showAddCourse(HttpServletRequest request) {

		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminaddcourses";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adminaddcourses")
	public String addCourse(CourseTL courseTL, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {

		db.insertCourse(courseTL);
		return "redirect:admincourses.htm";

	}

	@RequestMapping("/adminaddsubject")
	public String showAddSubject(HttpServletRequest request) {

		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);
		return "adminaddsubject";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adminaddsubject")
	public String addSubject(SubjectTL subjectTL, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {

		db.addSubject(subjectTL);
		return "redirect:adminsubjects.htm";

	}

	@RequestMapping("adminsubjects.htm")
	public String showSubjects(HttpServletRequest request) {

		List<SubjectTL> subjects = db.getAllSubjects();
		request.setAttribute("subjects", subjects);

		return "adminsubjects";

	}
	@RequestMapping("adminranks.htm")
	public String adminranks(HttpServletRequest request) {

		try {
			db.getRanks();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "adminranks";

	}
	

	@RequestMapping("adminallotsubject")
	public String adminallotsubject(HttpServletRequest request) {
		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminallotsubject";

	}

	@RequestMapping("adminallotedsubjects")
	public String adminallotedsubjects(HttpServletRequest request) {
		List<DeptTL> depts = db.getAllDept();
		request.setAttribute("depts", depts);

		return "adminallotedsubjects";

	}

	@RequestMapping(value = "/adminaddstaffsubject", method = RequestMethod.POST)
	public String adminaddstaffsubject(HttpServletRequest request) {

		StaffSubjectTL staffSubjectTL = new StaffSubjectTL();
		staffSubjectTL.setStaffId(new Integer(request.getParameter("facultyId")));
		staffSubjectTL.setSubjectId(new Integer(request.getParameter("subjectId")));

		staffSubjectTL.setStatus("Active");
		db.addStaffSubject(staffSubjectTL);

		return "redirect:adminallotsubject.htm?msg=successfully alloted";

	}

	@RequestMapping("/adminaddstudent")
	public String showAddStudent(HttpServletRequest request) {

		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);
		return "adminaddstudent";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/adminaddstudent")
	public String addStudent(StudentTL studentTL, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		if(db.getStudent(studentTL.getRegNo())==null)
		{
		Random r = new Random();

		String alphabet = "01234567890QWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuffer password = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			password.append(alphabet.charAt(r.nextInt(alphabet.length())));
		} //
		studentTL.setPassword(password.toString());
		try {
			new MailService().send(studentTL.getEmailId(), "Exam",
					"your username" + studentTL.getRegNo() + " password" + studentTL.getPassword());
		} catch (Exception e) {
			// TODO: handle exception
		}

		db.addStudent(studentTL);
		return "redirect:adminstudents.htm";
		}
		else
		{
			return "redirect:adminaddstudent.htm?msg=RegNo Already Exist";
		}

	}

	@RequestMapping("adminstudents.htm")
	public String showStudent(HttpServletRequest request) {

		List<StudentTL> students = db.getAllStudents();
		request.setAttribute("students", students);

		return "adminstudents";

	}
	@RequestMapping("deletefac.htm")
	public String deletefac(HttpServletRequest request) {
		db.deleteUser(new Integer(request.getParameter("fid")));
		return "redirect:adminfaculty.htm";
		
	}
	@RequestMapping("deletedept.htm")
	public String deletedept(HttpServletRequest request) {
		db.deleteDept(new Integer(request.getParameter("did")));
		return "redirect:admindept.htm";
	}
	@RequestMapping("deletecourse.htm")
	public String deletecourse(HttpServletRequest request) {
		db.deleteCourse(new Integer(request.getParameter("cid")));
		return "redirect:admincourses.htm";
	}
	@RequestMapping("deletesubject.htm")
	public String deletesubject(HttpServletRequest request) {
		db.deleteSubject(new Integer(request.getParameter("sid")));
		return "redirect:adminsubjects.htm";
	}
	@RequestMapping("deletequestion.htm")
	public String deletequestion(HttpServletRequest request) {
		db.deleteQue(new Integer(request.getParameter("queId")));
		return "redirect:facultyviewquestions.htm";
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getsubjectsstaff")
	public @ResponseBody String getCheckStaffSubjectAgencyInfoInJSON(@RequestParam("cid") Integer courseId) {

		int subjectCount = 0;
		int staffCount = 0;
		String sb = "";
		String sf = "";
		try {
			List<SubjectTL> subjects = db.getCourseSubjects(courseId);
			List<Map<String, Object>> staff = db.getDeptUsers(courseId);
			ObjectMapper m = new ObjectMapper();
			sb = m.writeValueAsString(subjects);
			sf = m.writeValueAsString(staff);
			if (subjects != null) {
				subjectCount = subjects.size();
			}
			if (staff != null) {
				staffCount = staff.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "{\"subjectCount\":\"" + subjectCount + "\",\"subjects\":\"" + sb + "\",\"staffCount\":\"" + staffCount
				+ "\",\"subjects\":\"" + sf + "\"}";
	}

	@RequestMapping(value = "facultysubject", method = RequestMethod.POST)
	public String facultysubject(HttpServletRequest request) {
		String subjId = request.getParameter("subjId");
		SubjectTL subject = db.getSubject(new Integer(subjId));
		request.setAttribute("subject", subject);

		return "facultysubject";

	}

	@RequestMapping(value = "studentsubject", method = RequestMethod.POST)
	public String studentsubject(HttpServletRequest request) {
		String subjId = request.getParameter("subjId");
		SubjectTL subject = db.getSubject(new Integer(subjId));
		request.setAttribute("subject", subject);
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		List<Map<String, Object>> m = db.getStudentAns(new Integer(subjId), student.getStudentId());
		if (m != null && m.size() > 0) {
			request.setAttribute("already", m);
			Integer rank=db.getRank(student.getStudentId(), new Integer(subjId));
			request.setAttribute("rank", rank);
		
			List<Map<String, Object>> ma = db.getStudentAns(new Integer(subjId), student.getStudentId(),"Section A");
			
			List<Map<String, Object>> mb = db.getStudentAns(new Integer(subjId), student.getStudentId(),"Section B");
			if(mb==null||mb.size()<1)
			{
				String stype = "Section A" + "Section B";
				session.setAttribute("sec", stype);
				return ex(request, new Integer(subjId), "Section B");
			}
			List<Map<String, Object>> mc = db.getStudentAns(new Integer(subjId), student.getStudentId(),"Section C");
			if(mc==null||mc.size()<1)
			{
				String stype = "Section A" + "Section B"+"Section C";
				session.setAttribute("sec", stype);
				return ex(request, new Integer(subjId), "Section c");
			}
			
			request.setAttribute("alreadya", ma);
			request.setAttribute("alreadyb", mb);
			request.setAttribute("alreadyc", mc);
		}

		return "studentsubject";

	}

	@RequestMapping(value = "studentsubject", method = RequestMethod.GET)
	public String studentsubjects(HttpServletRequest request) {
		String subjId = request.getParameter("subjId");
		SubjectTL subject = db.getSubject(new Integer(subjId));
		request.setAttribute("subject", subject);
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		List<Map<String, Object>> m = db.getStudentAns(new Integer(subjId), student.getStudentId());
		
		if (m != null && m.size() > 0) {
			request.setAttribute("already", m);
			
			List<Map<String, Object>> ma = db.getStudentAns(new Integer(subjId), student.getStudentId(),"Section A");
			List<Map<String, Object>> mb = db.getStudentAns(new Integer(subjId), student.getStudentId(),"Section B");
			List<Map<String, Object>> mc = db.getStudentAns(new Integer(subjId), student.getStudentId(),"Section C");
			
			
			request.setAttribute("alreadya", ma);
			request.setAttribute("alreadyb", mb);
			request.setAttribute("alreadyc", mc);
		}
		

		return "studentsubject";

	}

	@RequestMapping("facultyaddquestions")
	public String facultyaddquestions(HttpServletRequest request) {
		String sid = request.getParameter("sid");

		return "facultyaddquestions";

	}

	@RequestMapping("studentexam")
	public String studentexam(HttpServletRequest request) {
		int sid = Integer.parseInt(request.getParameter("sid"));
		return ex(request, sid, "Section A");

	}

	public String ex(HttpServletRequest request, int sid, String stype) {

		List<QuestionTl> questions = db.getQuestion("Active", sid, stype);
		long seed = System.nanoTime();
		Collections.shuffle(questions, new Random(seed));
		request.setAttribute("questions", questions);
		
		SubjectTL subject = db.getSubject(sid);
		request.setAttribute("subject", subject);
		request.setAttribute("sz", questions.size());
		request.setAttribute("stype", stype);

		return "studentexam";
	}

	@RequestMapping("studentmockexam")
	public String studentmockexam(HttpServletRequest request) {
		int sid = Integer.parseInt(request.getParameter("sid"));
		List<QuestionTl> questions = db.getMockQuestion("Active", sid);
		request.setAttribute("questions", questions);
		SubjectTL subject = db.getSubject(sid);
		request.setAttribute("subject", subject);
		request.setAttribute("sz", questions.size());

		return "studenmocktexam";

	}

	@RequestMapping(value = "submitexam", method = RequestMethod.POST)
	public String submitexam(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String stype = request.getParameter("stype");
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		String sid = request.getParameter("sid");
		List<StudentAnsTL> queAns = new ArrayList<>();
		for (int index = 1; index <= 20; index++) {
			String q = request.getParameter("q" + index);
			if (q != null) {
				String qtype = request.getParameter("qtype" + index);
				String ans = "";
				if (qtype != null && qtype.equalsIgnoreCase("multiple")) {
					String[] mans = request.getParameterValues("a" + index);
					if (mans != null && mans.length > 0) {
						int indx = 0;
						for (String m : mans) {
							if (indx == 0) {
								ans += m;
							} else {
								ans += "," + m;
							}
							indx++;

						}
					} else {
						ans = null;
					}

				} else {
					ans = request.getParameter("a" + index);
				}
				StudentAnsTL studentAns = new StudentAnsTL();
				studentAns.setAns(ans);
				studentAns.setCreatedon(new Timestamp(new java.util.Date().getTime()));
				studentAns.setQid(new Integer(q));
				studentAns.setSid(new Integer(sid));
				studentAns.setStudentId(student.getStudentId());

				queAns.add(studentAns);

			}

		}
		db.addStudentAns(queAns);
		if (session.getAttribute("sec") == null) {

			session.setAttribute("sec", stype);

		}
		String sec = session.getAttribute("sec").toString();
		System.out.println(sec);
		if (sec.equalsIgnoreCase("Section A")) {
			stype = stype + "Section B";
			session.setAttribute("sec", stype);
			return ex(request, new Integer(sid), "Section B");

		} else if (sec.equalsIgnoreCase("Section ASection B")) {
			stype = stype + "Section C";
			session.setAttribute("sec", stype);
			return ex(request, new Integer(sid), "Section C");
		} else {

			session.removeAttribute("sec");
			return "redirect:studentsubject.htm?subjId=" + sid;
		}

	}

	@RequestMapping(value = "submitmockexam", method = RequestMethod.POST)
	public String submockmitexam(HttpServletRequest request) {
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		String sid = request.getParameter("sid");
		/*
		 * List<StudentAnsTL> queAns = new ArrayList<>(); for (int index = 1;
		 * index <= 20; index++) { String q = request.getParameter("q" + index);
		 * if (q != null) { String qtype = request.getParameter("qtype" +
		 * index); String ans = ""; if (qtype != null &&
		 * qtype.equalsIgnoreCase("multiple")) { String[] mans =
		 * request.getParameterValues("a" + index); if (mans != null &&
		 * mans.length > 0) { for (String m : mans) { ans += m; } }
		 * 
		 * } else { ans = request.getParameter("a" + index); } StudentAnsTL
		 * studentAns = new StudentAnsTL(); studentAns.setAns(ans);
		 * studentAns.setCreatedon(new Timestamp(new
		 * java.util.Date().getTime())); studentAns.setQid(new Integer(q));
		 * studentAns.setSid(new Integer(sid));
		 * studentAns.setStudentId(student.getStudentId());
		 * 
		 * queAns.add(studentAns);
		 * 
		 * }
		 * 
		 * } db.addStudentMockAns(queAns);
		 * 
		 * 
		 * SubjectTL subject = db.getSubject(new Integer(sid));
		 * request.setAttribute("subject", subject);
		 * 
		 * List<Map<String, Object>> m=db.getStudentMockAns( new
		 * Integer(sid),student.getStudentId()); if(m!=null&&m.size()>0) {
		 * request.setAttribute("already", m); }
		 * 
		 * db.updateStudentMockAns(student.getStudentId(), "DeActive");
		 */

		List<QuestionTl> questions = db.getMockQuestion("Active", new Integer(sid));
		request.setAttribute("questions", questions);
		SubjectTL subject = db.getSubject(new Integer(sid));
		request.setAttribute("subject", subject);
		request.setAttribute("sz", questions.size());

		return "mockresult";

	}

	@RequestMapping("studentresult")
	public String studentresult(HttpServletRequest request) {
		String sid = request.getParameter("sid");
		List<QuestionTl> questions = db.getQuestion("Active", new Integer(sid));
		HttpSession session = request.getSession();
		StudentTL student = (StudentTL) session.getAttribute("AuthStudent");
		// List<StudentAnsTL> studentAns=db.getStudentAns(new Integer(sid),
		// student.getStudentId());
		request.setAttribute("questions", questions);
		// request.setAttribute("studentAns", studentAns);

		return "studentresult";

	}

	@RequestMapping("facultyviewquestions")
	public String facultyviewquestions(HttpServletRequest request) {
		int sid = Integer.parseInt(request.getParameter("sid"));

		List<QuestionTl> questions = db.getQuestion("Active", sid);
		request.setAttribute("questions", questions);
		SubjectTL subject = db.getSubject(sid);
		request.setAttribute("subject", subject);

		return "facultyviewquestions";

	}

	@RequestMapping("vq")
	public String vq(HttpServletRequest request) {
		int qid = Integer.parseInt(request.getParameter("qid"));

		QuestionTl question = db.getQuestion(qid);
		request.setAttribute("question", question);

		return "vq";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/facultyaddquestions")
	public String addFacultyAddQuestions(QuestionTl questionTl, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {

		db.addQuestion(questionTl);
		return "redirect:facultyaddquestions.htm?sid=" + questionTl.getSid();

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getdeptfaculty")
	public @ResponseBody String getCheckAgencyDetailsJSON(@RequestParam("deptId") Integer deptId) {
		String result = null;

		ObjectMapper mapper = new ObjectMapper();

		List<Map<String, Object>> users = db.getDeptFac(deptId);
		List<Map<String, Object>> courses = db.getDeptCourses(deptId);

		Map<String, Object> m = new HashMap<>();
		m.put("users", users);
		m.put("courses", courses);

		if (users != null) {
			try {

				result = mapper.writeValueAsString(m);
			} catch (JsonGenerationException e) {

				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getcoursesubject")
	public @ResponseBody String getCheckSubjectyDetailsJSON(@RequestParam("courseId") Integer courseId,
			@RequestParam("sem") String sem) {
		String result = null;
		System.out.println(courseId + " " + sem);

		ObjectMapper mapper = new ObjectMapper();

		List<SubjectTL> subjects = db.getSubjects(courseId, sem);

		if (subjects != null) {
			try {

				result = mapper.writeValueAsString(subjects);
			} catch (JsonGenerationException e) {

				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getfacsubject")
	public @ResponseBody String getfacsubject(@RequestParam("courseId") Integer courseId,
			@RequestParam("sem") String sem, @RequestParam("facultyId") Integer facultyId) {
		String result = null;
		System.out.println(courseId + " " + sem);

		ObjectMapper mapper = new ObjectMapper();

		List<SubjectTL> subjects = db.getSubjects(courseId, sem);

		if (subjects != null) {
			try {

				result = mapper.writeValueAsString(subjects);
			} catch (JsonGenerationException e) {

				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			result = "fail";
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/postexampattern")
	public @ResponseBody String postexampattern(HttpServletRequest request) {
		System.out.println(request.getParameter("subjectId"));

		String qpattern = request.getParameter("qpattern");
		Integer subjectId = new Integer(request.getParameter("subjectId"));

		db.updateSubjectExamPattern(qpattern, subjectId);
		System.out.println("ex");

		return "{s:'success'";
	}

	@RequestMapping("/studentregs")
	public String showRegStudent(HttpServletRequest request) {

		List<Map<String, Object>> courses = db.getAllCourses();
		request.setAttribute("courses", courses);
		return "studentreg";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/studentregs")
	public String regStudent(StudentTL studentTL, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		Random r = new Random();

		String alphabet = "01234567890QWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuffer password = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			password.append(alphabet.charAt(r.nextInt(alphabet.length())));
		} //
		studentTL.setPassword(password.toString());
		try {
			new MailService().send(studentTL.getEmailId(), "Exam",
					"your username" + studentTL.getRegNo() + " password" + studentTL.getPassword());
		} catch (Exception e) {
			// TODO: handle exception
		}

		db.addStudent(studentTL);
		return "redirect:login.htm?msg=Your successfully registered";

	}
	

}
