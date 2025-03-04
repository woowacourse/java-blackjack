import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackDeckGenerator;
import domain.TrumpCard;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class BlackjackDeckGeneratorTest {

    @Test
    void 덱에서_트럼프_카드를_생성할_수_있다() {
        BlackjackDeckGenerator deckGenerator = new BlackjackDeckGenerator();
        Set<TrumpCard> trumpCards = deckGenerator.generateDeck();

        assertThat(trumpCards.size()).isEqualTo(52);
    }
}
