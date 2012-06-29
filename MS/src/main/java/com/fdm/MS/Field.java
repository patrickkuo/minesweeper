package com.fdm.MS;

import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Field {

	private boolean isBomb;
	private int bombValue;
	private JToggleButton bu;
	private boolean opened;

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public JToggleButton getBu() {
		return bu;
	}

	public void setBu(JToggleButton bu2) {
		this.bu = bu2;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	public int getBombValue() {
		return bombValue;
	}

	public void setBombValue(int bombValue) {
		this.bombValue = bombValue;
	}

	public boolean isBomb() {
		return isBomb;
	}

}
