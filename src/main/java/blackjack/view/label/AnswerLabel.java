package blackjack.view.label;

import blackjack.model.game.HitAnswer;
import java.util.Arrays;

public enum AnswerLabel {
    YES("y", HitAnswer.HIT),
    NO("n", HitAnswer.STAY);

    private final String label;
    private final HitAnswer hitAnswer;

    AnswerLabel(String label, HitAnswer hitAnswer) {
        this.label = label;
        this.hitAnswer = hitAnswer;
    }

    public static HitAnswer asHitAnswer(String label) {
        return Arrays.stream(values())
                .filter(answerLabel -> answerLabel.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("부적절한 응답입니다."))
                .hitAnswer;
    }
}
