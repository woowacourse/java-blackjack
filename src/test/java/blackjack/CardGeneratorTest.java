package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CardGeneratorTest {

    @Test
    void cardGenerate() {
        CardGenerator cardGenerator = new CardGenerator();
        Set<Card> set = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = cardGenerator.generate();
            if (set.contains(card)) {
                fail();
            }
            set.add(card);
        }

        assertThat(set).hasSize(52);
    }

}
