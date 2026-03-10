package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.Rank;
import vo.Suit;

public class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void 일반_카드_점수_합산() {
        // given
        hand.saveCard(new Card(Suit.SPADE, Rank.TWO));
        hand.saveCard(new Card(Suit.HEART, Rank.THREE));

        // when & then
        assertThat(hand.calculateTotalScore()).isEqualTo(5);
    }

    @Test
    void 에이스_포함_21이하_11점으로_계산() {
        // given
        hand.saveCard(new Card(Suit.SPADE, Rank.ACE));
        hand.saveCard(new Card(Suit.HEART, Rank.FIVE));

        // when & then
        assertThat(hand.calculateTotalScore()).isEqualTo(16);
    }

    @Test
    void 에이스_포함_21초과_시_1점으로_계산() {
        // given
        hand.saveCard(new Card(Suit.SPADE, Rank.ACE));
        hand.saveCard(new Card(Suit.HEART, Rank.KING));
        hand.saveCard(new Card(Suit.DIAMOND, Rank.FIVE));

        // when & then
        assertThat(hand.calculateTotalScore()).isEqualTo(16);
    }

    @Test
    void 에이스_없이_21초과_버스트() {
        // given
        hand.saveCard(new Card(Suit.SPADE, Rank.KING));
        hand.saveCard(new Card(Suit.HEART, Rank.QUEEN));
        hand.saveCard(new Card(Suit.DIAMOND, Rank.TWO));

        // when & then
        assertThat(hand.calculateTotalScore()).isEqualTo(22);
    }

    @Test
    void 점수_16이하_딜러_히트_필요() {
        // given
        hand.saveCard(new Card(Suit.SPADE, Rank.TEN));
        hand.saveCard(new Card(Suit.HEART, Rank.SIX));

        // when & then
        assertThat(hand.determineDealerDealMore()).isTrue();
    }

    @Test
    void 점수_17이상_딜러_히트_불필요() {
        // given
        hand.saveCard(new Card(Suit.SPADE, Rank.TEN));
        hand.saveCard(new Card(Suit.HEART, Rank.SEVEN));

        // when & then
        assertThat(hand.determineDealerDealMore()).isFalse();
    }

    @Test
    void 카드_목록_반환() {
        // given
        hand.saveCard(new Card(Suit.SPADE, Rank.ACE));
        hand.saveCard(new Card(Suit.HEART, Rank.KING));

        // when
        var cards = hand.getCards();

        // then
        assertThat(cards).hasSize(2);
        assertThat(cards.get(0).getRankName()).isEqualTo("A");
        assertThat(cards.get(1).getRankName()).isEqualTo("K");
    }
}