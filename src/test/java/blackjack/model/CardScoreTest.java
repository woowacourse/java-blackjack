package blackjack.model;

import blackjack.model.card.CardNumber;
import blackjack.model.card.CardScore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardScoreTest {

    @Test
    @DisplayName("에이스가 포함된 점수계산에서 작은 점수를 가져온다.")
    void smallScore() {
        //given
        List<CardNumber> numbers = List.of(CardNumber.ACE, CardNumber.FOUR, CardNumber.JACK);
        CardScore score = new CardScore(numbers, ResultState.STAND);

        //when
        int smallScore = score.smallScore();

        //then
        assertThat(smallScore).isEqualTo(1+4+10);
    }

    @Test
    @DisplayName("에이스가 포함된 점수계산에서 큰 점수를 가져온다.")
    void bigScore() {
        //given
        List<CardNumber> numbers = List.of(CardNumber.ACE, CardNumber.FOUR, CardNumber.JACK);
        CardScore score = new CardScore(numbers, ResultState.STAND);

        //when
        int bigScore = score.bigScore();

        //then
        assertThat(bigScore).isEqualTo(11+4+10);
    }

    @Test
    @DisplayName("카드 점수와 플레이 상태에 따라 더 큰것을 비교한다.")
    void card_score_compare_to() {
        //given
        List<CardNumber> numbers1 = List.of(CardNumber.SIX, CardNumber.FOUR, CardNumber.JACK); //6+4+10 = 20
        List<CardNumber> numbers2 = List.of(CardNumber.FOUR, CardNumber.FOUR, CardNumber.JACK); // 4+4+10 = 18
        CardScore cardScore1 = new CardScore(numbers1, ResultState.STAND);
        CardScore cardScore2 = new CardScore(numbers2, ResultState.STAND);

        // when
        int resultCompare = cardScore1.compareTo(cardScore2);

        //then
        assertThat(resultCompare).isEqualTo(2);
    }
}