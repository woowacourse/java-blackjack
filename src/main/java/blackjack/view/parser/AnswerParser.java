package blackjack.view.parser;

import blackjack.model.Answer;
import java.util.Map;

public class AnswerParser {

    private static final Map<String, Answer> ANSWERS = Map.of(
            "y", Answer.YES,
            "n", Answer.NO
    );

    public static Answer parseToModel(String rawAnswer) {
        if (!ANSWERS.containsKey(rawAnswer)) {
            throw new IllegalArgumentException("부적절한 응답입니다.");
        }

        return ANSWERS.get(rawAnswer);
    }
}
