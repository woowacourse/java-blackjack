package BlackJack.domain.User;

import BlackJack.domain.Card.Card;
import BlackJack.domain.Card.Number;
import BlackJack.domain.Card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("플레이어가 소지한 카드가 21 미만이면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        Player player = new Player("test");
        player.addCard(new Card(Shape.HEART, Number.JACK));
        assertThat(player.checkPossibleAdd(player.getScore())).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 21 이상이면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        Player player = new Player("test");
        player.addCard(new Card(Shape.HEART, Number.JACK));
        player.addCard(new Card(Shape.HEART, Number.TEN));
        player.addCard(new Card(Shape.HEART, Number.TEN));
        assertThat(player.checkPossibleAdd(player.getScore())).isEqualTo(false);
    }
}