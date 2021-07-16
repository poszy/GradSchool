package huffman;

import java.io.Serializable;



public class HuffmanCodeForBytes implements Serializable {

	private static final long serialVersionUID = 1L;

	public static int[] countFrequency(String s) {
		int[] frequency = new int[256];

		for(int i = 0, n = s.length() ; i < n ; i++) {
		    char c = s.charAt(i);
		    frequency[c]++;
		}

		return frequency;
	}

	public static HuffmanRoot buildTree(int[] frequency) throws DataStructureException {
		//Build a priority queue of the text;
		PriorityQueue<AsciiCodeNode> pq =
			new BinaryHeap<AsciiCodeNode>(256); // 256 for 8 bit code

		// put initial nodes in tree
		for (int i = 0; i < frequency.length; i++) {
			if (frequency[i] != 0) {
				AsciiCodeNode acn = new AsciiCodeNode(i, frequency[i]);

				pq.insert(acn);
			}
		}

		// build tree from priority queue

		if (pq.isEmpty()) {
			throw new DataStructureException("Your input string did not have any characters in it");
		}
		else if (pq.size() == 1) {
			return pq.deleteFirst();
		}

		int nextAvailable = 256;  // Give each node a unique name
		System.out.println("\n" + "----- Start Debugging -----" + "\n");

		while (pq.size() > 1) {

            // deleteFirst on two nodes,
			AsciiCodeNode a  = pq.findFirst();
			System.out.println("\n"+"First Node " + a);
			pq.deleteFirst();

			AsciiCodeNode b  = pq.findFirst();
			System.out.println("Second Node " + b);
			pq.deleteFirst();

			System.out.println("---------- Creating New Node");

			// create a new node whose children are these two nodes,
			System.out.println("---------- Pushing a:  " + a);
			System.out.println("---------- Pushing b:  " + b);

			int freq = a.getFrequency() + b.getFrequency();


			AsciiCodeNode mAsciiCodeNode = new AsciiCodeNode(nextAvailable,freq,a,b);

			// and the frequency is the sum of the two nodes
			System.out.println("freqeuecny of nodes: " + mAsciiCodeNode.getFrequency());

			// Push node
			System.out.println("Inserting Node in PQ: " + mAsciiCodeNode + "\n");
			pq.insert(mAsciiCodeNode);

			nextAvailable = nextAvailable + 1;

			// Printing nodes in tree for debugging
			DsIterator<AsciiCodeNode> i = pq.iterator();
			while(i.hasNext()) {

				System.out.println("Nodes in tree: " + i.next());

			}

		}
		// What is left should be the root of a Huffman tree
		return pq.deleteFirst();
	}

	public static String[] buildHuffmanCode(HuffmanRoot hr) throws DataStructureException {
		String[] table = new String[256];
		generateCode((AsciiCodeNode)hr, "", table);
		System.out.println("----- End Debugging -----" + "\n");
		return table;
	}

	public static String encodeString(String[] huffmanCode, String s) {

		// Translate the String s by translating each character in
		// s to the corresponding Huffman code.  Append all the
		String x = "";
		for (int i = 0; i < huffmanCode.length; i++) {

			if (huffmanCode[i] != null){

				x = x + huffmanCode[i];

			}
		}
		return x;
	}

	public static String decodeString(HuffmanRoot hr, String s) {

		StringBuilder decoded = new StringBuilder();

		AsciiCodeNode mNode = (AsciiCodeNode) hr;

		for (int i = 0; i < s.length(); i++) {
			int j = Integer.parseInt(String.valueOf(s.charAt(i)));

			// If the next character in is is a 0, node = node.left.
			if (j == 0) {
				mNode = mNode.getLeft();

				// Stop when node.left is null.
				// The character in the node will be the original character.
				if (mNode.getLeft() == null && mNode.getRight() == null) {
					decoded.append(mNode.getChar());
					mNode = (AsciiCodeNode) hr;
				}
			}

			// If the character is 1,
			// node = node.right.
			if (j == 1) {
				mNode = mNode.getRight();
				if (mNode.getLeft() == null && mNode.getRight() == null) {
					decoded.append(mNode.getChar());
					mNode = (AsciiCodeNode) hr;
				}
			}
		}

		return decoded.toString();

	}

	private static void generateCode(AsciiCodeNode acn, String code, String[] table) {


		// if acn.left is null, acn.right must be null, and this is
		// a left node.  call acn.getAsciiCode to get the ASCII code
		if (acn.getLeft() == null) {
			int mACode = acn.getAsciiCode();

			// set the ascii code to its string value
			System.out.print("\n"+ "generateCode: Ascii Code - " + mACode + "\n");


			// The left ascii code is the index for the code in the table array.
			// The string for this code should be in the argumentString "code"

			// store acn position in the table the coded string
			// set asccii code to its string value

			table[mACode] = code;

			System.out.print("generateCode: HuffmanCode - " + code + "\n");


		}

		// recursive walk the tree for each acn node by calling
		// generateCode for the left and right nodes.  Add a "0" to
		// the code string when you walk left, and a "1" when you walk right.
		else {
			//System.out.print("else code : " + code + " ");

			generateCode(acn.left,code + "0" ,table);
			generateCode(acn.right,code + "1" ,table);
		}



	}

	public static interface HuffmanRoot {
	}

	private static class AsciiCodeNode
	    implements Comparable<AsciiCodeNode>,
	    HuffmanRoot {
		private int asciiCode;
		private int frequency;
		private AsciiCodeNode left, right;

		public AsciiCodeNode(int asciiCode, int frequency) {
			this.asciiCode = asciiCode;
			this.frequency = frequency;
			this.left = null;
			this.right = null;
		}

		public AsciiCodeNode(int asciiCode, int frequency,
			   AsciiCodeNode left,
			   AsciiCodeNode right) {
			this.asciiCode = asciiCode;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}

		public int getAsciiCode() {
			return asciiCode;
		}

		public int getFrequency() {
			return frequency;
		}

		public AsciiCodeNode getLeft() {
			return left;
		}

		public AsciiCodeNode getRight() {
			return right;
		}

		public int compareTo(AsciiCodeNode n) {
			if (this.frequency == n.frequency) {
				return this.asciiCode - n.asciiCode;
			}
			return this.frequency - n.frequency;
		}

		public String toString() {
			return ((char) asciiCode + ";" + frequency);
		}

		public String getChar(){
			return ((char) asciiCode + "");
		}
	}
}
