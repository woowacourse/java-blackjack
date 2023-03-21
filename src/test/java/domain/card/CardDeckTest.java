package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드 덱에서 카드를 뽑는다")
    void pickCard() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        Suit[] suits = Suit.values();
        Suit lastSuit = suits[suits.length - 1];

        Denomination[] denominations = Denomination.values();
        Denomination lastDenomination = denominations[denominations.length - 1];

        //then
        Assertions.assertThat(cardDeck.pick())
            .isEqualTo(Card.of(lastSuit, lastDenomination));
    }
}
