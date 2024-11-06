package model;

import java.sql.SQLException;
import java.util.List;

import dao.ShohinDAO;
import exception.BOException;

public class ShohinQueryLogic extends BOMessage {
	//商品名検索
	public List<Shohin> execute(String name) throws BOException {
		try {
			return new ShohinDAO().findByName(name);
		} catch (SQLException e) {
			throw new BOException(MSG_DB_ERROR, e);
		}
	}

	//価格検索
	public List<Shohin> execute(
			String low, String high) throws BOException {
		try {
			int lowNum, highNum;
			if (low.isBlank()) {
				lowNum = 0;
			} else {
				lowNum = Integer.parseInt(low);
			}
			if (high.isBlank()) {
				highNum = Integer.MAX_VALUE;
			} else {
				highNum = Integer.parseInt(high);
			}
			if (lowNum <= highNum) {
				return new ShohinDAO().findByPrice(
						lowNum, highNum);
			} else {
				return new ShohinDAO().findByPrice(
						highNum, lowNum);
			}
		} catch (NumberFormatException e) {
			throw new BOException(MSG_INPUT_ERROR, e);
		} catch (SQLException e) {
			throw new BOException(MSG_DB_ERROR, e);
		}
	}
}