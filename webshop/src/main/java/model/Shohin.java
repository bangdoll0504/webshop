package model;

import java.io.Serializable;

public class Shohin implements Serializable {
	private String code;
	private String name;
	private String vol;
	private int price;
	private String comment;
	private String image;

	public Shohin() {
	}

	public Shohin(String code, String name, String vol, int price,
			String comment, String image) {
		this.code = code;
		this.name = name;
		this.vol = vol;
		this.price = price;
		this.comment = comment;
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getVol() {
		return vol;
	}

	public int getPrice() {
		return price;
	}

	public String getComment() {
		return comment;
	}

	public String getImage() {
		return image;
	}
}