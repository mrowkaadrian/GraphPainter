/*
 * Program: Aplikacja do tworzenia i modyfikowania prostych grafów przy pomocy GUI (Swing)
 *    Plik: Graph.java
 *
 *   Autor: Adrian Mrówka
 *  Indeks: 234939
 *    Data: grudzień 2018 r.
 */

import javax.swing.*;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Graph implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Node> nodes;
	private List<Edge> edges;
	
	public Graph() {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public void removeNode(Node node) {
		nodes.remove(node);
	}

	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}

	public void renameNode(Node node) {
		String name = JOptionPane.showInputDialog("Podaj nowa nazwe:");
		node.setName(name);
	}
	
	public Node[] getNodes() {
		Node [] array = new Node[0];
		return nodes.toArray(array);
	}

	public Edge[] getEdges() {
		Edge [] array = new Edge[0];
		return edges.toArray(array);
	}
	
	public void draw(Graphics g) {
		for (Edge edge : edges) {
			edge.draw(g);
		}
		for (Node node : nodes) {
			node.draw(g);
		}
	}

}
