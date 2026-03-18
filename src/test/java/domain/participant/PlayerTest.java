package domain.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Cards;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 받으면 카드 개수가 1 증가한다")
    void drawCardByPlayer() {
        Player player = new Player("pobi", 1000);
        Cards cards = new Cards(new Random(1));

        player.drawCard(cards);

        assertEquals(1, player.getCardList().size());
    }
}
