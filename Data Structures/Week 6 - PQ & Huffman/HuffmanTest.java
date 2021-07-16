package huffman;
public class HuffmanTest {

	public static void main(String[] args) throws DataStructureException {
		String str = "luis";
		int[] test = HuffmanCodeForBytes.countFrequency(str);
        for (int i = 0; i < test.length; i++) {
        	if (test[i] != 0)
        	    System.out.println((char)i + " " + test[i] );
        }
        HuffmanCodeForBytes.HuffmanRoot root = HuffmanCodeForBytes.buildTree(test);
        System.out.println("Root Node: " + root.toString());

        String[] hc = HuffmanCodeForBytes.buildHuffmanCode(root);

        System.out.println("String with corresponding HuffmanCodes: ");
        for (int i = 0; i < hc.length; i++) {
        	String s = hc[i];
        	if (s != null){
        	    System.out.println((char)i + " : " + s);

			}
        }

        String encoded = HuffmanCodeForBytes.encodeString(hc, str );
        System.out.println("\n" +"Encoded String: "  + encoded);

        String decoded = HuffmanCodeForBytes.decodeString(root, encoded);
        System.out.println("\n" +"Decoded String: " + decoded);

	}

}


