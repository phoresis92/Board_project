package vo;

public class BoardVO {
	
	private int seq;
	private String title;
	private String contents;
	private String writer;
	private String time;
	private int viewcount;
	private int password;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	
	public BoardVO() {
		super();
	}
	
	public BoardVO(int seq, String title, String contents, String writer, String time, int viewcount, int password) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.time = time;
		this.viewcount = viewcount;
		this.password = password;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", contents=" + contents + ", writer=" + writer + ", time="
				+ time + ", viewcount=" + viewcount + ", password=" + password + "]";
	}
	
	
	

}
