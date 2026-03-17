package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.hand.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @Test
    void 처음_두장_합이_21이면_블랙잭이다() {
        Hand hand = new Hand(List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.JACK, CardShape.SPADE)
        ));
        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    void 카드가_세장이면_21이어도_블랙잭이_아니다() {
        Hand hand = new Hand(List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.FIVE, CardShape.SPADE),
                Card.of(CardNumber.FIVE, CardShape.DIAMOND)
        ));
        assertThat(hand.isBlackjack()).isFalse();
    }

    @Test
    void 두장이지만_합이_21이_아니면_블랙잭이_아니다() {
        Hand hand = new Hand(List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.FIVE, CardShape.DIAMOND)
        ));
        assertThat(hand.isBlackjack()).isFalse();
    }
}
