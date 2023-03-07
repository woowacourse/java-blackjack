package blackjack.response;

import blackjack.domain.ResultType;
import java.util.Objects;

public class ResultTypeResponse {

    private final String result;

    private ResultTypeResponse(final String result) {
        this.result = result;
    }

    public static ResultTypeResponse from(final ResultType resultType) {
        return new ResultTypeResponse(resultType.name());
    }

    public static ResultTypeResponse from(final String resultType) {
        return new ResultTypeResponse(resultType);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ResultTypeResponse that = (ResultTypeResponse) o;
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }

    public String getResult(final ResultConvertStrategy resultConvertStrategy) {
        return resultConvertStrategy.convertResult(result);
    }
}
