package blackjack.domain.card;

import java.util.Objects;

public class Score {
    private final static int BLACK_JACK = 21;

    private final int value;

    public Score(int value){
        if(value < 0){
            throw new IllegalArgumentException("유효하지 않은 점수입니다.");
        }

        this.value = value;
    }

    public boolean isBust() {
        return value > BLACK_JACK;
    }

    public boolean isBlackJack(){
        return value == BLACK_JACK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
