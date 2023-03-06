package blackjack.model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardScoreTest {

    @Test
    @DisplayName("에이스가 포함된 점수계산에서 작은 점수를 가져온다.")
    void smallScore() {
        //given
        List<CardNumber> numbers = List.of(CardNumber.ACE, CardNumber.FOUR, CardNumber.JACK);
        CardScore score = new CardScore(numbers);

        //when
        int smallScore = score.getSmallScore();

        //then
        assertThat(smallScore).isEqualTo(1 + 4 + 10);
    }

    @Test
    @DisplayName("에이스가 포함된 점수계산에서 큰 점수를 가져온다.")
    void bigScore() {
        //given
        List<CardNumber> numbers = List.of(CardNumber.ACE, CardNumber.FOUR, CardNumber.JACK);
        CardScore score = new CardScore(numbers);

        //when
        int bigScore = score.getBigScore();

        //then
        assertThat(bigScore).isEqualTo(11 + 4 + 10);
    }
}
