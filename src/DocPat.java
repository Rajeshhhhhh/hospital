
public class DocPat {

	int id;
	String name;
	String doc_name;
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public DocPat(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public DocPat(String doc_name, String name) {
		
		this.doc_name = doc_name;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
