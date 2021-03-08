package blackjack.domain;

public enum UserAnswer {

    HIT("y"),
    STAY("n");

    private final String answer;

    UserAnswer(String answer) {
        this.answer = answer;
    }

    public static boolean isHit(final String input) {
        String lowerInput = input.toLowerCase();
        validateHitOrStay(lowerInput);
        return HIT.answer.equals(input);
    }

    private static void validateHitOrStay(final String input) {
        if (!input.equals(HIT.answer) && !input.equals(STAY.answer)) {
            throw new IllegalArgumentException("요청은 " + HIT.answer + "또는 " + STAY.answer + "이어야 합니다.");
        }
    }

    public String getAnswer() {
        return answer;
    }
}
