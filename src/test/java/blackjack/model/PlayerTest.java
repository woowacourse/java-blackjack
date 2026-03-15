package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 점수가 21점을 넘지 않으면 카드를 뽑을 수 있다.")
    void canReceiveCard() {
        // given
        Player player = new Player("luke", 1000);
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardSix = new Card(Figure.SPADE, Number.SIX);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardSix);
        player.getScore();
        // then
        assertThat(player.canReceive()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점을 넘으면 카드를 뽑을 수 없다.")
    void cantReceiveCard() {
        // given
        Player player = new Player("luke", 1000);
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardJack = new Card(Figure.SPADE, Number.JACK);
        Card cardSix = new Card(Figure.SPADE, Number.SIX);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardJack);
        player.receiveCard(cardSix);
        player.getScore();
        // then
        assertThat(player.canReceive()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 점수가 21점이면 카드를 뽑을 수 있다.")
    void cantReceiveCardScore21Test() {
        // given
        Player player = new Player("luke", 1000);
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardJack = new Card(Figure.SPADE, Number.JACK);
        Card cardAce = new Card(Figure.SPADE, Number.ACE);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardJack);

        player.receiveCard(cardAce);
        player.getScore();
        // then
        assertThat(player.canReceive()).isTrue();
    }

    @Test
    @DisplayName("처음 받은 2장의 합이 21점이면 블랙잭이다")
    void isBlackjackTrue() {
        // given
        Player player = new Player("luke", 1000);
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardAce = new Card(Figure.SPADE, Number.ACE);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardAce);
        // then
        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("3장 이상의 합이 21점이면 블랙잭이 아니다")
    void isBlackjackFalse() {
        // given
        Player player = new Player("luke", 1000);
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardJack = new Card(Figure.SPADE, Number.JACK);
        Card cardAce = new Card(Figure.SPADE, Number.ACE);
        // when
        player.receiveCard(cardTen);
        player.receiveCard(cardJack);
        player.receiveCard(cardAce);
        // then
        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("21이 넘으면 버스트 처리한다.")
    void isBustTest() {
        // given
        Participant participant = new Player("luke", 1000);

        participant.receiveCard(new Card(Figure.DIAMOND, Number.TWO));
        participant.receiveCard(new Card(Figure.DIAMOND, Number.TEN));
        participant.receiveCard(new Card(Figure.SPADE, Number.TEN));
        // when & then
        assertThat(participant.isBust()).isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 버스트 처리하지 않는다.")
    void isBustTest2() {
        // given
        Participant participant = new Player("luke", 1000);

        participant.receiveCard(new Card(Figure.DIAMOND, Number.ACE));
        participant.receiveCard(new Card(Figure.SPADE, Number.TEN));
        // when & then
        assertThat(participant.isBust()).isFalse();
    }
}
