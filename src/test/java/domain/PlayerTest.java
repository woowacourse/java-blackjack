package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerTest {

    @Test
    void 플레이어가_카드를_뽑는다() {
        Player player = new Player("drago", new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.JACK))));
        Card drawCard = new Card(Symbol.HEART, Number.FOUR);
        List<Card> providedCards = List.of(drawCard);

        Player newPlayer = player.drawCard(providedCards);
        Player expectedPlayer = new Player("drago", new Cards(
            List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.CLOVER, Number.JACK), drawCard)));
        assertThat(newPlayer).isEqualTo(expectedPlayer);
    }

    @Test
    void 플레이어가_가진_카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        Player drago = new Player("drago", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.DIAMOND, Number.JACK),
                        new Card(Symbol.HEART, Number.KING))));

        Player duei = new Player("duei", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.EIGHT), new Card(Symbol.DIAMOND, Number.JACK))));

        assertAll(
                () -> assertThat(drago.checkExceedBurst()).isTrue(),
                () -> assertThat(duei.checkExceedBurst()).isFalse()
        );
    }

    @Test
    void 플레이어의_이름과_카드리스트의_총합을_반환한다() {
        Player player = new Player("drago", new Cards(
                List.of(new Card(Symbol.DIAMOND, Number.EIGHT),
                        new Card(Symbol.DIAMOND, Number.JACK),
                        new Card(Symbol.HEART, Number.FOUR))));

        assertThat(player.getTotalNumberSum()).isEqualTo(22);
    }
}
