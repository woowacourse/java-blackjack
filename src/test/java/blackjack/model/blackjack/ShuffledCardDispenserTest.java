package blackjack.model.blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

import blackjack.model.card.Card;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ShuffledCardDispenserTest {

    @Test
    void cardGenerate() {
        CardDispenser cardDispenser = new ShuffledCardDispenser();
        Set<Card> set = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = cardDispenser.issue();
            if (set.contains(card)) {
                fail();
            }
            set.add(card);
        }

        assertThat(set).hasSize(52);
        assertThatThrownBy(cardDispenser::issue).isInstanceOf(IllegalStateException.class);
    }
}
