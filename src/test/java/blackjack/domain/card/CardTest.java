package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void ACE_랭크의_카드는_isAce가_참이다() {
        Card aceCard = new Card(Suit.CLOVER, Rank.ACE);
        assertThat(aceCard.isAce()).isTrue();
    }

    @Test
    void ACE가_아닌_랭크의_카드는_isAce가_거짓이다() {
        Card kingCard = new Card(Suit.CLOVER, Rank.KING);
        assertThat(kingCard.isAce()).isFalse();
    }

    @Test
    void ACE_바로_다음_순서인_TWO_카드는_isAce가_거짓이다() {
        Card twoCard = new Card(Suit.CLOVER, Rank.TWO);
        assertThat(twoCard.isAce()).isFalse();
    }
}
