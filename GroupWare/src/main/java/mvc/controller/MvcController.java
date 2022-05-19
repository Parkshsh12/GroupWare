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

import com.oreilly.servlet.MultipartRequest;

import mvc.model.BoardDAO;
import mvc.model.BoardDTO;
import mvc.model.MemberDAO;
import mvc.model.NoticeDAO;
import mvc.model.NoticeDTO;

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
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./home.jsp?id=홈화면");
			rd.forward(request, response);
		}
		//로그인
		else if (command.equals("/login.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/login/login.jsp");
			rd.forward(request, response);
		} else if (command.equals("/LoginAction.do")) {
			requestBoardList(request);
			requestLoginMember(request);
			HttpSession session = request.getSession(true);
			String name = (String)request.getAttribute("name");
			if(name != null) {
				String number = (String)request.getAttribute("number");
				String type = (String)request.getAttribute("type");
				session.setAttribute("name", name);
				session.setAttribute("number", number);
				session.setAttribute("type", type);
				RequestDispatcher rd = request.getRequestDispatcher("/home.jsp?msg=1");
				rd.forward(request, response);
			}
		} else if (command.equals("/searchId.do")) {
			requestSearchId(request);
			
		}
		//인사관리
		else if (command.equals("/member_list.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_list.jsp?id=임직원 관리");
			rd.forward(request, response);
		} else if (command.equals("/member_registration.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_registration.jsp");
			rd.forward(request, response);
		} else if (command.equals("/member_update.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_update.jsp");
			rd.forward(request, response);
		} else if (command.equals("/member_update.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/member_update.jsp");
			rd.forward(request, response);
		} else if (command.equals("/attendance_admin.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/attendance_admin.jsp?id=근태 관리");
			rd.forward(request, response);
		} else if (command.equals("/attendance_admin.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/attendance_admin.jsp");
			rd.forward(request, response);
		} else if (command.equals("/attendance_month.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/management/attendance_month.jsp");
			rd.forward(request, response);
		}
		//영업 관리
		else if (command.equals("/business_company.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/business/business_company.jsp?id=월별 매출 조회");
			rd.forward(request, response);
		} else if (command.equals("/business_search.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/business/business_search.jsp?id=거래처 조회");
			rd.forward(request, response);
		} else if (command.equals("/purchase_main.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/business/purchase_main.jsp?id=매입 관리");
			rd.forward(request, response);
		} else if (command.equals("/sales_main.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/business/sales_main.jsp?id=매출 관리");
			rd.forward(request, response);
		}
		//게시판
		else if (command.equals("/notice_main.do")) {
			requestNoticeList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_main.jsp?id=공지사항");
			rd.forward(request, response);
		} else if (command.equals("/notice_add.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_add.jsp?id=글 등록");
			rd.forward(request, response);
		} else if (command.equals("/notice_add_submit.do")){
			requestNoticeAdd(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_add.jsp?id=글 등록");
			rd.forward(request, response);
		} else if (command.equals("/noticeView.do")) {
			requestNoticeView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_noticeNum.jsp?id=공지사항");
			rd.forward(request, response);
		} else if (command.equals("/notice_update.do")) {
			requestNoticeView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/notice_update.jsp?id=공지사항 수정");
			rd.forward(request, response);
		}
		// 자유게시판
		else if (command.equals("/board_main.do")) {
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/board_main.jsp?id=자유게시판");
			rd.forward(request, response);
		} else if(command.equals("/board_add.do")) {
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/board_add.jsp?id=자유게시판 글등록");
			rd.forward(request, response);
		} else if(command.equals("/boardAddAction.do")) {
			requestBoardWrite(request);
			RequestDispatcher rd = request.getRequestDispatcher("/board_main.do");
			rd.forward(request, response);
		} else if(command.equals("/BoardViewAction.do")) {
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list/board/board_boardNum.jsp?id=자유게시판");
			rd.forward(request, response);
		} else if(command.equals("/board_updateAction.do")) {
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/./list/board/board_update.jsp?id=자유게시판 수정");
			rd.forward(request, response);
		} else if(command.equals("/board_update.do")) {
			requestBoardUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/BoardViewAction.do");
			rd.forward(request, response);
		} else if(command.equals("/board_delete.do")) {
			requestBoardDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/board_main.do");
			rd.forward(request, response);
		}
		// board part end //
		//캘린더
		else if (command.equals("/schedule_all.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/schedule_all.jsp?id=전체 일정");
			rd.forward(request, response);
		} else if (command.equals("/schedule_dep.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/schedule/schedule_dep.jsp?id=부서 일정");
			rd.forward(request, response);
		}
		//주소록
		else if (command.equals("/contact.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/contact/contact.jsp?id=주소록");
			rd.forward(request, response);
		}
		//마이페이지
		else if (command.equals("/attendance_user.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/attendance_user.jsp?id=근태 조회");
			rd.forward(request, response);
		} else if (command.equals("/my_information.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/my_information.jsp?id=내 정보 관리");
			rd.forward(request, response);
		} else if (command.equals("/manager_pay.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/manager_pay.jsp?id=급여 관리");
			rd.forward(request, response);
		} else if (command.equals("/update_information.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("./list/mypage/update_information.jsp");
			rd.forward(request, response);


		}
		/* 로그인 영역*/
		
		/* 로그인 */

		

		

	}

	/* 공지사항 list 가져오기 */
	public void requestNoticeList(HttpServletRequest request) {
		
		NoticeDAO dao = NoticeDAO.getInstance();
		ArrayList<NoticeDTO> noticelist = new ArrayList<NoticeDTO>();
		
		int pageNum = 1;
		int limit = listCount;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		String search_item = request.getParameter("search_item");
		String text = request.getParameter("text");
		
		int total_record = dao.getAllNoticeListCount(search_item, text);
		noticelist = dao.getAllNoticeList(pageNum, limit, search_item, text);
		
		int total_page;
		
		if (total_record % limit == 0){     
	     	total_page =total_record/limit;
		}
		else{
		   total_page =total_record/limit;
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
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		NoticeDTO notice = dao.getNoticeNum(seq, pageNum);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("noticeView", notice);
		request.setAttribute("select_item", request.getParameter("select_item"));
		request.setAttribute("text", request.getParameter("text"));
		
		System.out.println(notice.getTitle());
	}
	
	public void requestNoticeAdd(HttpServletRequest request) {
		NoticeDAO dao = NoticeDAO.getInstance();
		
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int seq = dao.setNoticeAdd(number, name, title, content);
		String pageNum = request.getParameter("pageNum");
		
		
		
	}
	
	
	/* mskim end */
	
	/* 로그인 function*/
	public void requestLoginMember(HttpServletRequest request) {
		MemberDAO dao = MemberDAO.getInstance();
		
		String[] result = dao.loginMember(request.getParameter("number"), request.getParameter("pw"));
		System.out.println("fasdfas");
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
	public void requestMember_update(HttpServletRequest request){
		MemberDAO dao = MemberDAO.getInstance();
		
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String position = request.getParameter("position");
		String department = request.getParameter("department");
		String email = request.getParameter("email");
		int join_date = (int)request.getAttribute("join_date");
	}
	// shpark start
	public void requestBoardList(HttpServletRequest request) {
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		int pageNum = 1;
		int limit=listCount;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		
		String search_item = request.getParameter("search_item");
		String text = request.getParameter("text");
		
		int total_record=dao.getallBoardListCount(search_item, text);
		boardList = dao.getallBoardList(pageNum, limit, search_item, text);
		
		int total_page;
		
		if(total_record % limit == 0) {
			total_page = total_record/limit;
		}
		else {
			total_page = total_record/limit;
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
		System.out.println(dto.getName());
		System.out.println(dto.getNumber());
		System.out.println(dto.getContent());
		System.out.println(dto.getTitle());
		System.out.println(dto.getHit());
		System.out.println(dto.getB_date());
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

		request.setAttribute("num",	num);
		request.setAttribute("board", board);
		request.setAttribute("pageNum",	pageNum);
		
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
	// shpark end
}
