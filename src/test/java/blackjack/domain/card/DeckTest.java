package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("카드를 한 장 만들어서 반환한다.")
    void createCard() {
        // give
        final Card card = Card.of(CardSymbol.DIAMOND, CardNumber.ACE);
        final Deck deck = Deck.createBy(List.of(card));

        // when
        final Card actual = deck.drawCard();

        // then
        assertThat(actual).isEqualTo(card);
    }
}
