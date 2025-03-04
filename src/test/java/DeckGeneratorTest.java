import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckGeneratorTest {
    @Test
    @DisplayName("덱을 만들 때 카드의 개수는 52개이다")
    void test1() {
        // given
        DeckGenerator deckGenerator = new DeckGenerator();

        // when
        List<Card> cards = deckGenerator.generate();

        // then
        assertThat(cards).hasSize(52);
    }
}
