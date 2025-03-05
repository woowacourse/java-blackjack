import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void 모든_카드를_생성한다() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when & then
        assertThat(cardDeck.getDeck()).hasSize(52);
    }

    @Test
    void 카드를_1장_드로우한다() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        Card actual = cardDeck.drawCard();

        //then
        Card expected = new Card(Pattern.DIAMOND, CardNumber.ACE);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 게임_시작을_위해_카드를_2장_드로우한다() {
        //given
        CardDeck cardDeck = new CardDeck();

        //when
        List<Card> actual = cardDeck.drawCardWhenStart();

        //then
        List<Card> expected = List.of(
                new Card(Pattern.DIAMOND, CardNumber.ACE),
                new Card(Pattern.DIAMOND, CardNumber.TWO));
        assertThat(actual).containsExactlyElementsOf(expected);
    }
}
