package com.harystolho.canvas.eventHandler;

import java.util.HashMap;
import java.util.function.Function;

import com.harystolho.Main;
import com.harystolho.canvas.CanvasManager;
import com.harystolho.controllers.FileRightClickController;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The Key Event Handler for this application
 * 
 * @author Harystolho
 *
 */
public class ApplicationKeyHandler {

	private CanvasManager cm;
	private Scene scene;

	private HashMap<KeyCode, Function<KeyEvent, Boolean>> keyMap;

	public ApplicationKeyHandler(Scene scene, CanvasManager cm) {
		this.cm = cm;
		this.scene = scene;

		keyMap = new HashMap<>();

		init();

	}

	private void init() {

		loadKeyMap();

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

		Function<KeyEvent, Boolean> key = keyMap.get(e.getCode());
		if (key != null) { // If there's a function for this key
			if (key.apply(e)) { // If the function returns true then stop here, else execute code below
				return;
			}
		}

		pressKeyOnCanvas(e);

	}

	private void pressKeyOnCanvas(KeyEvent e) {
		if (cm.getCanvas().isFocused()) {
			if (cm.getCurrentFile() != null) {
				cm.getCurrentFile().type(e);
				e.consume();
			}
		}
	}

	private void loadKeyMap() {

		keyMap.put(KeyCode.UP, (e) -> {
			cm.lineUp();
			e.consume();
			return true;
		});

		keyMap.put(KeyCode.DOWN, (e) -> {
			cm.lineDown();
			e.consume();
			return true;
		});

		keyMap.put(KeyCode.LEFT, (e) -> {
			cm.moveCursorLeft();
			e.consume();
			return true;
		});

		keyMap.put(KeyCode.RIGHT, (e) -> {
			cm.moveCursorRight();
			e.consume();
			return true;
		});

		keyMap.put(KeyCode.S, (e) -> {
			if (e.isControlDown()) {
				Main.getApplication().getMainController().saveOpenedFile();
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});

		keyMap.put(KeyCode.HOME, (e) -> {
			if (e.isControlDown()) {
				cm.moveCursorToFirstLine();
			} else {
				cm.moveCursorToBeginningOfTheLine();
			}
			return true;
		});

		keyMap.put(KeyCode.END, (e) -> {
			if (e.isControlDown()) {
				cm.moveCursorToLastLine();
			} else {
				cm.moveCursorToEndOfTheLine();
			}
			return true;
		});

		keyMap.put(KeyCode.ALT, (e) -> {
			return true;
		});

		keyMap.put(KeyCode.SHIFT, (e) -> {
			return true;
		});

		keyMap.put(KeyCode.CONTROL, (e) -> {
			return true;
		});

		keyMap.put(KeyCode.CAPS, (e) -> {
			return true;
		});

		keyMap.put(KeyCode.F3, (e) -> {
			cm.printDebugMessage();
			return true;
		});

		keyMap.put(KeyCode.F4, (e) -> {
			if (!e.isAltDown()) {
				cm.getCurrentFile().getWords().printDebug();
			}
			return true;
		});

		// Normal Keys
		keyMap.put(KeyCode.W, (e) -> {
			if (e.isControlDown()) {
				Main.getApplication().getMainController().closeFile(cm.getCurrentFile());
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});

		keyMap.put(KeyCode.C, (e) -> {
			if (e.isControlDown()) {
				FileRightClickController.copyFile(Main.getApplication().getCanvasManager().getCurrentFile());
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});

		keyMap.put(KeyCode.V, (e) -> {
			if (e.isControlDown()) {
				FileRightClickController.pasteFile(Main.getApplication().getCanvasManager().getCurrentFile());
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});
		
		
		// Shift
		keyMap.put(KeyCode.OPEN_BRACKET, (e) -> {
			if (e.isShiftDown()) {
				KeyEvent ke = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, " ", "{", KeyCode.BRACELEFT, false, false,
						false, false);
				pressKeyOnCanvas(ke);
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});

		keyMap.put(KeyCode.CLOSE_BRACKET, (e) -> {
			if (e.isShiftDown()) {
				KeyEvent ke = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, " ", "}", KeyCode.BRACERIGHT, false, false,
						false, false);
				pressKeyOnCanvas(ke);
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});
		
		keyMap.put(KeyCode.DIGIT9, (e) -> {
			if (e.isShiftDown()) {
				KeyEvent ke = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, " ", "(", KeyCode.LEFT_PARENTHESIS, false, false,
						false, false);
				pressKeyOnCanvas(ke);
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});

		keyMap.put(KeyCode.DIGIT0, (e) -> {
			if (e.isShiftDown()) {
				KeyEvent ke = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, " ", ")", KeyCode.RIGHT_PARENTHESIS, false, false,
						false, false);
				pressKeyOnCanvas(ke);
			} else {
				pressKeyOnCanvas(e);
			}
			return true;
		});

	}

}
