import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Cards;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    @DisplayName("같은 시드의 Random을 사용하면 카드 순서가 동일하다")
    void shuffleWithSameSeed() {
        Cards firstCards = new Cards(new Random(1));
        Cards secondCards = new Cards(new Random(1));

        assertEquals(firstCards.draw().displayName(), secondCards.draw().displayName());
    }
}
