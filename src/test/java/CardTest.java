import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Card;
import except.BlackJackException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CardTest {

    @Test
    void 숫자_카드를_생성할_수_있다(){
        Card card = new Card();
        String number = card.generateCard();
        assertThatCode(() -> Integer.parseInt(number))
                .doesNotThrowAnyException();
    }

    @Test
    void 문자_카드를_생성할_수_있다(){
        Card card = new Card();
        String stringCard = card.generateCard();
        assertThatThrownBy(() -> Integer.parseInt(stringCard))
                .isInstanceOf(BlackJackException.class);
    }
}
