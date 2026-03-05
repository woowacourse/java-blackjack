package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("트럼프 카드 덱을 초기화한다.")
    public void 트럼프_덱_초기화_성공() throws Exception {

        // when
        CardDeck cardDeck = CardDeck.initCardDeck();

        // then
        List<Card> cards = cardDeck.getCardDeck();
        assertThat(cards).hasSize(CardSuit.values().length * CardRank.values().length);

        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                assertThat(cards).contains(new Card(cardSuit, cardRank));
            }
        }
    }

}