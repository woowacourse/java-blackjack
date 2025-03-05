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

    @Test
    void 플레이어에게_카드를_준다() {
        //given
        final String name = "윌슨";
        final List<Card> cards = List.of(new Card(CardType.DIAMOND_2), new Card(CardType.CLOVER_4));
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final Card card = randomCardGenerator.generate();
        final CardGroup cardGroup = new CardGroup(cards);

        //when
        final Player player = new Player(name, cardGroup);
        final boolean result = player.receiveCard(card);

        //then
        assertThat(result).isTrue();

    }

    @Test
    void 플레이어의_점수를_딜러와_비교한다(){
        final String name = "윌슨";
        final List<Card> cards = List.of(new Card(CardType.DIAMOND_2), new Card(CardType.CLOVER_4));
        final CardGroup cardGroup = new CardGroup(cards);

        final Player player = new Player(name, cardGroup);

        assertThat(player.isGreaterThan(7)).isTrue();
    }
}
