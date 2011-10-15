package box;

public class Box {
	private boolean opened = false;
	private Object content = null;
	
	public void open() throws Exception {
		if (opened) throw new Exception("Already Opened");
		opened = true;
	}
	
	public void close() throws Exception {
		if (!opened) throw new Exception("Already Closed");
		opened = false; 
	}
	
	public boolean isOpened() {
		return opened;
	}
	
	public void put(Object content) throws Exception {
		if (!opened) throw new Exception("Box Closed");
		if (this.content != null) throw new Exception("Box Full");
		this.content = content;
	}
	
	public Object inspect() throws Exception {
		if (!opened) throw new Exception("Box Closed");
		if (content == null) throw new Exception("Box Empty");
		return content;
	}
	
	public void remove() throws Exception {
		if (!opened) throw new Exception("Box Closed");
		if (content == null) throw new Exception("Box Empty");
		content = null;
	}
}
