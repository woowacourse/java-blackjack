package client;

import static util.ExceptionConstants.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import participant.value.Money;

public class InputParser {
    private static final String INVALID_ANSWER_TYPE = "응답은 y 혹은 n으로 해주세요.";
    private static final String INVALID_PRICE_TYPE = "베팅 금액은 정수로 입력해주세요.";
    private static final String NAME_SEPARATOR = ",";
    private static final Map<String, AnswerType> ANSWER_TYPE_PARSER = Map.of(
            "y", AnswerType.YES,
            "n", AnswerType.NO
    );

    public List<String> parsePlayerNames(String rawPlayerNames) {
        return Arrays.stream(rawPlayerNames.split(NAME_SEPARATOR))
                .map(String::trim)
                .toList();
    }

    public AnswerType parseAnswerType(String rawAnswer) {
        AnswerType answerType = ANSWER_TYPE_PARSER.get(rawAnswer);
        validateAnswerType(answerType);
        return answerType;
    }

    private void validateAnswerType(AnswerType answerType) {
        if (answerType == null) {
            throw new IllegalArgumentException(ERROR_HEADER + INVALID_ANSWER_TYPE);
        }
    }

    public int parsePrice(String rawPrice) {
        try {
            return Integer.parseInt(rawPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_HEADER + INVALID_PRICE_TYPE);
        }
    }
}
