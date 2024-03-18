package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Denomination.*;
import static blackjack.fixture.CardFixture.카드;
import static blackjack.fixture.DealerFixture.딜러;
import static blackjack.fixture.ScoreFixture.점수;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = 딜러();
    }

    @Test
    void 카드를_한_장_뽑을_수_있다() {
        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.DIAMOND, KING));
    }

    @Test
    void 기존_카드_덱의_카드를_모두_사용하였을_경우_새로운_카드_덱에서_카드를_뽑는다() {
        for (int i = 0; i < 52; i++) {
            dealer.pickCard();
        }

        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.HEART, ACE));
    }

    @Test
    void 딜러는_카드를_셔플할_수_있다() {
        dealer.shuffleCards();
        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.HEART, ACE));
    }

    @Test
    void 딜러_카드의_총_점수를_계산할_수_있다() {
        dealer.receiveCard(카드(KING));
        dealer.receiveCard(카드(SIX));

        final Score result = dealer.calculateScore();

        assertThat(result).isEqualTo(점수(16));
    }

    @Test
    void 딜러가_카드의_총_점수가_16_이하라면_카드를_더_받을_수_있다() {
        dealer.receiveCard(카드(KING));
        dealer.receiveCard(카드(SIX));

        final boolean result = dealer.canReceiveCard();

        assertThat(result).isTrue();
    }

    @Test
    void 딜러가_카드의_총_점수가_17_이상이라면_카드를_더_받을_수_없다() {
        dealer.receiveCard(카드(KING));
        dealer.receiveCard(카드(SEVEN));

        final boolean result = dealer.canReceiveCard();

        assertThat(result).isFalse();
    }
}
