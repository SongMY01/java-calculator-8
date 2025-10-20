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
            return;
        }

        // 기본 구분자 형식 검증 로직
    }

}
