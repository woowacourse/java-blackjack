package blackjack.domain.card;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void ACE_랭크의_카드는_isAce가_참이다() {
        Card aceCard = new Card(Suit.CLUB, Rank.ACE);
        assertThat(aceCard.isAce()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"ACE"})
    void ACE가_아닌_랭크의_카드는_isAce가_거짓이다(Rank rank) {
        Card twoCard = new Card(Suit.CLUB, rank);
        assertThat(twoCard.isAce()).isFalse();
    }
}
