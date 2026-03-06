import domain.Dealer;
import domain.DuplicationSet;
import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @BeforeEach
    void clearCards() {
        DuplicationSet.duplicationCards.clear();
    }

    @Test
    @DisplayName("플레이어가 카드를 받으면 카드 개수가 1 증가한다")
    void drawCardByPlayer() {
        Player player = new Player("pobi");

        player.drawCard();

        assertEquals(1, player.getCardList().size());
    }

    @Test
    @DisplayName("딜러가 카드를 받으면 카드 개수가 1 증가한다")
    void drawCardByDealer() {
        Dealer dealer = new Dealer();

        dealer.drawCard();

        assertEquals(1, dealer.getCardList().size());
    }
}
