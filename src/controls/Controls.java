package controls;

import java.io.Serializable;

import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Controls implements Serializable {
	public KeyStroke forward;
	public KeyStroke forward2;
	public KeyStroke back;
	public KeyStroke back2;
	public KeyStroke turnLeft;
	public KeyStroke turnLeft2;
	public KeyStroke turnRight;
	public KeyStroke turnRight2;
	
	public KeyStroke strafeRight;
	public KeyStroke strafeRight2;
	public KeyStroke strafeLeft;
	public KeyStroke strafeLeft2;
	
	public KeyStroke stabilize;
	public KeyStroke stabilize2;
	
	public KeyStroke openOptions;
	public KeyStroke openOptions2;
	
	public Controls() {
		resetDefaults();
	}
	
	public void resetDefaults() {
		forward = KeyStroke.getKeyStroke('w');
		forward2 = KeyStroke.getKeyStroke("UP");
		back = KeyStroke.getKeyStroke('s');
		back2 = KeyStroke.getKeyStroke("DOWN");
		turnLeft = KeyStroke.getKeyStroke('a');
		turnLeft2 = KeyStroke.getKeyStroke("LEFT");
		turnRight = KeyStroke.getKeyStroke('d');
		turnRight2 = KeyStroke.getKeyStroke("RIGHT");
		strafeRight = KeyStroke.getKeyStroke('e');
		strafeRight2 = null;
		strafeLeft = KeyStroke.getKeyStroke('q');
		strafeLeft2 = null;
		stabilize = KeyStroke.getKeyStroke('f');
		stabilize2 = null;
		openOptions = KeyStroke.getKeyStroke(' ');
		openOptions2 = null;
	}

	public KeyStroke getForward() {
		return forward;
	}

	public void setForward(KeyStroke forward) {
		this.forward = forward;
	}

	public KeyStroke getForward2() {
		return forward2;
	}

	public void setForward2(KeyStroke forward2) {
		this.forward2 = forward2;
	}

	public KeyStroke getBack() {
		return back;
	}

	public void setBack(KeyStroke back) {
		this.back = back;
	}

	public KeyStroke getBack2() {
		return back2;
	}

	public void setBack2(KeyStroke back2) {
		this.back2 = back2;
	}

	public KeyStroke getTurnLeft() {
		return turnLeft;
	}

	public void setTurnLeft(KeyStroke turnLeft) {
		this.turnLeft = turnLeft;
	}

	public KeyStroke getTurnLeft2() {
		return turnLeft2;
	}

	public void setTurnLeft2(KeyStroke turnLeft2) {
		this.turnLeft2 = turnLeft2;
	}

	public KeyStroke getTurnRight() {
		return turnRight;
	}

	public void setTurnRight(KeyStroke turnRight) {
		this.turnRight = turnRight;
	}

	public KeyStroke getTurnRight2() {
		return turnRight2;
	}

	public void setTurnRight2(KeyStroke turnRight2) {
		this.turnRight2 = turnRight2;
	}

	public KeyStroke getStrafeLeft() {
		return strafeLeft;
	}

	public void setStrafeLeft(KeyStroke strafeLeft) {
		this.strafeLeft = strafeLeft;
	}

	public KeyStroke getStrafeRight() {
		return strafeRight;
	}

	public void setStrafeRight(KeyStroke strafeRight) {
		this.strafeRight = strafeRight;
	}

	public KeyStroke getStrafeRight2() {
		return strafeRight2;
	}

	public void setStrafeRight2(KeyStroke strafeRight2) {
		this.strafeRight2 = strafeRight2;
	}

	public KeyStroke getStrafeLeft2() {
		return strafeLeft2;
	}

	public void setStrafeLeft2(KeyStroke strafeLeft2) {
		this.strafeLeft2 = strafeLeft2;
	}

	public KeyStroke getStabilize() {
		return stabilize;
	}

	public void setStabilize(KeyStroke stabilize) {
		this.stabilize = stabilize;
	}

	public KeyStroke getStabilize2() {
		return stabilize2;
	}

	public void setStabilize2(KeyStroke stabilize2) {
		this.stabilize2 = stabilize2;
	}

	public KeyStroke getOpenOptions() {
		return openOptions;
	}

	public void setOpenOptions(KeyStroke openOptions) {
		this.openOptions = openOptions;
	}

	public KeyStroke getOpenOptions2() {
		return openOptions2;
	}

	public void setOpenOptions2(KeyStroke openOptions2) {
		this.openOptions2 = openOptions2;
	}

}
