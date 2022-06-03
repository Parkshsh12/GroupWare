package mvc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import mvc.model.BoardDAO;
import mvc.model.BoardDTO;
import mvc.model.CalendarDAO;
import mvc.model.CalendarDTO;
import mvc.model.CommuteDAO;
import mvc.model.CompanyDAO;
import mvc.model.CompanyDTO;
import mvc.model.MemberDAO;
import mvc.model.MemberDTO;
import mvc.model.NoticeDAO;
import mvc.model.NoticeDTO;
import mvc.model.PStableDAO;
import mvc.model.PStableDTO;
import mvc.model.PaymentDAO;
import mvc.model.PaymentDTO;

public class MvcController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int listCount = 5;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		if (command.equals("/home.do")) {
			requestAllCalendar(request);
			requestInfoView(request);
			requestBoardList(request);
			requestNoticeList(request);
			requestCommuteChk(request);
			RequestDispatcher rd = request.getRequestDispatcher("./home.jsp?id=홈화면");
			rd.forward(request, response);
		}
		// 로그인
		else if (command.equals("/logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("./home_login.jsp");
			rd.forward(request, response);
		} else if (command.equals("/LoginAction.do")) {
			requestAllCalendar(request);
			requestNoticeList(request);
			requestBoardList(request);
			requestLoginMember(request);
			requestCommuteChk(request);
			HttpSession session = request.getSession(true);
			String name = (String) request.getAttribute("name");
			if (name != null) {
				String number = (String) request.getAttribute("number");
				String type = (String) request.getAttribute("type");
				session.setAttribute("name", name);
				session.setAttribute("number", number);
				session.setAttribute("type", type);
				RequestDispatcher rd = request.getRequestDispatcher("/home.do");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("./home_login.jsp?error=1");
				rd.forward(request, response);
			}
		} else if (command.equals("/searchId.do")) {
			requestSearchId(request);

		} else if (command.equals("/id_search.do")) {

			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_list.jsp?id=임직원 관리");
			rd.forward(request, response);
		}
		
		// mskim login
		
		else if(command.equals("/start_time.do")) {
			requestChkCommute(request);
			RequestDispatcher rd = request.getRequestDispatcher("/home.do?id=홈화면");
			rd.forward(request, response);
		} else if(command.equals("/end_time.do")) {
			endCommute(request);
			RequestDispatcher rd = request.getRequestDispatcher("/home.do?id=홈화면");
			rd.forward(request, response);
		}
		
		// 인사관리
		else if (command.equals("/member_list.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_list.jsp?id=임직원 관리");
			rd.forward(request, response);
		} else if (command.equals("/member_registration.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_registration.jsp");
			rd.forward(request, response);
		} else if (command.equals("/member_update.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_update.jsp");
			rd.forward(request, response);
		} else if (command.equals("/attendance_admin.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/attendance_admin.jsp?id=근태 관리");
			rd.forward(request, response);
		} else if (command.equals("/attendance_month.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/attendance_month.jsp");
			rd.forward(request, response);
		}
		// 영업 관리
		else if (command.equals("/business_company.do")) {
			requestCompanyList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/business/business_company.jsp?id=거래처 조회");
			rd.forward(request, response);
		} else if (command.equals("/business_companyAdd.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/business/business_companyAdd.jsp?id=거래처 등록");
			rd.forward(request, response);
		} else if (command.equals("/companyAddAction.do")) {
			requestCompanyAdd(request);
			RequestDispatcher rd = request.getRequestDispatcher("/business_company.do");
			rd.forward(request, response);
		} else if (command.equals("/companyUpdate.do")) {
			companyUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/business/business_companyUpdate.jsp?id=거래처 수정");
			rd.forward(request, response);
		} else if (command.equals("/business_search.do")) {
			String division = request.getParameter("division");
			PStableMain(request);
			businessSearch(request);
			RequestDispatcher rd;
			System.out.println(division);
			if(request.getParameter("division").equals("f")) {
				rd = request.getRequestDispatcher("./list/business/business_search_file.jsp");
			}else {
				rd = request.getRequestDispatcher("./list/business/business_search.jsp?id=월별 매출 조회");
			}
			rd.forward(request, response);
		} else if (command.equals("/psmenu_main.do")) {
			PStableMain(request);
			String division = (String) request.getAttribute("division");
			RequestDispatcher rd;
			if (division.equals("p")) {
				rd = request.getRequestDispatcher("./list/business/ps_main.jsp?id=매입 관리");
			} else {
				rd = request.getRequestDispatcher("./list/business/ps_main.jsp?id=매출 관리");
			}
			rd.forward(request, response);
		} else if (command.equals("/company_delete.do")) {
			companyDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/business_company.do");
			rd.forward(request, response);
		} else if (command.equals("/companyUpdateAction.do")) {
			companyUpdateAction(request);
			RequestDispatcher rd = request.getRequestDispatcher("/business_company.do");
			rd.forward(request, response);
		} else if (command.equals("/psadd.do")) {
			String division = request.getParameter("division");
			requestCompanyNameList(request);
			RequestDispatcher rd;
			if (division.equals("p")) {
				rd = request.getRequestDispatcher("/list/business/ps_add.jsp?id=매입 등록&division=p");
			} else {
				rd = request.getRequestDispatcher("/list/business/ps_add.jsp?id=매출 등록&division=s");
			}
			rd.forward(request, response);
		} else if (command.equals("/ps_addAction.do")) {
			setPsAdd(request);
			String pageNum = request.getParameter("pageNum");
			String division = (String) request.getAttribute("division");
			RequestDispatcher rd = request.getRequestDispatcher("/psmenu_main.do?pageNum=" + pageNum + "&division=" + division);
			rd.forward(request, response);
		} else if(command.equals("/psUpdate.do")) {
			psUpdate(request);
			String division = (String) request.getAttribute("division");
			RequestDispatcher rd;
			if(division.equals("p")) {
				rd = request.getRequestDispatcher("/list/business/ps_update.jsp?id=매입품 수정");				
			} else {
				rd = request.getRequestDispatcher("/list/business/ps_update.jsp?id=매출품 수정");
			}
			rd.forward(request, response);
		} else if(command.equals("/psupdateAction.do")) {
			String division = request.getParameter("division");
			setpsUpdate(request);
			RequestDispatcher rd;
			if (division.equals("p")) {
				rd = request.getRequestDispatcher("/psmenu_main.do?division=p");
			} else {
				rd = request.getRequestDispatcher("/psmenu_main.do?division=s");
			}
			rd.forward(request, response);
		}
		// 공지 게시판
		else if (command.equals("/notice_main.do")) {
			requestNoticeList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_main.jsp?id=공지사항");
			rd.forward(request, response);
		} else if (command.equals("/notice_add.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_add.jsp?id=공지사항 등록");
			rd.forward(request, response);
		} else if (command.equals("/notice_add_submit.do")) {
			requestNoticeAdd(request);
			requestNoticeList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_main.jsp?id=공지사항");
			rd.forward(request, response);
		} else if (command.equals("/noticeView.do")) {
			requestNoticeView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_noticeNum.jsp?id=공지사항");
			rd.forward(request, response);
		} else if (command.equals("/notice_update.do")) {
			int seq = (Integer.parseInt(request.getParameter("seq")));
			request.setAttribute("seq", seq);
			requestNoticeView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_update.jsp?id=공지사항 수정");
			rd.forward(request, response);
		} else if (command.equals("/notice_update_submit.do")) {
			requestSetUpdate(request);
			requestNoticeView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_noticeNum.jsp?id=공지사항");
			rd.forward(request, response);
		} else if (command.equals("/notice_delete.do")) {
			noticeDelete(request);
			requestNoticeList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_main.jsp?id=공지사항");
			rd.forward(request, response);
		}
		// 자유 게시판
		else if (command.equals("/board_main.do")) {
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/board_main.jsp?id=자유게시판");
			rd.forward(request, response);
		} else if (command.equals("/board_add.do")) {
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/board_add.jsp?id=자유게시판 등록");
			rd.forward(request, response);
		} else if (command.equals("/boardAddAction.do")) {
			requestBoardWrite(request);
			RequestDispatcher rd = request.getRequestDispatcher("/board_main.do");
			rd.forward(request, response);
		} else if (command.equals("/BoardViewAction.do")) {
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/board_boardNum.jsp?id=자유게시판");
			rd.forward(request, response);
		} else if (command.equals("/board_updateAction.do")) {
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/./list/board/board_update.jsp?id=자유게시판 수정");
			rd.forward(request, response);
		} else if (command.equals("/board_update.do")) {
			requestBoardUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardViewAction.do");
			rd.forward(request, response);
		} else if (command.equals("/board_delete.do")) {
			requestBoardDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/board_main.do");
			rd.forward(request, response);
		}
		// 캘린더
		else if (command.equals("/scheduleAllAction.do")) {
			requestAllCalendar(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/schedule_all.jsp?id=전체 일정");
			rd.forward(request, response);
		} else if (command.equals("/scheduleAllDetail.do")) {
			requestCalendarDetail(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/schedule_all_detail.jsp?id=상세 일정");
			rd.forward(request, response);
		} else if (command.equals("/scheduleAllAdd.do")) {
			requestInfoView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/allschedule_add.jsp?id=일정 추가");
			rd.forward(request, response);
		} else if (command.equals("/scheduleAddAllAction.do")) {
			requestAddSchedule(request);
			RequestDispatcher rd = request.getRequestDispatcher("/scheduleAllAction.do");
			rd.forward(request, response);
		} else if (command.equals("/scheduleDepAction.do")) {
			requestInfoView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/schedule_dep.do");
			rd.forward(request, response);
		} else if (command.equals("/schedule_dep.do")) {
			requestCalendarView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/schedule_dep.jsp?id=부서 일정");
			rd.forward(request, response);
		} else if (command.equals("/scheduleDepAdd.do")) {
			requestInfoView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/depshedule_add.jsp?id=일정 추가");
			rd.forward(request, response);
		} else if (command.equals("/scheduleAddDepAction.do")) {
			requestInfoView(request);
			requestAddSchedule(request);
			RequestDispatcher rd = request.getRequestDispatcher("/schedule_dep.do");
			rd.forward(request, response);
		} else if (command.equals("/scheduleDetail.do")) {
			requestCalendarDetail(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/schedule_content.jsp?id=상세 일정");
			rd.forward(request, response);
		} else if (command.equals("/scheduleDepBack.do")) {
			requestInfoView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/schedule_dep.do");
			rd.forward(request, response);
		} else if (command.equals("/scheduleDelete.do")) {
			requestDeleteSchedule(request);
			RequestDispatcher rd = request.getRequestDispatcher("/schedule_dep.do");
			rd.forward(request, response);
		} else if (command.equals("/scheduleHomeDetail.do")) {
			requestCalendarDetail(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/schedule_home_detail.jsp?id=상세 일정");
			rd.forward(request, response);
		}
		// 주소록
		else if (command.equals("/contact.do")) {
			requestGetMember(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/contact/contact.jsp?id=주소록");
			rd.forward(request, response);
		// 마이페이지
		} else if (command.equals("/attendance_user.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/attendance_user.jsp?id=근태 조회");
			rd.forward(request, response);
		} else if (command.equals("/my_information.do")) {
			requestInfoView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/my_information.jsp?id=내 정보 관리&msg=1");
			rd.forward(request, response);
		} else if (command.equals("/my_informationChk.do")) {
			requestInfoView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/my_information.jsp?id=내 정보 관리&msg=0");
			rd.forward(request, response);
		} else if (command.equals("/manager_pay.do")) {
			requestInfoView(request);
			requestPayList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/manager_pay.jsp?id=급여 관리");
			rd.forward(request, response);
		} else if (command.equals("/update_information.do")) {
			requestInfoView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/update_information.jsp?id=내 정보 수정");
			rd.forward(request, response);
		} else if (command.equals("/updateInfoAction.do")) {
			requestInfoUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/my_informationChk.do");
			rd.forward(request, response);
		}
		/* 로그인 영역 */

		/* 로그인 */

	}

	/* 영업관리 영역 */
	public void requestCompanyList(HttpServletRequest request) {
		CompanyDAO dao = CompanyDAO.getInstance();
		ArrayList<CompanyDTO> companylist = new ArrayList<CompanyDTO>();

		String search_item = request.getParameter("search_item");
		String text = request.getParameter("text");

		companylist = dao.getAllCompanyList(search_item, text);

		request.setAttribute("companylist", companylist);
	}

	public void requestCompanyAdd(HttpServletRequest request) {
		CompanyDTO company = new CompanyDTO();
		CompanyDAO dao = CompanyDAO.getInstance();

		String p_companyNum = request.getParameter("p_companyNum1") + "-" + request.getParameter("p_companyNum2") + "-"
				+ request.getParameter("p_companyNum3");
		String p_personNum = request.getParameter("p_personNum1") + "-" + request.getParameter("p_personNum2") + "-"
				+ request.getParameter("p_personNum3");

		company.setP_company(request.getParameter("p_company"));
		company.setP_industry(request.getParameter("p_industry"));
		company.setP_address(request.getParameter("p_address"));
		company.setP_companyNum(p_companyNum);
		company.setP_person(request.getParameter("p_person"));
		company.setP_personNum(p_personNum);

		dao.CompanyAdd(company);
	}

	public void companyUpdate(HttpServletRequest request) {
		CompanyDAO dao = CompanyDAO.getInstance();
		CompanyDTO dto = new CompanyDTO();

		dto = dao.getCompany(request.getParameter("seq"));

		request.setAttribute("p_company", dto.getP_company());
		request.setAttribute("p_industry", dto.getP_industry());
		request.setAttribute("p_address", dto.getP_address());
		request.setAttribute("p_person", dto.getP_person());
		request.setAttribute("p_companyNum", dto.getP_companyNum());
		request.setAttribute("p_personNum", dto.getP_personNum());
		request.setAttribute("seq", dto.getSeq());
	}

	public void companyDelete(HttpServletRequest request) {
		CompanyDAO dao = CompanyDAO.getInstance();
		int seq = Integer.parseInt(request.getParameter("seq"));
		dao.deleteCompany(seq);
		System.out.println("딜리트되냐");
	}

	public void companyUpdateAction(HttpServletRequest request) {
		CompanyDAO dao = CompanyDAO.getInstance();
		CompanyDTO dto = new CompanyDTO();
		int seq = Integer.parseInt(request.getParameter("seq"));

		String p_companyNum = request.getParameter("p_companyNum1") + "-" + request.getParameter("p_companyNum2") + "-"
				+ request.getParameter("p_companyNum3");
		String p_personNum = request.getParameter("p_personNum1") + "-" + request.getParameter("p_personNum2") + "-"
				+ request.getParameter("p_personNum3");

		dto.setP_industry(request.getParameter("p_industry"));
		dto.setP_address(request.getParameter("p_address"));
		dto.setP_person(request.getParameter("p_person"));
		dto.setP_companyNum(p_companyNum);
		dto.setP_personNum(p_personNum);
		dto.setSeq(Integer.parseInt(request.getParameter("seq")));

		dao.companyUpdateAction(dto);
	}

	public void PStableMain(HttpServletRequest request) {
		String division = request.getParameter("division");
		PStableDAO dao = PStableDAO.getInstance();
		ArrayList<PStableDTO> list = new ArrayList<PStableDTO>();
		int pageNum = 1;
		String search_item = "";
		String text = "";
		
		if(request.getParameter("pageNum") != null) {
			search_item = request.getParameter("search_item");
			text = request.getParameter("text");
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		String company = request.getParameter("company");
		list = dao.getPStableList(list, division, company, search_item, text, pageNum);
		
		int total_record = dao.getPStableCount(search_item, text, division);
		int total_page;
		
		if (total_record % 5 == 0) {
			total_page = total_record / 5;
		} else {
			total_page = total_record / 5;
			total_page++;
		}
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search_item", search_item);
		request.setAttribute("text", text);
		request.setAttribute("total_page", total_page);
		request.setAttribute("company", company);
		request.setAttribute("list", list);
		request.setAttribute("division", division);
	}

	public void businessSearch(HttpServletRequest request) {
		PStableDAO dao = PStableDAO.getInstance();
		ArrayList list = (ArrayList) request.getAttribute("list");
		ArrayList Array = null;
		String year = request.getParameter("year");
		Array = dao.getBusinessList(list,year);

		request.setAttribute("year", year);
		request.setAttribute("Array", Array);
	}
	
	public void requestCompanyNameList(HttpServletRequest request) {
		PStableDAO dao = PStableDAO.getInstance();
		String[] list = null;
		list = dao.getCompanyList(list);
		request.setAttribute("list", list);
	}

	public void setPsAdd(HttpServletRequest request) {
		PStableDAO dao = PStableDAO.getInstance();
		PStableDTO dto = new PStableDTO();

		dto.setDivision(request.getParameter("division"));
		dto.setCompany(request.getParameter("company"));
		dto.setDate(request.getParameter("date"));
		dto.setCategory(request.getParameter("category"));
		dto.setQty(Integer.parseInt(request.getParameter("qty")));
		dto.setUnit(request.getParameter("unit"));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dto.setBecause(request.getParameter("because"));
		dto.setName(request.getParameter("name"));

		dao.setPStable(dto);

		request.setAttribute("division", dto.getDivision());
	}
	
	public void psUpdate(HttpServletRequest request) {
		PStableDAO dao = PStableDAO.getInstance();
		PStableDTO dto = new PStableDTO();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		dto = dao.getpsUpdate(dto, seq);
		request.setAttribute("division", dto.getDivision());
		request.setAttribute("dto", dto);
		request.setAttribute("seq", dto.getSeq());
	}
	
	public void setpsUpdate(HttpServletRequest request) {
		PStableDAO dao = PStableDAO.getInstance();
		PStableDTO dto = new PStableDTO();
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		dto.setCompany(request.getParameter("company"));
		dto.setName(request.getParameter("name"));
		dto.setDate(request.getParameter("date"));
		dto.setCategory(request.getParameter("category"));
		dto.setQty(Integer.parseInt(request.getParameter("qty")));
		dto.setUnit(request.getParameter("unit"));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dto.setBecause(request.getParameter("because"));
		dto.setDivision(request.getParameter("division"));
		
		dao.setupdate(dto, seq);
	}
	

	/* 공지사항 list 가져오기 */
	public void requestNoticeList(HttpServletRequest request) {

		NoticeDAO dao = NoticeDAO.getInstance();
		ArrayList<NoticeDTO> noticelist = new ArrayList<NoticeDTO>();

		int pageNum = 1;
		int limit = listCount;

		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		String search_item = request.getParameter("search_item");
		String text = request.getParameter("text");

		int total_record = dao.getAllNoticeListCount(search_item, text);
		noticelist = dao.getAllNoticeList(pageNum, limit, search_item, text);

		int total_page;

		if (total_record % limit == 0) {
			total_page = total_record / limit;
		} else {
			total_page = total_record / limit;
			total_page++;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		request.setAttribute("noticelist", noticelist);
		request.setAttribute("search_item", search_item);
		request.setAttribute("text", text);
	}

	public void requestNoticeView(HttpServletRequest request) {
		NoticeDAO dao = NoticeDAO.getInstance();

		int seq = Integer.parseInt(request.getParameter("seq"));
		request.setAttribute("seq", seq);
		seq = ((Integer) request.getAttribute("seq")).intValue();

		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		NoticeDTO notice = dao.getNoticeNum(seq, pageNum);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("noticeView", notice);
		request.setAttribute("select_item", request.getParameter("select_item"));
		request.setAttribute("text", request.getParameter("text"));

	}

	public void requestNoticeAdd(HttpServletRequest request) {
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeDTO notice = new NoticeDTO();

		notice.setName(request.getParameter("name"));
		notice.setNumber(request.getParameter("number"));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));

		dao.setNoticeAdd(notice);

		int seq = noticeTotalList();

		request.setAttribute("pageNum", 1);
		request.setAttribute("seq", seq);
	}

	public int noticeTotalList() {
		NoticeDAO dao = NoticeDAO.getInstance();
		return dao.getNoticeNext();
	}

	public void requestSetUpdate(HttpServletRequest request) {
		NoticeDAO dao = NoticeDAO.getInstance();
		int seq = (Integer.parseInt(request.getParameter("seq")));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		request.setAttribute("seq", seq);
		dao.updateNoticeSubmit(seq, title, content);
	}

	public void noticeDelete(HttpServletRequest request) {
		NoticeDAO dao = NoticeDAO.getInstance();
		int seq = Integer.parseInt(request.getParameter("seq"));

		dao.setNoticeDelete(seq);
	}

	/* mskim end */

	/* 로그인 function */
	public void requestLoginMember(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();

		String[] result = dao.loginMember(request.getParameter("number"), request.getParameter("pw"));
		request.setAttribute("number", result[0]);
		request.setAttribute("name", result[1]);
		request.setAttribute("type", result[2]);
	}

	/* 아이디 찾기-2 */
	public void requestSearchId(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String type = "SearchPw";
		String pw = dao.SearchIdPw(number, name, email, type);

	}

	/* 직원추가 */
	public void requestMember_update(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();

		String number = request.getParameter("number");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String position = request.getParameter("position");
		String department = request.getParameter("department");
		String email = request.getParameter("email");
		int join_date = (int) request.getAttribute("join_date");
	}

	// sh board start

	public void requestBoardList(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();

		int pageNum = 1;
		int limit = listCount;

		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		String search_item = request.getParameter("search_item");
		String text = request.getParameter("text");

		int total_record = dao.getallBoardListCount(search_item, text);
		boardList = dao.getallBoardList(pageNum, limit, search_item, text);

		int total_page;

		if (total_record % limit == 0) {
			total_page = total_record / limit;
		} else {
			total_page = total_record / limit;
			total_page = total_page + 1;
		}

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("boardList", boardList);
		request.setAttribute("total_record", total_record);
		request.setAttribute("search_item", search_item);
		request.setAttribute("text", text);
	}

	public void requestBoardWrite(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();

		dto.setName(request.getParameter("name"));
		dto.setNumber(request.getParameter("number"));
		dto.setContent(request.getParameter("content"));
		dto.setTitle(request.getParameter("subject"));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String board_date = formatter.format(new java.util.Date());

		dto.setHit(0);
		dto.setB_date(board_date);
		dao.insertBoard(dto);
	}

	public void requestLoginName(HttpServletRequest request) {
		String number = request.getParameter("number");
		BoardDAO dao = BoardDAO.getInstance();

		String name = dao.getLoginNameByNumber(number);

		request.setAttribute("name", name);
	}

	public void requestBoardView(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num);

		request.setAttribute("num", num);
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);

	}

	public void requestBoardUpdate(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();
		int num = Integer.parseInt(request.getParameter("num"));

		dto.setSeq(num);
		dto.setTitle(request.getParameter("subject"));
		dto.setName(request.getParameter("name"));
		dto.setContent(request.getParameter("content"));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String board_date = formatter.format(new java.util.Date());
		dto.setB_date(board_date);
		dto.setHit(0);
		dao.updateBoard(dto);
	}

	public void requestBoardDelete(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
		dao.deleteBoard(num);
	}

	public void requestInfoView(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
		String number = request.getParameter("number");
		MemberDTO dto = new MemberDTO();
		dto = dao.getMemberById(number);
		request.setAttribute("member", dto);
		request.setAttribute("number", number);
	}

	public void requestInfoUpdate(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		dto.setNumber(request.getParameter("number"));
		dto.setPw(request.getParameter("password"));
		dto.setAddress(request.getParameter("address"));
		dto.setEmail(request.getParameter("email"));
		String phone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3");
		dto.setPhone(phone);
		dao.updateInfo(dto);
	}

	public void requestCalendarView(HttpServletRequest request) {
		CalendarDAO dao = CalendarDAO.getInstance();
		MemberDTO member = (MemberDTO) request.getAttribute("member");
		String department = member.getDepartment();
		ArrayList<CalendarDTO> calendarList = new ArrayList<CalendarDTO>();
		calendarList = dao.getCalendarContent(department);
		request.setAttribute("calendarList", calendarList);
	}

	public void requestAddSchedule(HttpServletRequest request) {
		CalendarDTO dto = new CalendarDTO();
		CalendarDAO dao = CalendarDAO.getInstance();

		dto.setNumber(request.getParameter("number"));
		dto.setName(request.getParameter("name"));
		dto.setC_content(request.getParameter("c_content"));
		dto.setC_title(request.getParameter("c_title"));
		dto.setDepartment(request.getParameter("department"));
		dto.setStart_date(request.getParameter("start_date"));
		dto.setEnd_date(request.getParameter("end_date"));

		dao.insertDepSchedule(dto);
	}

	public void requestCalendarDetail(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		CalendarDAO dao = CalendarDAO.getInstance();
		CalendarDTO dto = new CalendarDTO();
		dto = dao.getCalendarSeq(seq);
		request.setAttribute("schedule", dto);
	}

	public void requestDeleteSchedule(HttpServletRequest request) {
		CalendarDAO dao = CalendarDAO.getInstance();
		MemberDAO dao2 = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		String number = request.getParameter("number");
		int seq = Integer.parseInt(request.getParameter("seq"));
		dto = dao2.getMemberById(number);
		request.setAttribute("member", dto);
		dao.deleteSchedule(seq);
	}

	public void requestAllCalendar(HttpServletRequest request) {
		CalendarDAO dao = CalendarDAO.getInstance();
		ArrayList<CalendarDTO> calendarList = new ArrayList<CalendarDTO>();
		calendarList = dao.getAllCalendar();
		request.setAttribute("calendarAllList", calendarList);
	}
	// sh board end
	
	//sh 급여관리
	
	public void requestPayList(HttpServletRequest request) {
		int pageNum = 1;
		int limit = listCount;
		PaymentDAO dao = PaymentDAO.getInstance();
		ArrayList<PaymentDTO> paymentList = new ArrayList<PaymentDTO>();
		String number = request.getParameter("number");
		
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		int total_record = dao.getAllpaymentCount(number);
		paymentList = dao.getPaymentList(pageNum, limit, number);
		
		int total_page;
		if(total_record % limit == 0) {
			total_page = total_record / limit;
		} else {
			total_page = total_record / limit;
			total_page = total_page + 1;
		}

		request.setAttribute("paymentList", paymentList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
	}
	//sh 급여 끝
	
	public void requestChkCommute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String number = (String)session.getAttribute("number");
		CommuteDAO dao = CommuteDAO.getInstance();
		boolean chk = dao.chkCommute(number);
		if(chk == false) {
			dao.newStartCommute(number);
		} else {
			dao.updateStartCommute(number);
		}
		request.setAttribute("chk", chk);
	}
	
	public void endCommute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String number = (String)session.getAttribute("number");
		CommuteDAO dao = CommuteDAO.getInstance();
		
		dao.endCommute(number);
		boolean chk = false;
		chk = dao.CommuteChk(number);
		request.setAttribute("chk", chk);
	}
	public void requestCommuteChk(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String number = (String)session.getAttribute("number");
		CommuteDAO dao = CommuteDAO.getInstance();
		boolean chk = dao.CommuteChk(number);
		session.setAttribute("chk", chk);
	}
	public void requestGetMember(HttpServletRequest request) {
		int pageNum = 1;
		int limit = listCount;
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		String search_item = request.getParameter("search_item");
		String text = request.getParameter("text");
		
		int total_record = dao.getAllMemberCount(search_item, text);
		memberList = dao.getMember(pageNum, limit, search_item, text);
		
		int total_page;
		if(total_record % limit == 0) {
			total_page = total_record / limit;
		} else {
			total_page = total_record / limit;
			total_page = total_page + 1;
		}

		request.setAttribute("memberList", memberList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
	}
}
