package domain;

import domain.deck.Deck;
import domain.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class PlayerTest {
    Deck deck = new Deck();

    @DisplayName("플레이어가 처음 카드를 뽑으면 패의 크기는 1이다.")
    @Test
    void drawTest() {
        final String testName = "test";
        final int amount = 100_000;
        Player player = new Player(testName, amount);

        Assertions.assertEquals(0, player.cards().size());
        player.drawCard(deck.popCard());
        Assertions.assertEquals(1, player.cards().size());
    }
}
