package blackjack.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {

    @Test
    @DisplayName("카드의 숫자 합을 계산한다")
    void calculateScoreTest() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Deck deck = new Deck();
        List<Card> cardsOne = List.of(deck.giveCard(), deck.giveCard());
        List<Card> cardsTwo = List.of(deck.giveCard(), deck.giveCard());

        // when
        int cardScoreOne = scoreCalculator.getCardScore(cardsOne);
        int cardScoreTwo = scoreCalculator.getCardScore(cardsTwo);

        // then
        assertThat(cardScoreOne).isEqualTo(13);
        assertThat(cardScoreTwo).isEqualTo(7);
    }

}