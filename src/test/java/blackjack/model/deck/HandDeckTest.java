package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandDeckTest {

    HandDeck handDeck = new HandDeck();

    @DisplayName("핸드덱에 카드를 추가한다.")
    @Test
    void add() {
        //given
        Card card = Card.of(CardPattern.CLOVER, CardNumber.EIGHT);

        //when
        handDeck.add(card);
        List<Card> cards = handDeck.cards();

        //then
        assertThat(cards).containsExactly(card);
    }

    @DisplayName("핸드덱에 카드를 추가할때 스코어를 계산한다.")
    @Test
    void calculateDeckScore() {
        //given
        Card card = Card.of(CardPattern.CLOVER, CardNumber.EIGHT);

        //when
        handDeck.add(card);
        int score = handDeck.score();

        //then
        assertThat(score).isEqualTo(CardNumber.EIGHT.getNumber());
    }
}
