package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {
    private Dealer dealerWith(TrumpCard... cards) {
        Dealer dealer = Dealer.of();
        for (TrumpCard card : cards) {
            dealer.hit(card);
        }
        return dealer;
    }

    private TrumpCard card(Suit suit, Rank rank) {
        return TrumpCard.of(suit, rank);
    }

    @Test
    void 딜러를_생성한다() {
        Dealer dealer = Dealer.of();
        assertThat(dealer).isNotNull();
    }

    @Test
    void 딜러가_카드_1장을_받는다() {
        Dealer dealer = Dealer.of();
        dealer.hit(TrumpCard.of(Suit.SPADE, Rank.ACE));
        assertThat(dealer.countCards()).isEqualTo(1);
    }

    @Test
    void 딜러의_현재_점수를_계산한다() {
        Dealer dealer = dealerWith(
                card(Suit.SPADE, Rank.KING),
                card(Suit.HEART, Rank.FIVE)
        );
        assertThat(dealer.score()).isEqualTo(15);
    }

    @Test
    void 딜러는_16이하면_카드를_더_받아야한다() {
        Dealer dealer = dealerWith(
                card(Suit.SPADE, Rank.KING),
                card(Suit.HEART, Rank.FIVE)
        );
        assertThat(dealer.shouldHit()).isTrue();
    }

    @Test
    void 딜러는_17이상이면_카드를_받지_않는다() {
        Dealer dealer = dealerWith(
                card(Suit.SPADE, Rank.KING),
                card(Suit.HEART, Rank.SEVEN)
        );
        assertThat(dealer.shouldHit()).isFalse();
    }
}
