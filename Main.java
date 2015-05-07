import java.util.Arrays;


public class Main {

	public static void main(String[] args) {

		DOTGenerator generator = new DOTGenerator();
		
		generator.addNode("A");
		generator.addNode("C");
		generator.addAdjacencies("C", Arrays.asList("B", "A"));
		generator.addNode("D");
		generator.addAdjacency("C", "D");
		
		generator.printDot();
	}

}
