package calculator.input;

public class InputValidator {

    public void validate(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력은 null일 수 없습니다.");
        }

        // 빈 문자열은 허용 (결과 0)
        if (input.isEmpty()) {
            return;
        }

        // 커스텀 구분자 형식인 경우
        if (input.startsWith("//")) {
            validateCustomDelimiterExpression(input);
            return;
        }

        // 기본 구분자 형식 검증 로직
        validateCharacters(input, null);
        validateDelimiterSequence(input);
    }
    private void validateCustomDelimiterExpression(String input) {
        int delimiterSectionEnd = input.indexOf('\n');

        if (delimiterSectionEnd <= 2) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다. 예: //;\\n1;2;3");
        }

        String delimiter = input.substring(2, delimiterSectionEnd);

        if (delimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
        }

        String expression = input.substring(delimiterSectionEnd + 1);

        validateCharacters(expression, delimiter);
        validateDelimiterSequence(expression);
    }

    private void validateCharacters(String expression, String customDelimiter) {
        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                continue; // 숫자는 항상 허용
            }

            if (ch == ',' || ch == ':' ||
                    (customDelimiter != null && ch == customDelimiter.charAt(0))) {
                continue; // 기본 구분자 또는 커스텀 구분자는 허용
            }

            if (ch == '-') {
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            }

            throw new IllegalArgumentException("지원하지 않는 문자가 포함되어 있습니다: '" + ch + "'");
        }
    }

    private void validateDelimiterSequence(String expression) {
        // 연속된 구분자 방지
        if (expression.contains(",,") || expression.contains("::") || expression.contains(";;")) {
            throw new IllegalArgumentException("연속된 구분자는 허용되지 않습니다.");
        }

        // 구분자로 시작하거나 끝나는 경우 방지
        if (expression.startsWith(",") || expression.startsWith(":") || expression.startsWith(";")
                || expression.endsWith(",") || expression.endsWith(":") || expression.endsWith(";")) {
            throw new IllegalArgumentException("식은 구분자로 시작하거나 끝날 수 없습니다.");
        }
    }

}
