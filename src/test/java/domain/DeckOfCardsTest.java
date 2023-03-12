package domain;

import domain.card.Card;
import domain.card.DeckOfCards;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DeckOfCardsTest {
    @Test
    @DisplayName("CardRepository가 생성되면 모든 종류의 카드가 저장되어 있다.")
    void initializingCardRepository() {
        DeckOfCards deckOfCards = DeckOfCards.create(null);

        assertThat(deckOfCards)
                .extracting("cards")
                .asList()
                .hasSize(52);
    }

    @Test
    @DisplayName("CardRepository에서 인덱스가 주어지면 그 인덱스에 해당하는 카드를 가져온다.")
    void findCardByIndex() {
        DeckOfCards deckOfCards = DeckOfCards.create(cardSize -> 3);
        Card card = deckOfCards.findAnyOneCard();

        assertAll(
                () -> assertThat(card).isEqualTo(new Card(Suit.HEART, Denomination.FOUR)),
                () -> assertThat(deckOfCards)
                        .extracting("cards")
                        .asList()
                        .hasSize(51)
        );
    }
}