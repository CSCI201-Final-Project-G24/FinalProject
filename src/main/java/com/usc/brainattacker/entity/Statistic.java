package com.usc.brainattacker.entity;

public class Statistic {
	private int win_number;
	private int game_number;
	private double win_rate;

	public Statistic() {
	}

	public Statistic(int win_number, int game_number) {
		this.win_number = win_number;
		this.game_number = game_number;
		win_rate = (double)win_number /(double)game_number; 
	}

	public double getWinRate(){
		return win_rate;
	}

	public int getWinNumber() {
		return win_number;
	}

	public void setWinNumber(int win_number) {
		this.win_number = win_number;
		win_rate = (double)win_number /(double)game_number; 
	}

	public int getGameNumber() {
		return game_number;
	}

	public void setGameNumber(int game_number) {
		this.game_number = game_number;
		win_rate = (double)win_number /(double)game_number; 
	}

}