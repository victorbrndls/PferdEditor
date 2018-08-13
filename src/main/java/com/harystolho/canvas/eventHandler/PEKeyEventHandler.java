package com.harystolho.canvas.eventHandler;

import com.harystolho.Main;
import com.harystolho.canvas.CanvasManager;
import com.harystolho.thread.FileUpdaterThread;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class PEKeyEventHandler {

	private CanvasManager cm;
	private Scene scene;

	public PEKeyEventHandler(Scene scene, CanvasManager cm) {
		this.cm = cm;
		this.scene = scene;

		init();

	}

	private void init() {

		scene.setOnKeyPressed((e) -> {
			keyPress(e);
		});

		scene.setOnKeyReleased((e) -> {
			keyRelease(e);
		});

	}

	private void keyRelease(KeyEvent e) {

	}

	private void keyPress(KeyEvent e) {

		switch (e.getCode()) {
		case UP:
			cm.lineUp();
			e.consume();
			return;
		case DOWN:
			cm.lineDown();
			e.consume();
			return;
		case LEFT:
			cm.moveCursorLeft();
			e.consume();
			return;
		case RIGHT:
			cm.moveCursorRight();
			e.consume();
			return;
		case S:
			pressS(e);
			return;
		case HOME:
			pressHOME(e);
			return;
		case END:
			pressEND(e);
			return;
		case F3:
			cm.printDebugMessage();
			return;
		case F4:
			cm.getCurrentFile().getWords().printDebug();
			return;
		default:
			break;
		}

		pressKeyOnCanvas(e);

	}

	private void pressKeyOnCanvas(KeyEvent e) {
		if (cm.getCanvas().isFocused()) {
			if (cm.getCurrentFile() != null) {
				e.consume();
				cm.getCurrentFile().type(e);
			}
		}
	}

	private void pressS(KeyEvent e) {
		if (e.isControlDown()) {
			Main.getApplication().getMainController().saveOpenedFile();
		} else {
			pressKeyOnCanvas(e);
		}
	}

	private void pressHOME(KeyEvent e) {
		if (e.isControlDown()) {
			cm.moveCursorToFirstLine();
		} else {
			cm.moveCursorToStartOfTheLine();
		}
	}

	private void pressEND(KeyEvent e) {
		if (e.isControlDown()) {
			cm.moveCursorToLastLine();
		} else {
			cm.moveCursorToEndOfTheLine();
		}
	}

}
