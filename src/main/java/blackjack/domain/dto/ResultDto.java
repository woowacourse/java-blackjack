package blackjack.domain.dto;

import blackjack.domain.Result;

import java.util.Objects;

public class ResultDto {
    private final String result;
    public ResultDto(Result result){
        this.result = translateResult(result);
    }

    private String translateResult(final Result result) {
        if (result == Result.LOSE) {
            return "패";
        }
        if (result == Result.DRAW) {
            return "무";
        }
        if (result == Result.WIN) {
            return "승";
        }
        throw new AssertionError();
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ResultDto resultDto = (ResultDto) o;
        return Objects.equals(result, resultDto.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
