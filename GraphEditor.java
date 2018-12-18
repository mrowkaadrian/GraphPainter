/*
 * Program: Aplikacja do tworzenia i modyfikowania prostych grafów przy pomocy GUI (Swing)
 *    Plik: GraphEditor.java
 *
 *   Autor: Adrian Mrówka
 *  Indeks: 234939
 *    Data: grudzień 2018 r.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.io.*;

public class GraphEditor extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private static final String APP_AUTHOR = "Autor: Adrian Mrowka\n  Data: grudzien 2018";
	private static final String APP_TITLE = "Prosty edytor grafow";
	
	private static final String APP_INSTRUCTION =
			"                  O P I S   P R O G R A M U \n\n" + 
	        "Aktywna klawisze:\n" +
			"   strzalki ==> przesuwanie wszystkich kolek\n" +
			"   SHIFT + strzalki ==> szybkie przesuwanie wszystkich kolek" + "\n\n" +
			"ponadto gdy kursor wskazuje kolo:\n" +
			"   DEL   ==> kasowanie kola\n" +
			"   +, -   ==> powiekszanie, pomniejszanie kola\n" +
			"   r,g,b ==> zmiana koloru kola\n\n" +
			"Operacje myszka:\n" +
			"   przeciaganie ==> przesuwanie wszystkich kol\n" +
			"   PPM ==> tworzenie nowego kola w niejscu kursora\n" +
	        "ponadto gdy kursor wskazuje kolo:\n" +
	        "   przeciaganie ==> przesuwanie kola\n" +
			"   PPM ==> zmiana koloru kola\n" +
	        "                   lub usuwanie kola\n";
	
	
	public static void main(String[] args) {
		new GraphEditor();
	}

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGraph = new JMenu("Graph");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuNew = new JMenuItem("New", KeyEvent.VK_N);
	private JMenuItem menuShowExample = new JMenuItem("Example", KeyEvent.VK_X);
	private JMenuItem menuExit = new JMenuItem("Exit", KeyEvent.VK_E);
	private JMenuItem menuListOfNodes = new JMenuItem("List of Nodes&Edges", KeyEvent.VK_N);
	private JMenuItem menuAuthor = new JMenuItem("Author", KeyEvent.VK_A);
	private JMenuItem menuInstruction = new JMenuItem("Instruction", KeyEvent.VK_I);

	// TO IMPLEMENT -----------------------------------------------------------------
	private JMenuItem menuSave = new JMenuItem("Save", KeyEvent.VK_S);
	private JMenuItem menuOpen = new JMenuItem("Open", KeyEvent.VK_O);
	// ----------------------------------------------------------------- TO IMPLEMENT

	
	private GraphPanel panel = new GraphPanel();
	
	
	public GraphEditor() {
		super(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		setContentPane(panel);
		createMenu();
		showBuildingExample();
		setVisible(true);
	}

	private void showList(Graph graph) {
		Node node_array[] = graph.getNodes();
		Edge edge_array[] = graph.getEdges();
		int i = 0;
		StringBuilder message = new StringBuilder("Liczba wezlow: " + node_array.length + "\n");
		for (Node node : node_array) {
			message.append(node + "    ");
			if (++i % 5 == 0)
				message.append("\n");
		}
		message.append("\nLiczba krawedzi: " + edge_array.length + "\n");
		for (Edge edge : edge_array) {
			message.append(edge + "    \n");
		}
		JOptionPane.showMessageDialog(this, message, APP_TITLE + " - Lista wezlow i krawedzi", JOptionPane.PLAIN_MESSAGE);
	}

	private void createMenu() {
		menuNew.addActionListener(this);
		menuShowExample.addActionListener(this);
		menuExit.addActionListener(this);
		menuListOfNodes.addActionListener(this);
		menuAuthor.addActionListener(this);
		menuInstruction.addActionListener(this);
		menuSave.addActionListener(this);
		menuOpen.addActionListener(this);
		
		menuGraph.setMnemonic(KeyEvent.VK_G);
		menuGraph.add(menuNew);
		menuGraph.add(menuShowExample);
		menuGraph.addSeparator();
		menuGraph.add(menuListOfNodes);
		menuGraph.addSeparator();
		menuGraph.add(menuSave);
		menuGraph.add(menuOpen);
		menuGraph.addSeparator();
		menuGraph.add(menuExit);

		
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.add(menuInstruction);
		menuHelp.add(menuAuthor);
		
		menuBar.add(menuGraph);
		menuBar.add(menuHelp);
		setJMenuBar(menuBar);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == menuNew) {
			panel.setGraph(new Graph());
		}
		if (source == menuShowExample) {
			showBuildingExample();
		}
		if (source == menuListOfNodes) {
			showList(panel.getGraph());
		}
		if (source == menuSave) {
			saveToFile(panel.getGraph());
		}
		if (source == menuOpen) {
			openFromFile(panel.getGraph());
		}
		if (source == menuAuthor) {
			JOptionPane.showMessageDialog(this, APP_AUTHOR, APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
		}
		if (source == menuInstruction) {
			JOptionPane.showMessageDialog(this, APP_INSTRUCTION, APP_TITLE, JOptionPane.PLAIN_MESSAGE);
		}
		if (source == menuExit) {
			System.exit(0);
		}
	}

	private void showBuildingExample() {
		Graph graph = new Graph();

		Node n1 = new Node(100, 100);
		Node n2 = new Node(100, 200);
		n2.setColor(Color.CYAN);
		Node n3 = new Node(200, 100);
		n3.setR(20);
		Node n4 = new Node(200, 250);
		n4.setColor(Color.GREEN);
		n4.setR(30);
		Node n5 = new Node(250, 250);
		n5.setColor(Color.RED);
		n5.setR(10);

		Edge e1 = new Edge(n1, n2);


		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		graph.addNode(n4);
		graph.addNode(n5);

		graph.addEdge(e1);


		panel.setGraph(graph);
	}

	private void saveToFile(Graph graph) {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("graph.txt"))){
            oos.writeObject(graph);
            System.out.println("Pomyslnie zapisano do pliku");
        } catch (Exception e) {
            System.err.println("Nie mozna zapisac do pliku!");
            e.printStackTrace();
        }
	}

	private void openFromFile(Graph graph) {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("graph.txt"))) {
	        Graph newGraph = (Graph)ois.readObject();
	        panel.setGraph(newGraph);
            System.out.println("Pomyslnie wczytano z pliku");
        } catch (Exception e) {
	        System.err.println("Nie mozna wczytac z pliku!");
	        e.printStackTrace();
        }
        panel.repaint();
	}
}
