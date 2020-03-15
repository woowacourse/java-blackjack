package domain.answer;

public class Answer {
    private String answer;

    public Answer(String answer) {
        validateAnswer(answer);
        this.answer = answer;
    }

    private static void validateAnswer(String answer) {
        if (!answer.equals("y") && !answer.equals("n")) {
            throw new IllegalArgumentException("예는 y,아니오는 n 로 입력해주세요");
        }
    }


    public String getAnswer() {
        return answer;
    }
}
