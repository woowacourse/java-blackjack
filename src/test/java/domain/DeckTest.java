package domain;

import domain.card.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("NoShuffleStrategy를 사용하면 카드가 순서대로 나온다.")
    void NoShuffleStrategy_카드_순서() {
        // given
        Deck deck = new Deck(new NoShuffleStrategy());

        // when
        Card firstCard = deck.drawCard();

        // then
        Assertions.assertEquals(Rank.TWO, firstCard.getRank());
        Assertions.assertEquals(Suit.HEART, firstCard.getSuit());
    }
}
