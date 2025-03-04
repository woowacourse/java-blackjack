import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Card;
import domain.Deck;
import except.BlackJackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DeckTest {

    @Test
    void 숫자_트럼프_카드를_덱에서_뽑을_수_있다(){
        Deck deck = new Deck();
        String number = deck.drawCard();
        assertThatCode(() -> Integer.parseInt(number))
                .doesNotThrowAnyException();
    }

    @Test
    void 문자_트럼프_카드를_덱에서_뽑을_수_있다(){
        Deck deck = new Deck();
        String suit = deck.drawCard();
        assertThatThrownBy(() -> Integer.parseInt(suit))
                .isInstanceOf(BlackJackException.class);
    }
}
