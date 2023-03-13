package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.K;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player("player1");
    }

    @Test
    @DisplayName("Hand를 받는다.")
    void receiveHandTest() {
        Hand hand = new Hand(List.of(new Card(ACE, HEART), new Card(TWO, SPADE)));

        player.receiveHand(hand);

        assertThat(player.getHand().getHand().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void isBlackjackTest() {
        Hand hand = new Hand(List.of(new Card(ACE, HEART), new Card(K, SPADE)));

        player.receiveHand(hand);

        assertThat(player.isBlackjack()).isTrue();
    }
}
