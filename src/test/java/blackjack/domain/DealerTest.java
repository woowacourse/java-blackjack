package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.of();
    }

    @Test
    void 딜러를_생성한다() {
        assertThat(dealer).isNotNull();
    }

    @Test
    void 딜러가_카드_1장을_받는다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        dealer.receiveCard(spadeAce);
        assertThat(dealer.getCards()).hasSize(1);
    }

    @Test
    void 딜러가_여러_카드를_받는다() {
        List<TrumpCard> cards = new ArrayList<>();
        cards.add(TrumpCard.of(Suit.SPADE, Rank.ACE));
        cards.add(TrumpCard.of(Suit.HEART, Rank.KING));

        dealer.receiveCards(cards);
        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    void 딜러의_현재_점수를_계산한다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        TrumpCard heartFive = TrumpCard.of(Suit.HEART, Rank.FIVE);

        dealer.receiveCard(spadeKing);
        dealer.receiveCard(heartFive);

        assertThat(dealer.score()).isEqualTo(15);
    }

    @Test
    void 딜러는_16이하면_카드를_더_받아야한다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        TrumpCard heartFive = TrumpCard.of(Suit.HEART, Rank.FIVE);

        dealer.receiveCard(spadeKing);
        dealer.receiveCard(heartFive);

        assertThat(dealer.shouldHit()).isTrue();
    }

    @Test
    void 딜러는_17이상이면_카드를_받지_않는다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        TrumpCard heartSeven = TrumpCard.of(Suit.HEART, Rank.SEVEN);

        dealer.receiveCard(spadeKing);
        dealer.receiveCard(heartSeven);

        assertThat(dealer.shouldHit()).isFalse();
    }

    @Test
    void 딜러의_첫_번째_카드를_조회한다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        TrumpCard heartFive = TrumpCard.of(Suit.HEART, Rank.FIVE);

        dealer.receiveCard(spadeKing);
        dealer.receiveCard(heartFive);

        assertThat(dealer.getOpenCard()).isEqualTo(spadeKing);
    }

    @Test
    void 처음_두장의_합이_21이면_블랙잭이다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        dealer.receiveCard(spadeAce);
        dealer.receiveCard(heartKing);

        assertThat(dealer.isBlackjack()).isTrue();
    }

    @Test
    void 세장_이상으로_합이_21이면_블랙잭이_아니다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);
        TrumpCard cloverKing = TrumpCard.of(Suit.CLOVER, Rank.KING);

        dealer.receiveCard(spadeAce);
        dealer.receiveCard(heartKing);
        dealer.receiveCard(cloverKing);

        assertThat(dealer.isBlackjack()).isFalse();
    }

    @Test
    void 두장이지만_합이_21이_아니면_블랙잭이_아니다() {
        TrumpCard spadeJack = TrumpCard.of(Suit.SPADE, Rank.JACK);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        dealer.receiveCard(spadeJack);
        dealer.receiveCard(heartKing);

        assertThat(dealer.isBlackjack()).isFalse();
    }
}