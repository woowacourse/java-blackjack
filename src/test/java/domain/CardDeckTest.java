package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Number;
import domain.card.Suit;
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

        Number[] numbers = Number.values();
        Number lastNumber = numbers[numbers.length - 1];

        //then
        Assertions.assertThat(cardDeck.pick())
            .isEqualTo(new Card(lastSuit, lastNumber));
    }
}
