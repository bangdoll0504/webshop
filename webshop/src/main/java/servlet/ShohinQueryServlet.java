package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.BOException;
import model.Shohin;
import model.ShohinQueryLogic;

@WebServlet("/query")
public class ShohinQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//GETメソッド
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータ
		request.setCharacterEncoding("UTF-8");
		String mode = request.getParameter("mode");
		String name = request.getParameter("name");
		String low = request.getParameter("low");
		String high = request.getParameter("high");
		//検索
		List<Shohin> shohinList = null;
		try {
			if (mode != null && mode.equals("nameQuery")) {
				shohinList = new ShohinQueryLogic().execute(name);
			} else if (mode != null &&
					mode.equals("priceQuery")) {
				shohinList = new ShohinQueryLogic().execute(low, high);
			} else {
				shohinList = new ArrayList<>(); //初回の結果は空
			}
		} catch (BOException e) {
			//開発者向け例外ログはコンソールに表示
			e.printStackTrace();
			//エンドユーザー向けメッセージはリクエストスコープに保存
			request.setAttribute("errorMessage", e.getMessage());
		}
		//結果リストをリクエストスコープに格納
		request.setAttribute("shohinList", shohinList);
		//フォワード
		request.getRequestDispatcher("query.jsp")
				.forward(request, response);
	}
}