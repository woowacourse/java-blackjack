package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.NINE, Symbol.HEART),
            new Card(Rank.TWO, Symbol.SPADE)
        ));
        player.tryReceive(new Card(Rank.KING, Symbol.HEART));
        assertThat(player.getCards()).hasSize(4);
    }

    @Test
    @DisplayName("22점에서 카드받기 시도해도 카드가 안 늘어난다.")
    void tryReceive_Busted() {
        Player player = Player.withName("name");
        player.tryReceive(List.of(
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.TEN, Symbol.HEART),
            new Card(Rank.TWO, Symbol.SPADE)
        ));
        player.tryReceive(new Card(Rank.KING, Symbol.HEART));
        assertThat(player.getCards()).hasSize(3);
    }
}
