package org.sc.grammars;

import java.util.*;

public class PushbackWrapper<E> implements PushbackIterator<E> {
	
	private LinkedList<E> pending;
	private Iterator<E> inner;
	
	public PushbackWrapper(Iterator<E> itr) { 
		inner = itr;
		pending = new LinkedList<E>();
		findNextValue();
	}
	
	private void findNextValue() { 
		while(pending.isEmpty() && inner.hasNext()) { 
			pending.addLast(inner.next());
		}
	}

	public void pushback(E value) {
		pending.addFirst(value);
	}

	public boolean hasNext() {
		return !pending.isEmpty();
	}

	public E next() {
		E value = pending.removeFirst();
		findNextValue();
		return value;
	}
	
	public void remove() {
		throw new UnsupportedOperationException("remove");
	}

}
