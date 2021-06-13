import java.util.Iterator;

public class ForEachSimpleExample<E> implements Iterable<E> {
	E[] elements;
	public static void main(String[] args) {
		ForEachSimpleExample<String> fese = new ForEachSimpleExample<String>();

		for (Iterator<String> i = fese.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}

		for (String s: fese) {
			System.out.println(s);
		}

	}

	@SuppressWarnings("unchecked")
	public ForEachSimpleExample() {
		elements = (E[]) new String[3];
		elements[0] = (E) "ABC";
		elements[1] = (E) "DEF";
		elements[2] = (E) "GHI";
	}

	public Iterator<E> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<E> {
        int first = 0;
		@Override
		public boolean hasNext() {
			if (first < elements.length) {
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public E next() {
			E retS = elements[first];
			first++;
			return retS;
		}
	}

}
