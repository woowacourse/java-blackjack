package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("A를 포함하지 않는 카드의 총합을 구한다.")
    void calculateScore() {
        final Cards cards = new Cards(createFirstReceivedCard(CardNumber.KING, CardNumber.JACK));
        final int expected = 20;

        final int actual = cards.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 리스트에 카드를 추가한다.")
    void addCard() {
        final Cards cards = new Cards(createFirstReceivedCard(CardNumber.KING, CardNumber.JACK));
        final int expected = 3;

        cards.addCard(new Diamond(CardNumber.TWO));
        final int actual = cards.getCards().size();

        assertThat(actual).isEqualTo(expected);
    }

    private List<Card> createFirstReceivedCard(CardNumber... cardNumbers) {
        List<Card> cards = new ArrayList<>();
        for (CardNumber cardNumber : cardNumbers) {
            cards.add(new Diamond(cardNumber));
        }
        return cards;
    }
}
