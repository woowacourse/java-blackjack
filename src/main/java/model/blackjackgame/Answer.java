package model.blackjackgame;

import java.util.Objects;

public class Answer {

    private static final String HIT_WORD = "y";
    private static final String NO_HIT_WORD = "n";

    private final boolean isHit;

    public Answer(String answer) {
        validate(answer);
        this.isHit = changeFormatAnswer(answer);
    }

    private void validate(String answer) {
        if (!HIT_WORD.equals(answer) && !NO_HIT_WORD.equals(answer)) {
            throw new IllegalArgumentException("y 와 n 이외의 문자를 입력할 수 없습니다");
        }
    }

    private boolean changeFormatAnswer(String answer) {
        return Objects.equals(answer, HIT_WORD);
    }

    public boolean isHit() {
        return isHit;
    }
}
