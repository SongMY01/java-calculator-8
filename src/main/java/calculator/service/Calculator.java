package calculator.service;

import calculator.input.InputValidator.ParsedInput;

public class Calculator {

    public int add(ParsedInput parsedInput) {
        String delimiter = parsedInput.getDelimiter();
        String expression = parsedInput.getExpression();

        if (expression.isEmpty()) return 0;

        int sum = 0;
        String[] tokens = expression.split(delimiter);

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            int number = Integer.parseInt(token);
            sum += number;
        }
        return sum;
    }
}