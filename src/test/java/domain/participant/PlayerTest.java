package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.CardsFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어는 핸드의 총합값을 반환할 수 있다")
    void test1() {
        Player player = new Player("모루");

        CardsFactory cardsFactory = new CardsFactory();
        player.receiveCard(cardsFactory.createScore18Cards());

        assertThat(player.getHandTotal()).isEqualTo(18);
    }
}
