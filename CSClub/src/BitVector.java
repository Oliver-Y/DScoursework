
public interface BitVector<E extends Boolean>{
	void clear(); 
	boolean isEmpty(); 
	void add(); 
	Boolean remove(); 
	boolean contains();
	boolean containsAll(BitVector<E> other);
	public Boolean clone(); 
	public int size();
	

}
