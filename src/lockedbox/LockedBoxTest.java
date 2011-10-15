package lockedbox;

import box.Box;

import com.feup.contribution.aida.annotations.ReplaceTest;
import com.feup.contribution.aida.annotations.TestFor;

import junit.framework.TestCase;

@TestFor("lockedbox")
public class LockedBoxTest extends TestCase {

	public void testDefaultKey() throws Exception{
		Box b = new Box();
		b.unlock(1234);
	}

	public void testLockUnlock() throws Exception{
		Box b = new Box();
		b.unlock(1234);
		b.lock(9876);
		try {
			b.unlock(1234);
			fail();
		} catch (Exception e) {
			assertEquals("Wrong Key", e.getMessage());
		}
		b.unlock(9876);
	}
	
	public void testLockLocked() {
		Box b = new Box();
		try {
			b.lock(9876);
			fail();
		} catch (Exception e) {
			assertEquals("Already Locked", e.getMessage());
		}
	}
	
	public void testOpenLocked() {
		Box b = new Box();
		try {
			b.open();
			fail();
		} catch (Exception e) {
			assertEquals("Box Locked", e.getMessage());
		}
	}
	
	public void testOpenUnlocked() throws Exception {
		Box b = new Box();
		b.unlock(1234);
		b.open();
		assertFalse(b.isLocked());
	}
	
	@ReplaceTest("box.BoxTest.testOpenClose")
	public void testOpenClose() throws Exception {
		Box b = new Box();
		b.unlock(1234);
		assertFalse(b.isOpened());
		b.open();
		assertTrue(b.isOpened());
		b.close();
		assertFalse(b.isOpened());
	}
	
	@ReplaceTest("box.BoxTest.testOpenFail")
	public void testOpenFail() throws Exception {
		Box b = new Box();
		b.unlock(1234);
		b.open();
		try {
			b.open();
			fail();
		} catch (Exception e) {
			assertEquals("Already Opened", e.getMessage());
		}
	}
	
	@ReplaceTest("box.BoxTest.testPutRemove")
	public void testPutRemove() throws Exception {
		Box b = new Box();
		b.unlock(1234);
		b.open();
		b.put("Stuff");
		assertEquals("Stuff", b.inspect());
		b.remove();
		try {
			b.inspect();
			fail();
		} catch (Exception e) {
			assertEquals("Box Empty", e.getMessage());
		}
	}

	@ReplaceTest("box.BoxTest.testBoxFull")
	public void testBoxFull() throws Exception {
		Box b = new Box();
		b.unlock(1234);
		b.open();
		b.put("Stuff");
		try {
			b.put("Something Else");
			fail();
		} catch (Exception e) {
			assertEquals("Box Full", e.getMessage());
		}
	}
}
