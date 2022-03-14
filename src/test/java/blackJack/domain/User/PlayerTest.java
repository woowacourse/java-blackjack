package blackJack.domain.User;

import blackJack.domain.Card.Card;
import blackJack.domain.Card.Number;
import blackJack.domain.Card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player = new Player("test");

    @Test
    @DisplayName("플레이어가 소지한 카드가 21 미만이면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        player.cards.add(new Card(Shape.HEART, Number.JACK));
        assertThat(player.isPossibleToAdd()).isEqualTo(true);
    }

    @Test
    @DisplayName("플레이어가 소지한 카드가 21 이상이면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        player.cards.add(new Card(Shape.HEART, Number.JACK));
        player.cards.add(new Card(Shape.HEART, Number.TEN));
        player.cards.add(new Card(Shape.HEART, Number.TEN));
        assertThat(player.isPossibleToAdd()).isEqualTo(false);
    }

}