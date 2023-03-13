package domain;

import domain.deck.Deck;
import domain.player.Amount;
import domain.player.Name;
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
        // given
        final Name name = new Name("test");
        final Amount amount = new Amount(100_000);
        final Player player = new Player(name, amount);

        // when
        player.drawCard(deck.popCard());

        // then
        Assertions.assertEquals(1, player.cards().size());
    }
}
