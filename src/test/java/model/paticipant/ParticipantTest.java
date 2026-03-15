package model.paticipant;

import static org.assertj.core.api.Assertions.assertThat;

import model.card.Card;
import model.card.CardShape;
import model.card.CardValue;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    void 처음_2장의_카드_합이_21이면_블랙잭이다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.ACE));
        dealer.addCard(new Card(CardShape.HEART, CardValue.KING));

        // when
        boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }

    @Test
    void 카드가_2장이상이면_블랙잭이_아니다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.TEN));
        dealer.addCard(new Card(CardShape.HEART, CardValue.FIVE));
        dealer.addCard(new Card(CardShape.HEART, CardValue.SIX));

        // when
        boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }

    @Test
    void 카드_2장의_합이_21이_아니면_블랙잭이_아니다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.TEN));
        dealer.addCard(new Card(CardShape.HEART, CardValue.FIVE));

        // when
        boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }
}
