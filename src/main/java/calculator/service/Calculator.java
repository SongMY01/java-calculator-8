package calculator.service;

import calculator.input.InputValidator.ParsedInput;

import java.util.regex.Pattern;

public class Calculator {

    public int add(ParsedInput parsedInput) {
        String delimiter = parsedInput.getDelimiter();
        String expression = parsedInput.getExpression();

        if (expression.isEmpty()) return 0;

        int sum = 0;
        String[] tokens = expression.split(Pattern.quote(delimiter));

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            int number = Integer.parseInt(token);
            sum += number;
        }
        return sum;
    }
}