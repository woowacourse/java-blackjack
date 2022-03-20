package blackjack.model.trumpcard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    private Hand hand;

    @BeforeEach
    void initializeDeck() {
        this.hand = new Hand();
    }

    @DisplayName("7하트, 10다이아몬드 점수의 합은 17이다")
    @Test
    void sumCardScore_7heart_10diamond() {
        hand.add(new TrumpCard(TrumpDenomination.SEVEN, TrumpSuit.HEART));
        hand.add(new TrumpCard(TrumpDenomination.TEN, TrumpSuit.DIAMOND));

        assertThat(hand.sumScore()).isEqualTo(17);
    }

    @DisplayName("9클로버, J하트 점수의 합은 19이다")
    @Test
    void sumCardScore_9clover_Jheart() {
        hand.add(new TrumpCard(TrumpDenomination.NINE, TrumpSuit.CLOVER));
        hand.add(new TrumpCard(TrumpDenomination.JACK, TrumpSuit.HEART));

        assertThat(hand.sumScore()).isEqualTo(19);
    }

    @DisplayName("9클로버, J하트, A클로버 점수에는 Ace Advantage가 반영되지 않는다")
    @Test
    void sumCardScore_9clover_Jheart_Aclover() {
        hand.add(new TrumpCard(TrumpDenomination.NINE, TrumpSuit.CLOVER));
        hand.add(new TrumpCard(TrumpDenomination.JACK, TrumpSuit.HEART));
        hand.add(new TrumpCard(TrumpDenomination.ACE, TrumpSuit.CLOVER));

        assertThat(hand.sumScore()).isEqualTo(20);
    }

    @DisplayName("9클로버, A클로버 점수에 Ace Advantage가 반영된다")
    @Test
    void sumCardScore_9clover_Aclover() {
        hand.add(new TrumpCard(TrumpDenomination.NINE, TrumpSuit.CLOVER));
        hand.add(new TrumpCard(TrumpDenomination.ACE, TrumpSuit.CLOVER));

        assertThat(hand.sumScore()).isEqualTo(20);
    }

    @DisplayName("5하트, 6클로버, A클로버 점수에 Ace Advantage가 반영되지 않는다")
    @Test
    void sumCardScore_5heart_6clover_Aclover() {
        hand.add(new TrumpCard(TrumpDenomination.FIVE, TrumpSuit.HEART));
        hand.add(new TrumpCard(TrumpDenomination.SIX, TrumpSuit.CLOVER));
        hand.add(new TrumpCard(TrumpDenomination.ACE, TrumpSuit.CLOVER));

        assertThat(hand.sumScore()).isEqualTo(12);
    }

    @DisplayName("점수 합이 22이면 Bust이다")
    @Test
    void isBust_true() {
        hand.add(new TrumpCard(TrumpDenomination.NINE, TrumpSuit.CLOVER));
        hand.add(new TrumpCard(TrumpDenomination.THREE, TrumpSuit.HEART));
        hand.add(new TrumpCard(TrumpDenomination.JACK, TrumpSuit.SPADE));

        assertThat(hand.isBust()).isTrue();
    }

    @DisplayName("점수 합이 21이면 Bust가 아니다")
    @Test
    void isBust_false() {
        hand.add(new TrumpCard(TrumpDenomination.NINE, TrumpSuit.CLOVER));
        hand.add(new TrumpCard(TrumpDenomination.TWO, TrumpSuit.HEART));
        hand.add(new TrumpCard(TrumpDenomination.JACK, TrumpSuit.SPADE));

        assertThat(hand.isBust()).isFalse();
    }

    @DisplayName("이미 초기화한 패를 또 초기화하면 예외가 발생한다")
    @Test
    void initialize_exception_already_initialized() {
        hand.initialize(() -> new TrumpCardPack().draw());

        assertThatThrownBy(() -> hand.initialize(() -> new TrumpCardPack().draw()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이미 덱을 초기화했습니다.");
    }
}
