package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackScoreCalculatorTest {

    @Test
    @DisplayName("덱의 점수를 구한다. ACE의 경우 최선의 경우를 리턴한다")
    void apply() {
        Deck deck = new Deck();
        deck.add(new Card(Symbol.CLOVER, CardNumber.EIGHT));
        deck.add(new Card(Symbol.CLOVER, CardNumber.TWO));

        BlackjackScoreCalculator blackjackScoreCalculator = new BlackjackScoreCalculator();
        assertThat(blackjackScoreCalculator.apply(deck)).isEqualTo(10);

        deck = new Deck();
        deck.add(new Card(Symbol.CLOVER, CardNumber.ACE));

        blackjackScoreCalculator = new BlackjackScoreCalculator();
        assertThat(blackjackScoreCalculator.apply(deck)).isEqualTo(11);

        deck = new Deck();
        deck.add(new Card(Symbol.CLOVER, CardNumber.ACE));
        deck.add(new Card(Symbol.CLOVER, CardNumber.KING));
        deck.add(new Card(Symbol.CLOVER, CardNumber.TWO));

        blackjackScoreCalculator = new BlackjackScoreCalculator();
        assertThat(blackjackScoreCalculator.apply(deck)).isEqualTo(13);
    }
}