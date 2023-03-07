package blackjack.view;

import blackjack.response.ResultConvertStrategy;

public class ResultConvertStrategyImpl implements ResultConvertStrategy {

    @Override
    public String convertResult(final String result) {
        switch (result) {
            case "WIN":
                return "승";
            case "LOSE":
                return "패";
            case "DRAW":
                return "무";
            default:
                throw new IllegalArgumentException("올바르지 않은 결과입니다.");
        }
    }
}
