import domain.Dealer;
import domain.Cards;
import domain.Player;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 받으면 카드 개수가 1 증가한다")
    void drawCardByPlayer() {
        Player player = new Player("pobi");
        Cards cards = new Cards(new Random(1));

        player.drawCard(cards);

        assertEquals(1, player.getCardList().size());
    }

    @Test
    @DisplayName("딜러가 카드를 받으면 카드 개수가 1 증가한다")
    void drawCardByDealer() {
        Dealer dealer = new Dealer();
        Cards cards = new Cards(new Random(1));

        dealer.drawCard(cards);

        assertEquals(1, dealer.getCardList().size());
    }
}
