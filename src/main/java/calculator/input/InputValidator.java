package calculator.input;

public class InputValidator {

    public ParsedInput validate(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력은 null일 수 없습니다.");
        }

        // 빈 문자열은 허용 (결과 0)
        if (input.isEmpty()) {
            return new ParsedInput(",|:", "");
        }

        // 기본 구분자
        String delimiter = ",|:";
        String expression = input;

        // 커스텀 구분자 형식인 경우
        if (input.startsWith("//")) {
            int delimiterSectionEnd = input.indexOf("\\");

            if (delimiterSectionEnd <= 2) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다. 예: //;\\n1;2;3");
            }

            delimiter = input.substring(2, delimiterSectionEnd);

            if (delimiter.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
            }

            expression = input.substring(delimiterSectionEnd + 2);

        }

        // 기본 구분자 형식 검증
        validateCharacters(expression, delimiter);
        return new ParsedInput(delimiter, expression);
    }

    private void validateCharacters(String expression, String customDelimiter) {
        if (expression.contains(",,") || expression.contains("::") ||
                (customDelimiter != null && expression.contains(customDelimiter + customDelimiter))) {
            throw new IllegalArgumentException("연속된 구분자는 허용되지 않습니다.");
        }
        // 구분자로 시작하거나 끝나는 경우 방지
        if (expression.startsWith(",") || expression.startsWith(":") || expression.endsWith(",") || expression.endsWith(":") ||
                (customDelimiter != null && (expression.startsWith(customDelimiter) || expression.endsWith(customDelimiter)))) {
            throw new IllegalArgumentException("식은 구분자로 시작하거나 끝날 수 없습니다.");
        }

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

    public static class ParsedInput {
        private final String delimiter;
        private final String expression;

        public ParsedInput(String delimiter, String expression) {
            this.delimiter = delimiter;
            this.expression = expression;
        }

        public String getDelimiter() {
            return delimiter;
        }

        public String getExpression() {
            return expression;
        }
    }

}