package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardTest {
    @Test
    @DisplayName("캐싱으로 카드 추가 생성 시 동일한 카드가 생성되는지 확인")
    void create() {
        final var card = Card.of(Suit.CLOVER, Letter.ACE);

        assertThat(card).isNotNull()
                .isSameAs(Card.of(Suit.CLOVER, Letter.ACE));
    }

}
