package blackjack.dto;

import blackjack.domain.GameResult;
import java.util.Objects;

public class GameResultDto {

    private final String value;

    public GameResultDto(String gameResult) {
        this.value = gameResult;
    }

    public static GameResultDto from(GameResult gameResult) {
        return new GameResultDto(gameResult.getValue());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResultDto that = (GameResultDto) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
