import java.util.Scanner;

public class DriverClass {

	public static void main(String[] args) {
		
		PQEntry<Integer,String>[] PQentry = new PQEntry[10];
		
		PQEntry<Integer,String> entry1 = new PQEntry<Integer,String>(7,"a"); 
		PQEntry<Integer,String> entry2 = new PQEntry<Integer,String>(9,"b"); 
		PQEntry<Integer,String> entry3 = new PQEntry<Integer,String>(4,"c"); 
		PQEntry<Integer,String> entry4 = new PQEntry<Integer,String>(8,"d"); 
		PQEntry<Integer,String> entry5 = new PQEntry<Integer,String>(83,"e"); 
		PQEntry<Integer,String> entry6 = new PQEntry<Integer,String>(21,"f"); 
		PQEntry<Integer,String> entry7 = new PQEntry<Integer,String>(26,"g"); 
		PQEntry<Integer,String> entry8 = new PQEntry<Integer,String>(1,"h"); 
		PQEntry<Integer,String> entry9 = new PQEntry<Integer,String>(3,"k"); 
		PQEntry<Integer,String> entry10 = new PQEntry<Integer,String>(19,"z"); 
		
		PQentry[0] = entry1;
		PQentry[1] = entry2;
		PQentry[2] = entry3;
		PQentry[3] = entry4;
		PQentry[4] = entry5;
		PQentry[5] = entry6;
		PQentry[6] = entry7;
		PQentry[7] = entry8;
		PQentry[8] = entry9;
		PQentry[9] = entry10;
		

		Scanner scanner  = new Scanner(System.in); 
		System.out.println("You want a 'MIN' or 'MAX' : ");
		String userInput = scanner.next();
		boolean heapState = false; 
		
		if(userInput.equals("MIN")) heapState = true; 
		else heapState = false; 
		
		PriorityQueue <Integer,String> PQ = new PriorityQueue(PQentry, heapState);
		
		System.out.println("\nHere is the initial heap");
		PQ.displayHeap();
		
		System.out.println("\nTesting the remove top method...");
		PQ.removeTop();	
		PQ.displayHeap();
		
		System.out.println("\nTesting the insert method...");
		PQ.insert(5, "u");
		PQ.displayHeap();
		
		System.out.println("\nTesting the top method...");
		PQ.top();
		
		System.out.println("\nTesting method to remove specific entry...");
		PQ.remove(entry6);
		PQ.displayHeap();
		
		System.out.println("\nTesting replace key method...");
		PQ.replaceKey(entry5, 30);
		PQ.displayHeap();
		
		System.out.println("\nTestting replace value method...");
		PQ.replaceValue(entry4, "newValue");
		PQ.displayHeap();
		
		System.out.println("\nTesting toggle method...");
		PQ.toggle();
		System.out.println("\nTesting heap state method...");
		System.out.println(PQ.state() + "\n");
		
		System.out.println("\nTesting toggle method again...");
		PQ.toggle();
		System.out.println("\nTesting heap state method...");
		System.out.println(PQ.state() + "\n");
		
		System.out.println("\nTesting isEmpty method");
		PQ.isEmpty();
		
		System.out.println("\nTesting size method");
		PQ.size();
	}
}