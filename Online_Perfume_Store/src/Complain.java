
//This is the class we create a complain for admin to see

public class Complain {

	private String comment;
	
	private Perfume perfume;
	
	private int id;
	
	//constructor
	public Complain(Perfume p, String c)
	{
	    setPerfume(p);
	    setComment(c);
	    id = id + 1;
	}
	
	public void setComment(String c){
		this.comment = c;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setPerfume(Perfume p){
		this.perfume = p;
	}
	
	public Perfume getPerfume(){
		return perfume;
	}
}
