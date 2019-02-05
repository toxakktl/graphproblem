package kz.bts.GraphProblem;

import kz.bts.GraphProblem.data.Graph;
import kz.bts.GraphProblem.util.GraphSearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GraphProblemApplicationTests {

	@Autowired
	private Graph graph;

	@Autowired
	private GraphSearch graphSearch;

	@BeforeEach
	public void beforeEach(){

		graph = new Graph();
	}

	@Test
	@DisplayName("Add vertex (Type String) test")
	public void shouldAddVertexString(){
		String vertex = "A";
		graph.addVertex(vertex);
		Assertions.assertTrue(graph.getAdjacencyList().containsKey("A"));
	}

	@Test
	@DisplayName("Add vertex (Type Double) test")
	public void shouldAddVertexDouble(){
		Double vertex = 2.0;
		graph.addVertex(vertex);
		Assertions.assertTrue(graph.getAdjacencyList().containsKey(2.0));
	}

	@Test
	@DisplayName("Remove vertex (Type String) test")
	public void shouldRemoveVertexString(){
		String vertex = "A";
		graph.addVertex(vertex);
		graph.removeVertex(vertex);
		Assertions.assertFalse(graph.getAdjacencyList().containsKey("A"));
	}

	@Test
	@DisplayName("Remove vertex (Type Double) test")
	public void shouldRemoveVertexDouble(){
		Double vertex = 5.0;
		graph.addVertex(vertex);
		graph.removeVertex(vertex);
		Assertions.assertFalse(graph.getAdjacencyList().containsKey(5.0));
	}

	@Test
	@DisplayName("Graph size test")
	public void shouldRemturnGraphSize(){
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		Assertions.assertEquals(3,graph.getAdjacencyList().size());
	}

	@Test
	@DisplayName("Add an edge (Type String) test")
	public void shouldAddAnEdgeString(){
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addEdge("A", "B");
		List<String > list1 = ((ArrayList<String>)graph.getAdjacencyList().get("A"));
		List<String > list2 = ((ArrayList<String>)graph.getAdjacencyList().get("B"));
		Assertions.assertTrue(graph.getAdjacencyList().containsKey("A") &&
				graph.getAdjacencyList().containsKey("B") &&
				list1.contains("B") &&
				list2.contains("A"));
	}

	@Test
	@DisplayName("Add an edge (Type Double) test")
	public void shouldAddAnEdgeDouble(){
		graph.addVertex(1.0);
		graph.addVertex(2.0);
		graph.addEdge(1.0, 2.0);
		List<String > list1 = ((ArrayList<String>)graph.getAdjacencyList().get(1.0));
		List<String > list2 = ((ArrayList<String>)graph.getAdjacencyList().get(2.0));
		Assertions.assertTrue(graph.getAdjacencyList().containsKey(1.0) &&
				graph.getAdjacencyList().containsKey(2.0) &&
				list1.contains(2.0) &&
				list2.contains(1.0));
	}

	@Test
	@DisplayName("Remove an edge (Type String) test")
	public void shouldRemoveAnEdgeString(){
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addEdge("A", "B");
		graph.removeEdge("A", "B");
		List<String > list1 = ((ArrayList<String>)graph.getAdjacencyList().get("A"));
		List<String > list2 = ((ArrayList<String>)graph.getAdjacencyList().get("B"));
		Assertions.assertTrue(graph.getAdjacencyList().containsKey("A") &&
				graph.getAdjacencyList().containsKey("B") &&
				!list1.contains("B") &&
				!list2.contains("A"));
	}

	@Test
	@DisplayName("Remove an edge (Type Double) test")
	public void shouldRemoveAnEdgeDouble(){
		graph.addVertex(1.0);
		graph.addVertex(2.0);
		graph.addEdge(1.0, 2.0);
		graph.removeEdge(1.0, 2.0);
		List<String > list1 = ((ArrayList<String>)graph.getAdjacencyList().get(1.0));
		List<String > list2 = ((ArrayList<String>)graph.getAdjacencyList().get(2.0));
		Assertions.assertTrue(graph.getAdjacencyList().containsKey(1.0) &&
				graph.getAdjacencyList().containsKey(2.0) &&
				!list1.contains(2.0) &&
				!list2.contains(1.0));
	}

	@Test
	@DisplayName("Find path between two vertices")
	public void shouldFindPath(){
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("C", "D");

		List<String> paths = graphSearch.findPath("A", "D", graph);
		List<String> expectedList = Arrays.asList("A", "B", "C", "D");
		Assertions.assertTrue(paths.equals(expectedList));
	}

	@Test
	@DisplayName("Find path between two vertices")
	public void shouldFindPath2(){
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addVertex(6);

		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(1, 5);
		graph.addEdge(3, 5);
		graph.addEdge(3, 6);

		List<Integer> paths = graphSearch.findPath(1, 6, graph);
		List<Integer> expectedList = Arrays.asList(1, 2, 3, 6);
		Assertions.assertTrue(paths.equals(expectedList));
	}

	@Test
	@DisplayName("Find path between two vertices")
	public void shouldFindPathFalse(){
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");

		graph.addEdge("A", "B");
		graph.addEdge("B", "C");


		List<String> paths = graphSearch.findPath("A", "D", graph);
		Assertions.assertTrue(paths==null);
	}


}

