package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 처음_블랙잭_카드는_오픈되지_않은_상태다() {
        // given
        Card blackjackCard = new Card(Rank.ACE, Suit.DIAMOND);

        // when & then
        Assertions.assertThat(blackjackCard.isOpened()).isFalse();
    }

    @Test
    void 블랙잭_카드를_오픈한다() {
        // given
        Card blackjackCard = new Card(Rank.ACE, Suit.DIAMOND);

        // when
        blackjackCard.open();

        // then
        Assertions.assertThat(blackjackCard.isOpened()).isTrue();
    }
}