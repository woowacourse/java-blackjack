import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void 모든_카드를_생성한다() {
        CardDeck cardDeck = new CardDeck();

        Assertions.assertThat(cardDeck.getDeck()).hasSize(52);
    }

}
