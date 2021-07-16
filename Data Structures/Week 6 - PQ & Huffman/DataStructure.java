package huffman;

import java.io.Serializable;

public interface DataStructure<E extends Comparable<E>> extends Serializable {
    public String stringify();
    public String debug();
}
