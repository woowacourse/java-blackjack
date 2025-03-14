package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    private List<Card> cards;
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;
    private Card card7;
    private Card card8;
    private Card card9;
    private Card card10;

    private Hand hand;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        card1 = new Card(Rank.ACE, Shape.CLOVER);
        card2 = new Card(Rank.TWO, Shape.CLOVER);
        card3 = new Card(Rank.THREE, Shape.CLOVER);
        card4 = new Card(Rank.FOUR, Shape.CLOVER);
        card5 = new Card(Rank.FIVE, Shape.CLOVER);
        card6 = new Card(Rank.SIX, Shape.CLOVER);
        card7 = new Card(Rank.SEVEN, Shape.CLOVER);
        card8 = new Card(Rank.EIGHT, Shape.CLOVER);
        card9 = new Card(Rank.NINE, Shape.CLOVER);
        card10 = new Card(Rank.JACK, Shape.CLOVER);
    }

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
        hand.add(card1);
        hand.add(card2);

        // then
        assertThat(hand.getSumOfRank()).isEqualTo(3);
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

    @DisplayName("손에 있는 카드의 최소 합이 21이 안넘어가면 카드를 뽑을 수 있다.")
    @Test
    void 손에_있는_카드의_최소_합이_21이_안넘어가면_카드를_뽑을_수_있다() {

        // given
        hand = new Hand();
        hand.add(new Card(Rank.TEN, Shape.SPADE));
        hand.add(new Card(Rank.TEN, Shape.SPADE));

        // when & then
        assertThat(hand.isImPossibleDrawCard()).isFalse();
    }

    @DisplayName("손에 있는 카드의 최소 합이 21이 넘어가면 카드를 뽑지 않는다.")
    @Test
    void 손에_있는_카드의_최소_합이_21이_넘어가면_카드를_뽑지_않는다() {

        // given
        hand = new Hand();
        hand.add(new Card(Rank.TEN, Shape.SPADE));
        hand.add(new Card(Rank.TEN, Shape.SPADE));
        hand.add(new Card(Rank.ACE, Shape.SPADE));

        // when & then
        assertThat(hand.isImPossibleDrawCard()).isTrue();
    }
}
