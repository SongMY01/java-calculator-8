package calculator;

import calculator.input.InputReader;
import calculator.input.InputValidator;

public class Application {
    private final InputReader inputReader;
    private final InputValidator inputValidator;

    public Application(InputReader inputReader, InputValidator inputValidator) {
        this.inputReader = inputReader;
        this.inputValidator = inputValidator;
    }

    public static void main(String[] args) {
        Application application = new Application(new InputReader(), new InputValidator());
        application.run();
    }

    private void run() {
        try {
            String input = inputReader.readInput();
            inputValidator.validate(input);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            // 종료는 return으로 (System.exit() 금지)
        }
    }
}