package com.usc.brainattacker.entity;

public class Statistic {
	private int token;
	private int win_number;
	private int game_number;

	public Statistic() {
	}

	public Statistic(int win_number, int game_number, int token) {
		this.win_number = win_number;
		this.game_number = game_number;
		this.token = token;
	}

	public int getToken(){return token;}

	public void setToken(int token){this.token = token;}

	public int getWinNumber() {
		return win_number;
	}

	public void setWinNumber(int win_number) {
		this.win_number = win_number;
	}

	public int getGameNumber() {
		return game_number;
	}

	public void setGameNumber(int game_number) {
		this.game_number = game_number; 
	}

}