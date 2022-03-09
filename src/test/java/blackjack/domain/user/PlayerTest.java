package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어 생성 검증")
    @Test
    public void createPlayer() {
        //given
        String name = "pobi";

        //when
        Player player = new Player(name);

        //then
        assertThat(player).isNotNull();
    }

    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
    @Test
    public void testDrawCard() {
        //given
        Deck deck = new Deck();
        Player player = new Player("pobi");

        //when
        player.drawCard(deck);
        List<Card> cards = player.showCards();

        //then
        assertThat(cards.size()).isEqualTo(1);
    }
}
