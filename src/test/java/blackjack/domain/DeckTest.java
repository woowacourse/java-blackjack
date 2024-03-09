package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeckTest {

    @DisplayName("카드 덱은 카드를 한장씩 반환한다")
    @Test
    void should_giveOneCard() {
        Deck shuffledDeck = Deck.createSuffledDeck();
        assertThat(shuffledDeck.draw()).isInstanceOf(Card.class);
    }

    @DisplayName("덱의 카드가 모두 반환되면, 더이상 반환을 요청할 수 없다")
    @Test
    void should_ThrowIllegalArgumentException_When_NoMoreCard() {
        Deck shuffledDeck = Deck.createSuffledDeck();

        for (int i = 0; i < 52; i++) {
            shuffledDeck.draw();
        }

        assertThatThrownBy(shuffledDeck::draw)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
