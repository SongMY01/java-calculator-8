package calculator;

import calculator.input.InputReader;
import calculator.input.InputValidator;
import calculator.input.InputValidator.ParsedInput;
import calculator.service.Calculator;

public class Application {
    private final InputReader inputReader;
    private final InputValidator inputValidator;
    private final Calculator calculator;


    public Application(InputReader inputReader, InputValidator inputValidator, Calculator calculator) {
        this.inputReader = inputReader;
        this.inputValidator = inputValidator;
        this.calculator = calculator;
    }

    public static void main(String[] args) {
        Application application = new Application(
                new InputReader(),
                new InputValidator(),
                new Calculator()
        );
        application.run();
    }

    private void run() {
        try {
            String input = inputReader.readInput();
            ParsedInput parsedInput = inputValidator.validate(input);
            int result = calculator.add(parsedInput);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            // 종료는 return으로 (System.exit() 금지)
        }
    }
}