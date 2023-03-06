package blackjack.response;

import blackjack.domain.ResultType;

public class ResultTypeResponse {

    private final String result;

    private ResultTypeResponse(final String result) {
        this.result = result;
    }

    public static ResultTypeResponse from(final ResultType resultType) {
        return new ResultTypeResponse(resultType.name());
    }

    public String getResult(final ResultConvertStrategy resultConvertStrategy) {
        return resultConvertStrategy.convertResult(result);
    }
}
