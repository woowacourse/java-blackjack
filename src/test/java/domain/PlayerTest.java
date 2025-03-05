package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerTest {


    @Test
    void 플레이어를_생성한다() {
        //given
        final String name = "윌슨";
        final List<Card> cards = List.of(new Card(CardType.DIAMOND_2), new Card(CardType.CLOVER_4));
        final CardGroup cardGroup = new CardGroup(cards);

        //when
        final Player player = new Player(name,cardGroup);

        //then
        assertThat(player).isInstanceOf(Player.class);

    }
}
