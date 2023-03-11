package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    private static Player player;

    @BeforeEach
    void setUp() {
        player = new Player("IO");
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.HEART, CardNumber.JACK));
        player.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 성공")
    void isAbleToReceiveTest() {
        assertThat(player.getScore()).isEqualTo(21);
        assertThat(player.isAbleToReceive()).isTrue();
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 실패")
    void isAbleToReceiveFailTest() {
        player.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));

        assertThat(player.getScore()).isEqualTo(22);
        assertThat(player.isAbleToReceive()).isFalse();
    }
}
