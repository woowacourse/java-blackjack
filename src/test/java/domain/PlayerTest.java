package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerTest {

    @Test
    void 플레이어가_카드를_뽑는다() {
        Player player = new Player("드라고", new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.JACK))));
        Card drawCard = new Card(Symbol.HEART, Number.FOUR);
        List<Card> providedCards = List.of(drawCard);

        Player newPlayer = player.drawCard(providedCards);
        Player expectedPlayer = new Player("드라고", new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.JACK), drawCard)));
        assertThat(newPlayer).isEqualTo(expectedPlayer);
    }
}
