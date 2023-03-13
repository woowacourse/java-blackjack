package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeckTest {
    @Test
    @DisplayName("Deck이 생성되면 모든 종류의 카드가 저장되어 있다.")
    void initializingCardRepository() {
        Deck deck = Deck.create(null);

        assertThat(deck)
                .extracting("cards")
                .asList()
                .hasSize(52);
    }

    @Test
    @DisplayName("Deck에서 인덱스가 주어지면 그 인덱스에 해당하는 카드를 가져온다.")
    void findCardByIndex() {
        Deck deck = Deck.create(cardSize -> 3);
        Card card = deck.findAnyOneCard();

        assertAll(
                () -> assertThat(card).isEqualTo(new Card(Suit.HEART, Denomination.FOUR)),
                () -> assertThat(deck)
                        .extracting("cards")
                        .asList()
                        .hasSize(51)
        );
    }
}