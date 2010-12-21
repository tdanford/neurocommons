package org.sc.grammars;

import java.util.Iterator;

public interface PushbackIterator<E> extends Iterator<E> {
	public void pushback(E value);
}
