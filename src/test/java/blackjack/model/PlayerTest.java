package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 점수가 21점을 넘지 않으면 카드를 뽑을 수 있다.")
    void canReceiveCard() {
        // given
        Player player = new Player("luke");
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardSix = new Card(Figure.SPADE, Number.SIX);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardSix);
        player.updateScore();
        // then
        assertThat(player.canReceive()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점을 넘으면 카드를 뽑을 수 없다.")
    void cantReceiveCard() {
        // given
        Player player = new Player("luke");
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardJack = new Card(Figure.SPADE, Number.JACK);
        Card cardSix = new Card(Figure.SPADE, Number.SIX);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardJack);
        player.receiveCard(cardSix);
        player.updateScore();
        // then
        assertThat(player.canReceive()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점이면 카드를 뽑을 수 없다.")
    void cantReceiveCardScore21Test() {
        // given
        Player player = new Player("luke");
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardJack = new Card(Figure.SPADE, Number.JACK);
        Card cardAce = new Card(Figure.SPADE, Number.ACE);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardJack);

        player.receiveCard(cardAce);
        player.updateScore();
        // then
        assertThat(player.canReceive()).isFalse();
    }
}