package com.github.pauroza0;

public class RPNCalculator {
    private Stack<Integer> stack;

    public RPNCalculator() {
        this.stack = new Stack<>();
    }

    public int calculateExpression(String expression) {
        String[] elements = parseAndValidateRPNExpression(expression);
        for (String element : elements) {
            evaluate(element);
        }
        if (stack.getTop() != 0) {
            throw new IllegalArgumentException("Niepoprawny stosunek operatorów do operandów");
        }
        return stack.pop();
    }

    private void evaluate(String symbol) {
        try {
            switch (symbol) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int a = stack.pop();
                    if (a == 0) {
                        throw new IllegalArgumentException("Dzielenie przez 0");
                    }
                    stack.push(stack.pop() / a);
                    break;
                case "%":
                    int m = stack.pop();
                    if (m == 0) {
                        throw new IllegalArgumentException("Modulo 0");
                    }
                    stack.push(((stack.pop() % m) + m) % m);
                    break;
                case "^":
                    int pow = stack.pop();
                    stack.push ((int) Math.pow(stack.pop(), pow));
                    break;
                default:
                    stack.push(Integer.parseInt(symbol));
                    break;
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Za dużo operatorów, stos jest pusty");
        }
    }


    private String[] parseAndValidateRPNExpression(String expression) {
        String[] elements = expression.split(" ");
        for (String element : elements) {
            if (!element.matches("-?[0-9]+|[+\\-*/%^]")) {
                throw new IllegalArgumentException("Niedozwolony znak");
            }
        }
        if (elements.length < 3 || !elements[0].matches("-?[0-9]+") || !elements[1].matches("-?[0-9]+")) {
            throw new IllegalArgumentException("Za mało operandów na początku ciągu");
        }
        return elements;
    }
}
