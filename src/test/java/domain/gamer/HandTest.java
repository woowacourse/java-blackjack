package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    private Card card1 = new Card(Rank.ACE, Shape.CLOVER);
    private Card card2 = new Card(Rank.TWO, Shape.CLOVER);
    private Card card3 = new Card(Rank.THREE, Shape.CLOVER);
    private Card card4 = new Card(Rank.FOUR, Shape.CLOVER);
    private Card card5 = new Card(Rank.FIVE, Shape.CLOVER);
    private Card card6 = new Card(Rank.SIX, Shape.CLOVER);
    private Card card7 = new Card(Rank.SEVEN, Shape.CLOVER);
    private Card card8 = new Card(Rank.EIGHT, Shape.CLOVER);
    private Card card9 = new Card(Rank.NINE, Shape.CLOVER);
    private Card card10 = new Card(Rank.JACK, Shape.CLOVER);
    private Hand hand;


    @DisplayName("카드를 손에 추가한다.")
    @Test
    void 카드를_손에_추가한다() {

        // given
        final Hand hand = new Hand();

        // when & then
        assertThatCode(() -> {
            hand.add(card1);
            hand.add(card2);
        }).doesNotThrowAnyException();
    }

    @DisplayName("손에 있는 카드의 합을 가져온다.")
    @Test
    void 손에_있는_카드의_합을_가져온다() {

        // given
        final Hand hand = new Hand();

        // when
        hand.add(card4);
        hand.add(card5);

        // then
        assertThat(hand.calculateSumOfRank()).isEqualTo(9);
    }

    @DisplayName("버스트이면 true, 아니면 false를 반환한다.")
    @Test
    void 버스트이면_true_아니면_false를_반환한다() {

        // given
        final Hand hand1 = new Hand();

        hand1.add(card2);

        final Hand hand2 = new Hand();
        hand2.add(card3);
        hand2.add(card8);
        hand2.add(card10);

        final Hand hand3 = new Hand();
        hand3.add(card10);
        hand3.add(card9);
        hand3.add(card8);

        // when & then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(hand1.isBust()).isFalse();
            softly.assertThat(hand2.isBust()).isFalse();
            softly.assertThat(hand3.isBust()).isTrue();
        });
    }

    @DisplayName("손에 에이스가 있으면 true를 반환한다.")
    @Test
    void 손에_에이스가_있으면_true를_반환한다() {

        // given
        hand = new Hand();
        hand.add(card1);
        hand.add(card2);

        // when & then
        assertThat(hand.hasAce()).isTrue();
    }

    @DisplayName("손에 에이스가 없으면 false를 반환한다.")
    @Test
    void 손에_에이스가_없으면_false를_반환한다() {

        // given
        hand = new Hand();
        hand.add(card2);

        // when & then
        assertThat(hand.hasAce()).isFalse();
    }
}
