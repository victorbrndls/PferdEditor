package com.harystolho.canvas.eventHandler;

import com.harystolho.canvas.CanvasManager;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class CMKeyEventHandler {

	private CanvasManager cm;
	private Scene scene;

	public CMKeyEventHandler(Scene scene, CanvasManager cm) {
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
			return;
		case DOWN:
			cm.lineDown();
			return;
		case NUMPAD4:
			cm.scrollLeft();
			return;
		case NUMPAD6:
			cm.scrollRight();
			return;
		case F3:
			cm.printDebugMessage();
			return;
		case F4:
			System.out.println(cm.getCurrentFile().getWords().toString());
			return;
		default:
			break;
		}

		if (cm.getCanvas().isFocused()) {
			if (cm.getCurrentFile() != null) {
				cm.getCurrentFile().type(e);
			}
		}

	}

}
