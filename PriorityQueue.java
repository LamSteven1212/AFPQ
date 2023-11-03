public class PriorityQueue <K extends Comparable<K>,V> {
	
	private PQEntry<K,V>[] PQheap; 
	private boolean minHeap;
	private int PQcapacity; 
	private int PQsize; 
	private static final int initialCap = 40;
		
	//Parameterized Constructor
//	public PriorityQueue(boolean minHeap, int capacity) {
//		this.minHeap = minHeap; 
//		this.PQcapacity = capacity; 
//		this.PQsize = 0; 
//		this.PQheap = new PQEntry[capacity]; 
//	}
	
	public PriorityQueue(PQEntry<K, V>[] e, boolean minHeap) {
		this.PQheap = e; 
		this.PQsize = e.length; 
		this.PQcapacity = e.length; 
		this.minHeap = minHeap; 
		this.setHeap(minHeap); 
	}
	
	
	public void displayHeap() {
		System.out.println("Here all the entries of the heap ...");
		for(int i=0; i< PQsize; i++) {
			System.out.println("KEY: " + this.PQheap[i].key + " and VALUE: " + this.PQheap[i].value );
		}
	}
	
	
	private int rightChildren(int i) {
		if((2*i)+2 < PQsize) {
//			System.out.println("The right of the children is: " + ((2*i)+2));
			return (2*i)+2;
		}
		else {
//			System.out.println("The right children does not exist");
			return -1;
		}
	}
	
	
	private boolean hasRightChildren(int i) {
		if(rightChildren(i) == -1) return false;
		return true;
	}
	
	
	private int leftChildren(int i) {
		if((2*i)+1 < PQsize) {
//			System.out.println("The left of the children is: " + ((2*i)+1));
			return (2*i)+1;
		}
		else {
//			System.out.println("The left children does not exist");
			return -1;
		}
	}
	
	private boolean hasLeftChildren(int i) {
		if(leftChildren(i) == -1) return false;
		return true;
	}
	
	
	public PQEntry<K,V> lastElement(){
		if(isEmpty()) return null; 
		else  {
			System.out.println("Last entry of the heap : " + PQheap[PQsize-1]);
			return(PQheap[PQsize-1]); 
		}
	}
	
	
	public PQEntry<K,V> top(){
		if(isEmpty()) return null; 
		else {
			System.out.println("Top entry of the heap : " + PQheap[0]);
			return (PQheap[0]); 
		}
	}
	
	
	public boolean isEmpty() {
		boolean hasEntries = (this.PQsize == 0);
		System.out.println("The heap has entries : " + !hasEntries);
		return (hasEntries); 
	}
	
	
	public int size() {
		System.out.println("The current size of the heap is : " + this.PQsize);
		return (this.PQsize); 
	}
	
	
	public String state() {
		if(this.minHeap) {
			return "It is currently a min heap!"; 
		}
		return "It is currently a max heap!"; 
	}
	
	
	private void setHeap(boolean minHeap) {
		for(int index = PQheap.length-1 ; index >= 0 ; index--) {
			downHeap(index);
		}
	}
	
	
	public PQEntry<K,V> remove(PQEntry<K,V> entry){
		PQEntry<K,V> removedEntry = entry; 
		boolean foundEntry = false;
		int i = 0; 
		int p = 0;
		
		if(isEmpty()) {
			System.out.println("Error: You cannot remove an element, the queue is empty");
			return null;
		}
		else {
			for(i = 0; i < PQsize; i++) {
				if(PQheap[i].key == entry.key) {
					System.out.println("we just found the element to remove from the heap, " + PQheap[i]);
					PQheap[i] = PQheap[PQsize-1]; 
					foundEntry = true;
					PQsize--;
					break;
				}
			}
		}
		p = (i-1)/2;					
		if(minHeap) {
			if(PQheap[p].key.compareTo(PQheap[i].key) > 0) {
				System.out.println("just switched with last element and going up");
				upHeap(i);		
			}
			else {
				System.out.println("just switched with last element and going down");
				downHeap(i);
			}	
		}
		//handle a max heap
		else {
			if(PQheap[p].key.compareTo(PQheap[i].key) < 0) {
				System.out.println("just switched with last element and going up");
				upHeap(i);		
			}
			else {
				System.out.println("just switched with last element and going down");
				downHeap(i);
			}
		}			
		return removedEntry; 		
	}

	
	public PQEntry<K,V> removeTop(){
		if(isEmpty()) {
			System.out.println("Error: Cannot remove the top element, the heap is empty");
			return null; 
		}
		else { 
			System.out.println("The heap has elements, let's remove the top element");
			PQEntry<K,V> initialTop = PQheap[0]; 
			PQheap[0] = PQheap[PQsize-1];
			PQheap[PQsize-1] = null;
			PQsize--;
			downHeap(0);
			
			System.out.println("We just removed the top entry, which is : " + initialTop);
			System.out.println("The new top entry is now : " + PQheap[0]);
			return initialTop; 
		}
	}
	
	
	public void changeSize(int size) {
		PQEntry<K,V>[] temporary = new PQEntry[size];
		System.out.println("Array of entries now has " + size + " slots");
		for(int i = 0 ; i < PQheap.length ; i++) {
			temporary[i] = PQheap[i];
		}
		PQheap = temporary;
	}
	
	
	public V replaceValue(PQEntry<K,V> entry, V value){
		boolean foundEntry = false;
		V returnedValue = entry.value;
		if(isEmpty()) {
			System.out.println("The queue is empty, there is no entry to change");
			return null;
		}
		for(int i = 0 ; i < PQsize-1 ; i++) {
			if(PQheap[i].key == entry.key) {
				System.out.println("We just found the entry, it is : " + PQheap[i]);
				foundEntry = true;
				PQheap[i].value = value;
				System.out.println("We just changed its value to " + value);
				break;
			}
		}
		if(!foundEntry) {
			System.out.println("The entry that you are looking for does not exist in the heap");
			return null;
		}
		return returnedValue;
	}
	
	
	public K replaceKey(PQEntry<K,V> entry, K newKey){
		K returnedKey = entry.key;
		boolean foundEntry = false;
		int i = 0; 
		int p= 0;
		
		if(isEmpty()) {
			System.out.println("The queue is empty, there is no entry to change");
			return null;
		}
		for(i = 0 ; i <= PQsize-1 ; i++) {
			if(PQheap[i].key == entry.key) {
				System.out.println("We just found the entry, it is : " + PQheap[i]);
				foundEntry = true;
				PQheap[i].key = newKey;
				break;
			}
		}
		
		p = (i-1)/2;
		if(minHeap) {
			if(PQheap[p].key.compareTo(PQheap[i].key) > 0) {
				System.out.println("just switched with last element and going up");
				upHeap(i);		
			}
			else {
				System.out.println("just switched with last element and going down");
				downHeap(i);
			}	
		}
		//handle a max heap
		else {
			if(PQheap[p].key.compareTo(PQheap[i].key) < 0) {
				System.out.println("just switched with last element and going up");
				upHeap(i);		
			}
			else {
				System.out.println("just switched with last element and going down");
				downHeap(i);
			}
		}			
		if(!foundEntry) {
			System.out.println("The entry that you want to change does not exist within the heap");
			return null;
		}
		System.out.println("We change the key " + returnedKey + " and this key does not exist in the heap anymore");
		return returnedKey; 			
	}
	
	
	public PQEntry<K,V> insert(K key, V value){
		if(size() == PQheap.length) {
			System.out.println("The heap is already full, let's change its size!");
			changeSize(PQheap.length + 20);
			
			PQEntry<K,V> insertedEntry = new PQEntry(key,value); 
			PQsize++; 
			this.PQheap[PQsize-1] = insertedEntry; 
			upHeap(PQsize-1);
			System.out.println("We just inserted a new entry into the heap : " + insertedEntry.toString());
			return insertedEntry;
		}
		else {
			PQEntry<K,V> insertedEntry = new PQEntry(key, value); 
			PQsize++;
			this.PQheap[PQsize-1] = insertedEntry; 
			upHeap(PQsize-1);
			System.out.println("We just inserted a new entry into the heap : " + insertedEntry.toString());
			return insertedEntry;
		}
	}
	
	
	public void toggle() {
		if(minHeap) {
			System.out.println("We just change the MIN heap to a MAX heap");
			minHeap = false; 
		}
		else {
			System.out.println("We just change the MAX heap to a MIN heap");
			minHeap = true;
		}
		System.out.println("Let's downheap each element of the heap going backwards");
		for(int index = PQsize-1; index >= 0; index--) {
			downHeap(index); 
		}
//		System.out.println("After toggling the heap, we now have ...");
		this.displayHeap();
	}
	
	
	private void upHeap(int i) {
		int p = (i-1)/2;
		if(minHeap) {
			if(PQheap[p].key.compareTo(PQheap[i].key) > 0) {
				PQEntry<K, V> temporary = (PQEntry<K,V>) PQheap[p];
				PQheap[p] = PQheap[i];
				PQheap[i] = temporary;
				upHeap(p);
			}
		}
		//Handles max heap case
		else {
			if(PQheap[p].key.compareTo(PQheap[i].key) < 0) {
				PQEntry<K, V> temporary = (PQEntry<K,V>) PQheap[p];
				PQheap[p] = PQheap[i];
				PQheap[i] = temporary;
				upHeap(p);
			}
		}
	}
	
	
	
	private void downHeap(int i) {
		if(minHeap) {
			int leftChild = leftChildren(i); 
			int rightChild = rightChildren(i); 
			int nodeSwap = 0; 
			
			if(leftChild==-1) {
				System.out.println("This is a base case : A leaf node");
				return;
			}
			else {
				if(hasRightChildren(i) == false) {
					if(PQheap[leftChild].key.compareTo(PQheap[i].key) < 0) nodeSwap = leftChild;
				}
				
				else if(PQheap[leftChild].key.compareTo(PQheap[rightChild].key) > 0) nodeSwap = rightChild; 
				else if ((PQheap[leftChild].key.compareTo(PQheap[rightChild].key) < 0))  nodeSwap = leftChild; 

				if (PQheap[nodeSwap].key.compareTo(PQheap[i].key) < 0) {
					PQEntry<K, V> temporary = (PQEntry<K,V>) PQheap[i];
					PQheap[i] = PQheap[nodeSwap];
					PQheap[nodeSwap] = temporary;
					downHeap(nodeSwap);
				}
			}
		}
		else {
			int nodeSwap = 0; 
			int leftChild = leftChildren(i); 
			int rightChild = rightChildren(i); 
			
			if(leftChild==-1) {
				System.out.println("This is a base case : A leaf node");
				return;
			}
			else {
				if(hasRightChildren(i) == false) {
					if(PQheap[leftChild].key.compareTo(PQheap[i].key) > 0) nodeSwap = leftChild;
				}
				
				else if(PQheap[leftChild].key.compareTo(PQheap[rightChild].key) > 0) nodeSwap = leftChild; 
				else if ((PQheap[leftChild].key.compareTo(PQheap[rightChild].key) < 0))  nodeSwap = rightChild; 

				if (PQheap[nodeSwap].key.compareTo(PQheap[i].key) > 0) {
					PQEntry<K, V> temporary = (PQEntry<K,V>) PQheap[i];
					PQheap[i] = PQheap[nodeSwap];
					PQheap[nodeSwap] = temporary;
					downHeap(nodeSwap);
				}
			}
		}
	}
}


//Class for each Entry stored at each index of the array
	class PQEntry<K ,V>{
		K key; 
		V value; 
		
		public PQEntry(K key, V value){
			this.key = key; 
			this.value = value; 
		}
		public String toString(){
			return "The entry is : (" + this.key + ", " + this.value + ")"; 
		}
	}
