package domain;

import domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeckTest {
    @Test
    @DisplayName("Deck이 생성되면 모든 종류의 카드가 저장되어 있다.")
    void initializingCardRepository() {
        Deck deck = Deck.create();

        assertThat(deck)
                .extracting("cards")
                .asList()
                .hasSize(52);
    }

    @Test
    @DisplayName("Deck에서 한 장을 뽑아가면 남은 카드의 수가 줄어든다.")
    void findCardByIndex() {
        Deck deck = Deck.create();

        deck.findAnyOneCard();

        assertThat(deck)
                .extracting("cards")
                .asList()
                .hasSize(51);
    }
}