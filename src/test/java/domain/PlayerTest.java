package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Emblem;
import domain.card.Grade;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 카드의_합이_21이초과되면_버스트() {
        // given
        Hand hand = new Hand();
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));

        Player player = new Player(new Name("test"), hand);
        boolean expect = true;

        // when
        boolean result = player.isBust();

        // then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    void 카드의_합이_21이하이면_버스트가_아니다() {
        // given
        Hand hand = new Hand();
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        hand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));

        Player player = new Player(new Name("test"), hand);
        boolean expect = false;

        // when
        boolean result = player.isBust();

        // then
        assertThat(result).isEqualTo(expect);
    }

    @Test
    void 플레이어의_게임_진행_상태를_변경한다() {
        // given
        Player player = new Player(new Name("test"), new Hand());
        GameState expectedState = GameState.STAND;
        // when
        player.changeState();

        // then
        assertThat(player.getGameState()).isEqualTo(expectedState);
    }
}
