package br.com.plugins;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E>, Cloneable {

	public static final int CAPACITY = 4;
	private E[] data;
	private int t = -1;

	public ArrayStack() {
		this(CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public int size() {
		return (t + 1);
	}

	public boolean isEmpty() {
		return (t == -1);
	}

	public void push(E e) {
		if (size() == data.length)
			throw new IllegalStateException("Stack is full");
		data[++t] = e;
	}

	public E top() {
		if (isEmpty())
			return null;
		return data[t];
	}

	public E pop() {
		if (isEmpty())
			return null;
		E answer = data[t];
		data[t] = null;
		t--;
		return answer;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		for (int j = t; j >= 0; j--) {
			sb.append(data[j]);
			if (j > 0)
				sb.append(", ");
		}
		sb.append(")");
		return sb.toString();
	}
        
        public E[] getArray() {
            return data;
	}

        @Override
        public Object clone(){
            ArrayStack<E> v = new ArrayStack<E>();
            v.data = Arrays.copyOf(data, CAPACITY);
            v.t = t;
            return v;
        }
        
        
        
}
