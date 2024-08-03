import model.Card;
import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    void testPlayerCardsValue() {
        Player player = new Player("Test Player");
        player.receiveCard(new Card("하트", "2"));
        player.receiveCard(new Card("스페이드", "K"));
        assertEquals(12, player.getCardsValue());
    }

    @Test
    void testPlayerCardsValueWithAce() {
        Player player = new Player("Test Player");
        player.receiveCard(new Card("하트", "2"));
        player.receiveCard(new Card("스페이드", "A"));
        assertEquals(13, player.getCardsValue());
    }
}
