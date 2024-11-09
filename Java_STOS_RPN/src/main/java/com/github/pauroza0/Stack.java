package com.github.pauroza0;

import java.util.Arrays;

public class Stack<T> {
    private T[] elements;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack() {
        this.elements = (T[]) new Object[0];
        this.top = -1;
    }

    public void push(T element) {
        elements = Arrays.copyOf(elements, top + 2);
        top++;
        elements[top] = element;
    }

    public T pop() {
        if (top < 0) {
            throw new IllegalStateException("Stos jest pusty");
        }
        T value = elements[top];
        elements = Arrays.copyOf(elements, top--);
        return value;
    }

    public T peek() {
        if (top < 0) {
            throw new IllegalStateException("Stos jest pusty");
        }
        return elements[top];
    }

    public int getTop() {
        return top;
    }
}
