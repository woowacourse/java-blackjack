import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void 모든_카드를_생성한다() {
        CardDeck cardDeck = new CardDeck();

        Assertions.assertThat(cardDeck.getDeck()).hasSize(52);
    }

    @Test
    void 카드를_1장_드로우한다() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        Card actual = cardDeck.drawCard();

        //then
        Card expected = new Card(Pattern.DIAMOND, CardNumber.ACE);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
