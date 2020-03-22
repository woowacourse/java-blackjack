package blackjack.view;

import blackjack.domain.result.ResultType;

import java.util.Objects;

public class ResultTypeWordMapper {
    private static final String NULL_ERR_MSG = "인자로 Null이 들어올 수 없습니다.";
    private static final String NOT_SUPPORTED_ENUM_ERR_MSG = "해당 Enum을 찾을 수 없습니다.";

    public static String resultToKorean(ResultType type) {
        Objects.requireNonNull(type, NULL_ERR_MSG);

        if (type == ResultType.BLACKJACK) {
            return "블랙잭";
        }
        if (type == ResultType.WIN) {
            return "승";
        }
        if (type == ResultType.LOSE) {
            return "패";
        }
        if (type == ResultType.DRAW) {
            return "무";
        }

        throw new EnumConstantNotPresentException(ResultType.class, NOT_SUPPORTED_ENUM_ERR_MSG);
    }
}
