package box;

import com.feup.contribution.aida.annotations.TestFor;

import junit.framework.TestCase;

@TestFor("box")
public class BoxTest extends TestCase {
	
	public void testOpenClose() throws Exception {
		Box b = new Box();
		assertFalse(b.isOpened());
		b.open();
		assertTrue(b.isOpened());
		b.close();
		assertFalse(b.isOpened());
	}
	
	public void testOpenFail() throws Exception {
		Box b = new Box();
		b.open();
		try {
			b.open();
			fail();
		} catch (Exception e) {
			assertEquals("Already Opened", e.getMessage());
		}
	}

	public void testCloseFail() throws Exception {
		Box b = new Box();
		try {
			b.close();
			fail();
		} catch (Exception e) {
			assertEquals("Already Closed", e.getMessage());
		}
	}
	
	public void testPutRemove() throws Exception {
		Box b = new Box();
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
	
	public void testBoxFull() throws Exception {
		Box b = new Box();
		b.open();
		b.put("Stuff");
		try {
			b.put("Something Else");
			fail();
		} catch (Exception e) {
			assertEquals("Box Full", e.getMessage());
		}
	}
	
	public void testBoxClosed() throws Exception {
		Box b = new Box();
		
		try {
			b.put("Stuff");
			fail();
		} catch (Exception e) {
			assertEquals("Box Closed", e.getMessage());
		}
		
		try {
			b.inspect();
			fail();
		} catch (Exception e) {
			assertEquals("Box Closed", e.getMessage());
		}

		try {
			b.remove();
			fail();
		} catch (Exception e) {
			assertEquals("Box Closed", e.getMessage());
		}
	}
}
