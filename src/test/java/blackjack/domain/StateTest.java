package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Hit;
import blackjack.domain.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("pobi");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("4가지 기본 상태 확인")
    void name() {
        Cards cards = new Cards();
        Hit hit = new Hit(cards);
        Stay stay = new Stay(cards);
        Blackjack blackjack = new Blackjack(cards);
        Bust bust = new Bust(cards);

        assertThat(stay.isFinished());
        assertThat(blackjack.isFinished());
        assertThat(bust.isFinished());
        assertFalse(hit.isFinished());
    }

    @Test
    @DisplayName("블랙잭이 되는 경우 확인")
    void makeBlackjack() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));

        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));

        assertThat(player.state).isInstanceOf(Blackjack.class);
        assertThat(dealer.state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("3장은 블랙잭이 아님")
    void makeFakeBlackjack() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.FIVE));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.FIVE));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));

        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));

        assertThat(player.state).isInstanceOf(Hit.class);
        assertThat(dealer.state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("21이 넘어가면 Bust 상태가 됨")
    void makeBust() {
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));

        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));

        assertThat(player.state).isInstanceOf(Bust.class);
        assertThat(dealer.state).isInstanceOf(Bust.class);
    }
}
