package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.constant.TrumpSuit;
import blackjack.constant.TrumpRank;
import org.junit.jupiter.api.Test;

class CardTest {


    @Test
    void 문양과_숫자로_카드를_생성한다() {
        // given
        Card card = createCard(TrumpSuit.DIAMOND, TrumpRank.ACE);

        // then
        assertThat(card.getRank()).isEqualTo(TrumpRank.ACE);
        assertThat(card.getSuit()).isEqualTo(TrumpSuit.DIAMOND);
    }

    @Test
    void 카드가_ACE라면_true를_반환한다() {
        // given
        Card card = createCard(TrumpSuit.HEART, TrumpRank.ACE);

        // when // then
        assertThat(card.isAce()).isTrue();
    }

    @Test
    void 카드가_ACE가_아니라면_false를_반환한다() {
        // given
        Card card = createCard(TrumpSuit.HEART, TrumpRank.KING);

        // when // then
        assertThat(card.isAce()).isFalse();
    }

    private Card createCard(TrumpSuit suit, TrumpRank rank) {
        return new Card(rank, suit);
    }

}
