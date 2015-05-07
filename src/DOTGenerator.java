import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The Class DOTGenerator.
 * 
 * @author rodolfo
 */
public class DOTGenerator {

	/** The Constant GRAPH_TYPE. */
	private static final String GRAPH_TYPE = "digraph";
	
	/** The Constant NODE_FORMAT. */
	private static final String NODE_FORMAT = "node [shape=circle fontSize=16]";
	
	/** The Constant EDGE_FORMAT. */
	private static final String EDGE_FORMAT = "edge [length=100, color=gray, fontColor=black]";

	/** The adjacencies. */
	private Map<String, List<String>> adjacencies;

	/**
	 * Instantiates a new DOT generator.
	 */
	public DOTGenerator() {
		super();
		this.adjacencies = new HashMap<String, List<String>>();
	}
	
	/**
	 * Adds the node.
	 *
	 * @param node the node
	 */
	public void addNode(String node){
		if(!adjacencies.containsKey(node)) {
			ArrayList<String> newNodeAdj = new ArrayList<String>();
			adjacencies.put(node, newNodeAdj);
		}
	}

	/**
	 * Adds the adjacency.
	 *
	 * @param node the node
	 * @param adjacency the adjacency
	 */
	public void addAdjacency(String node, String adjacency) {
		if(adjacencies.containsKey(node)){
			List<String> nodeAdj = adjacencies.get(node);
			if(!nodeAdj.contains(adjacency))
				nodeAdj.add(adjacency);
		} else {
			ArrayList<String> newNodeAdj = new ArrayList<String>();
			newNodeAdj.add(adjacency);
			adjacencies.put(node, newNodeAdj);
		}
	}

	/**
	 * Adds the adjacencies.
	 *
	 * @param node the node
	 * @param adjacencies the adjacencies
	 */
	public void addAdjacencies(String node, List<String> adjacencies) {
		for (String adjacency : adjacencies) {
			addAdjacency(node, adjacency);
		}
	}
	
	/**
	 * Generate dot.
	 *
	 * @return the string
	 */
	public String generateDot(){
		StringBuilder sb = new StringBuilder();
		sb.append("//========================================================\n");
		sb.append(GRAPH_TYPE).append(" {\n");
		sb.append(NODE_FORMAT).append("\n");
		sb.append(EDGE_FORMAT).append("\n\n");
		
		for (Entry<String, List<String>> entry : adjacencies.entrySet()) {
			String node = entry.getKey();
			List<String> nodeAdjs = entry.getValue();
			
			sb.append(node);
			
			if(nodeAdjs.isEmpty()){
				sb.append(";");
			} else{
				sb.append(" -> ");
				sb.append("{");
				Iterator<String> adjsIt = nodeAdjs.iterator();
				while (adjsIt.hasNext()) {
					String elem = adjsIt.next();
					sb.append(elem);
					if(adjsIt.hasNext())
						sb.append("; ");					
				}
				sb.append("}");
			}
			sb.append("\n");			
		}
		
		sb.append("}\n");
		sb.append("//========================================================\n");
		
		return sb.toString();
	}
	
	/**
	 * Prints the dot.
	 */
	public void printDot(){
		System.out.println(generateDot());
	}

}
