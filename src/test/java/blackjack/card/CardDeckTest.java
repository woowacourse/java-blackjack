package blackjack.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @Test
    @DisplayName("초기화된 카드덱은 {카드 모양 개수 * 카드 등급 개수}장이며, 각각은 모두 유일하다")
    void initializedCardDeckShouldContain52UniqueCards() {
        // given
        CardDeck cardDeck = CardDeck.initialize();
        Cards cards = cardDeck.getCards();

        // when
        Set<Card> uniqueCards = new HashSet<>(cards.getCards());

        // then
        assertThat(uniqueCards).hasSize(CardSuit.values().length * CardRank.values().length);
    }
}
