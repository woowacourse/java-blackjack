package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.calculatestrategy.PlayerStrategy;
import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    private Card card1;
    private Card card2;
    private Card card10;

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(new PlayerStrategy());
        card1 = new Card(Rank.ACE, Shape.CLOVER);
        card2 = new Card(Rank.TWO, Shape.CLOVER);
        card10 = new Card(Rank.JACK, Shape.CLOVER);
    }

    @DisplayName("카드를 손에 추가한다.")
    @Test
    void 카드를_손에_추가한다() {

        // given

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

        // when
        hand.add(card1);
        hand.add(card2);

        // then
        assertThat(hand.getSumOfRank()).isEqualTo(13);
    }

    @DisplayName("카드의 합이 21이면 카드를 뽑지 못한다.")
    @Test
    void 카드의_합이_21이면_카드를_뽑지_못한다() {

        // given

        // when
        hand.add(card1);
        hand.add(card10);

        // then
        assertThat(hand.isImpossibleDrawCard()).isTrue();
    }

    @DisplayName("카드의 합이 21 이하면 카드를 뽑을 수 있다.")
    @Test
    void 카드의_합이_21_이하면_카드를_뽑을_수_있다() {

        // given

        // when
        hand.add(card1);

        // then
        assertThat(hand.isImpossibleDrawCard()).isFalse();
    }

    @DisplayName("카드의 합이 21 이하면 버스트가 아니다.")
    @Test
    void 카드의_합이_21_이하면_버스트가_아니다() {

        // given

        // when
        hand.add(card1);
        hand.add(card10);

        // then
        assertThat(hand.isBust()).isFalse();
    }

    @DisplayName("카드의 합이 21 초과면 버스트다.")
    @Test
    void 카드의_합이_21_초과면_버스트다() {

        // given

        // when
        hand.add(card1);
        hand.add(card10);
        hand.add(card10);
        hand.add(card10);

        assertThat(hand.isBust()).isTrue();
    }


    @DisplayName("카드의 합이 21이며 두장이면 블랙잭 상태다.")
    @Test
    void 카드의_합이_21이며_두장이면_블랙잭_상태다() {

        // given

        // when
        hand.add(card1);
        hand.add(card10);

        // then
        assertThat(hand.isBlackJack()).isTrue();
    }

    @DisplayName("카드의 합이 21이며 두장이 아니면 블랙잭 상태가 아니다.")
    @Test
    void 카드의_합이_21이며_두장이_아니면_블랙잭_상태가_아니다() {

        // given

        // when
        hand.add(card1);
        hand.add(card10);
        hand.add(card10);

        // then
        assertThat(hand.isBlackJack()).isFalse();
    }
}
