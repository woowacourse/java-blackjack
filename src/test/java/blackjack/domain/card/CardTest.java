package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class CardTest {
    @Test
    void ACE_카드와_일치한다면_TRUE_를_반환한다() {
        // given
        Card card = new Card(Rank.ACE, Suit.DIAMOND);
        // when & then
        assertThat(card.isAce()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.EXCLUDE, names = "ACE")
    void ACE_카드와_일치하지_않는다면_FALSE_를_반환한다(Rank rank) {
        // given
        Card card = new Card(rank, Suit.DIAMOND);
        // when & then
        assertThat(card.isAce()).isFalse();
    }

}