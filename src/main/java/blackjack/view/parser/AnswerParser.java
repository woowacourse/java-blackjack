package blackjack.view.parser;

import blackjack.model.game.HitAnswer;
import java.util.Map;

public class AnswerParser {

    private static final Map<String, HitAnswer> ANSWERS = Map.of(
            "y", HitAnswer.HIT,
            "n", HitAnswer.STAND
    );

    public static HitAnswer parseToModel(String rawAnswer) {
        if (!ANSWERS.containsKey(rawAnswer)) {
            throw new IllegalArgumentException("부적절한 응답입니다.");
        }

        return ANSWERS.get(rawAnswer);
    }
}
