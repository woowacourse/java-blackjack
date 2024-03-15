package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("원하는 종류의 카드를 캐시에서 꺼내올 수 있다.")
    @Test
    void popCardFromCache() {
        final Card card = Card.of(Rank.FOUR, Suit.SPADE);

        assertAll(
                () -> assertThat(card.getRank()).isEqualTo(Rank.FOUR),
                () -> assertThat(card.getSuit()).isEqualTo(Suit.SPADE)
        );
    }
}
