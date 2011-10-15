package lockedbox;

import box.Box;

public aspect LockedBox {
	private boolean Box.locked = true;
	private int Box.key = 1234;
	
	public void Box.lock(int key) throws Exception {
		if (locked) throw new Exception("Already Locked");
		if (isOpened()) throw new Exception("Box Open");
		locked = true;
		this.key = key;
	}

	public void Box.unlock(int key) throws Exception {
		if (!locked) throw new Exception("Already Unlocked");
		if (isOpened()) throw new Exception("Box Open");
		if (this.key != key) throw new Exception("Wrong Key");
		locked = false;
	}
	
	public boolean Box.isLocked() {
		return locked;
	}
	
	pointcut openBox(Box b) : call(public void Box.open()) && target(b);
	
	void around(Box b) throws Exception : openBox(b) {
		if (b.isLocked()) throw new Exception("Box Locked");
		proceed(b);
	}
}
