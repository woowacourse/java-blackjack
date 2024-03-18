package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("21점에서 카드받기 시도하면 카드가 늘어난다.")
    void tryReceive_NotBusted() {
        Player player = Player.withName("name");
        player.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.NINE, Symbol.HEART),
            Card.of(Rank.TWO, Symbol.SPADE)
        ));
        player.tryReceive(Card.of(Rank.KING, Symbol.HEART));
        assertThat(player.getCards()).hasSize(4);
    }

    @Test
    @DisplayName("22점에서 카드받기 시도해도 카드가 안 늘어난다.")
    void tryReceive_Busted() {
        Player player = Player.withName("name");
        player.tryReceive(List.of(
            Card.of(Rank.KING, Symbol.CLUB),
            Card.of(Rank.TEN, Symbol.HEART),
            Card.of(Rank.TWO, Symbol.SPADE)
        ));
        player.tryReceive(Card.of(Rank.KING, Symbol.HEART));
        assertThat(player.getCards()).hasSize(3);
    }
}
