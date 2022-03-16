package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("Dealer가 정상적으로 생성되는지 확인한다.")
    @Test
    void construct_Dealer() {
        Participant liver = new Dealer();

        assertThat(liver).isInstanceOf(Dealer.class);
    }

    @DisplayName("Card를 받으면 새로운 Dealer 인스턴스를 반환한다.")
    @Test
    void drawSeveral_new_Participant() {
        Participant dealer = new Dealer();
        Participant otherDealer = dealer.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(dealer).isNotEqualTo(otherDealer);
    }

    @DisplayName("Card를 hit 하면 새로운 Dealer 인스턴스를 반환한다.")
    @Test
    void draw_new_Participant() {
        Participant dealer = new Dealer();
        Participant otherDealer = dealer.hit(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(dealer).isNotEqualTo(otherDealer);
    }

    @DisplayName("카드 숫자의 합이 16을 넘으면 true를 반환한다.")
    @Test
    void finish_true() {
        Dealer dealer = new Dealer();
        dealer.receive(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));
        dealer.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(dealer.isFinish()).isTrue();
    }

    @DisplayName("카드 숫자의 합이 16이하 이면 false를 반환한다.")
    @Test
    void finish_false() {
        Dealer dealer = new Dealer();
        dealer.receive(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));
        dealer.receive(new Card(TrumpNumber.FIVE, TrumpSymbol.HEART));

        assertThat(dealer.isFinish()).isFalse();
    }

    @DisplayName("카드 숫자의 합이 21을 초과하면 true를 반환한다.")
    @Test
    void bust_false() {
        Participant dealer = new Dealer();
        dealer.receive(new Card(TrumpNumber.SEVEN, TrumpSymbol.CLOVER));
        dealer.receive(new Card(TrumpNumber.FIVE, TrumpSymbol.HEART));
        dealer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.HEART));

        assertThat(dealer.isBust()).isTrue();
    }

    @DisplayName("카드 숫자의 합이 21이하 이면 false를 반환한다.")
    @Test
    void bust_true() {
        Participant dealer = new Dealer();
        dealer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        dealer.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));
        dealer.receive(new Card(TrumpNumber.THREE, TrumpSymbol.HEART));

        assertThat(dealer.isBust()).isFalse();
    }

    @DisplayName("카드 숫자의 합이 21이고 카드가 두장이면 true를 반환한다.")
    @Test
    void blackjack_true() {
        Participant player = new Dealer();
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.ACE, TrumpSymbol.HEART));

        assertThat(player.isBlackjack()).isTrue();
    }

    @DisplayName("카드 숫자의 합이 21이고 카드가 두장이 아니면 false를 반환한다.")
    @Test
    void blackjack_false() {
        Participant player = new Dealer();
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));
        player.receive(new Card(TrumpNumber.THREE, TrumpSymbol.HEART));

        assertThat(player.isBlackjack()).isFalse();
    }

    @DisplayName("카드 숫자의 합이 21이 아니고 카드가 두장이면 false를 반환한다.")
    @Test
    void blackjack_false_2() {
        Participant player = new Dealer();
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(player.isBust()).isFalse();
    }

    @DisplayName("다른 참가자보다 카드숫자 합이 높으면 true를 반환한다.")
    @Test
    void win_true(){
        Participant player = new Dealer();
        player.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        player.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        Participant otherPlayer = new Player("포키");
        otherPlayer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        otherPlayer.receive(new Card(TrumpNumber.SEVEN, TrumpSymbol.HEART));

        assertThat(player.isWinBy(otherPlayer)).isTrue();
    }

    @DisplayName("다른 참가자보다 카드숫자 합이 낮으면 false를 반환한다.")
    @Test
    void win_false(){
        Participant dealer = new Dealer();
        dealer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        dealer.receive(new Card(TrumpNumber.SEVEN, TrumpSymbol.HEART));

        Participant otherPlayer = new Player("포키");
        otherPlayer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));
        otherPlayer.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(dealer.isWinBy(otherPlayer)).isFalse();
    }
}
