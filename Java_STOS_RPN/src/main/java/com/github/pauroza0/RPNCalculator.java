package com.github.pauroza0;

public class RPNCalculator {
    private Stack stack;

    public RPNCalculator() {
        this.stack = new Stack();
    }

    public int calculateExpression(String expression) {
        String[] expression1 = setExpression(expression);
        for (String element : expression1) {
            if (element.matches("-?[0-9]+")) {
                stack.push(element);
            } else {
                evaluate(element);
            }
        }
        if (stack.getTop() != 0) {
            throw new IllegalArgumentException("Niepoprawny stosunek operatorów do operandów");
        }
        return Integer.parseInt(stack.pop());
    }

    private void evaluate(String operator) { //
        try {
            int a = Integer.parseInt(stack.pop());
            int b = Integer.parseInt(stack.pop());
            int result = 0;

            switch (operator) {
                case "+":
                    result = b + a;
                    break;
                case "-":
                    result = b - a;
                    break;
                case "*":
                    result = b * a;
                    break;
                case "/":
                    if (a == 0) {
                        throw new IllegalArgumentException("Dzielenie przez 0");
                    }
                    result = b / a;
                    break;
                case "%":
                    if (a == 0) {
                        throw new IllegalArgumentException("Modulo 0");
                    }
                    result = ((b % a) + a) % a;
                    break;
                case "^":
                    result = (int) Math.pow(b, a);
            }
            stack.push(String.valueOf(result));
        } catch(IllegalStateException e){
            throw new IllegalStateException("Za dużo operatorów, stos jest pusty");
        }
    }


    public String[] setExpression(String expression) { //sprawdzic czy da sie obliczyc
        String[] input = expression.split(" ");
        for (String element : input) {
            if (!element.matches("-?[0-9]+|[+\\-*/%^]")) {
                throw new IllegalArgumentException("Niedozwolony znak");
            }
        }
        if (input.length < 3 || !input[0].matches("-?[0-9]+") || !input[1].matches("-?[0-9]+")) {
            throw new IllegalArgumentException("Za mało operandów na początku ciągu");
        }
        return input;
    }
}
