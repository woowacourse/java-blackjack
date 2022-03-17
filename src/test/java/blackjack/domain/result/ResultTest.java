package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.ParticipantAcceptStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    @DisplayName("딜러와 참가자가 모두 블랙잭이라면 무승부로 판정한다.")
    void drawWhenBothBlackjack() {
        Participant zero = new Participant("zero", new ParticipantAcceptStrategy(), new Money(0));
        zero.addCard(new Card(Type.SPADE, Score.ACE));
        zero.addCard(new Card(Type.HEART, Score.KING));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.KING));

        assertThat(Result.competeResult(dealer, zero)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("딜러와 참가자의 점수합이 같다면 무승부로 판단한다.")
    void drawWhenScoreEqual() {
        Participant zero = new Participant("zero", new ParticipantAcceptStrategy(), new Money(0));
        zero.addCard(new Card(Type.SPADE, Score.TEN));
        zero.addCard(new Card(Type.HEART, Score.SIX));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.EIGHT));
        dealer.addCard(new Card(Type.DIAMOND, Score.EIGHT));

        assertThat(Result.competeResult(dealer, zero)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("21점과 blackjack이 겨루면 blackjack을 가진 쪽이 승리한다.")
    void winBlackjackOwner() {
        Participant zero = new Participant("zero", new ParticipantAcceptStrategy(), new Money(0));
        zero.addCard(new Card(Type.SPADE, Score.ACE));
        zero.addCard(new Card(Type.HEART, Score.KING));
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.EIGHT));
        dealer.addCard(new Card(Type.DIAMOND, Score.EIGHT));
        dealer.addCard(new Card(Type.CLOVER, Score.FIVE));

        assertThat(Result.competeResult(dealer, zero)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("참가자가 Burst고 딜러는 Burst가 아니라면 딜러가 승리한다.")
    void dealerWinWhenNotBurstAndParticipantBurst() {
        Participant zero = new Participant("zero", new ParticipantAcceptStrategy(), new Money(0));
        zero.addCard(new Card(Type.SPADE, Score.KING));
        zero.addCard(new Card(Type.HEART, Score.SIX));
        zero.addCard(new Card(Type.HEART, Score.KING));
        Participant corinne = new Participant("corinne", new ParticipantAcceptStrategy(), new Money(0));
        corinne.addCard(new Card(Type.CLOVER, Score.SIX));
        corinne.addCard(new Card(Type.HEART, Score.TEN));
        corinne.addCard(new Card(Type.DIAMOND, Score.SIX));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.KING));

        assertAll(
                () -> assertThat(Result.competeResult(dealer, zero)).isEqualTo(Result.LOSE),
                () -> assertThat(Result.competeResult(dealer, corinne)).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("참가자와 딜러 모두 Burst 라면 딜러가 승리한다.")
    void dealerWinWhenBurstAndParticipantBurst() {
        Participant zero = new Participant("zero", new ParticipantAcceptStrategy(), new Money(0));
        zero.addCard(new Card(Type.SPADE, Score.KING));
        zero.addCard(new Card(Type.HEART, Score.SIX));
        zero.addCard(new Card(Type.CLOVER, Score.TEN));

        Participant corinne = new Participant("corinne", new ParticipantAcceptStrategy(), new Money(0));
        corinne.addCard(new Card(Type.CLOVER, Score.SIX));
        corinne.addCard(new Card(Type.HEART, Score.TEN));
        corinne.addCard(new Card(Type.DIAMOND, Score.SIX));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.KING));
        dealer.addCard(new Card(Type.CLOVER, Score.ACE));

        assertAll(
                () -> assertThat(Result.competeResult(dealer, zero)).isEqualTo(Result.LOSE),
                () -> assertThat(Result.competeResult(dealer, corinne)).isEqualTo(Result.LOSE)
        );
    }

    @Test
    @DisplayName("참가자가 딜러보다 점수합이 높으면 참가자가 승리한다.")
    void participantWinWhenOverDealer() {
        Participant zero = new Participant("zero", new ParticipantAcceptStrategy(), new Money(0));
        zero.addCard(new Card(Type.CLOVER, Score.ACE));
        zero.addCard(new Card(Type.HEART, Score.SIX));

        Participant corinne = new Participant("corinne", new ParticipantAcceptStrategy(), new Money(0));
        corinne.addCard(new Card(Type.SPADE, Score.KING));
        corinne.addCard(new Card(Type.HEART, Score.SEVEN));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.FIVE));

        assertAll(
                () -> assertThat(Result.competeResult(dealer, zero)).isEqualTo(Result.WIN),
                () -> assertThat(Result.competeResult(dealer, corinne)).isEqualTo(Result.WIN)
        );
    }
}
