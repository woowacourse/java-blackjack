package domain.answer;

public class Answer {
    private final String answer;

    public Answer(String answer) {
        validateAnswer(answer);
        this.answer = answer;
    }

    private static void validateAnswer(String answer) {
        if (!"y".equals(answer) && !"n".equals(answer)) {
            throw new IllegalArgumentException("예는 y,아니오는 n 로 입력해주세요");
        }
    }

    public boolean isYes() {
        AnswerType answerType = AnswerType.answerValueOf(this.answer);

        return answerType.isYes();
    }
}
