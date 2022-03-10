package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUpCards() {
        cards = new Cards(new ArrayList<>(Arrays.asList(new Diamond(CardNumber.JACK), new Heart(CardNumber.KING))));
    }

    @Test
    @DisplayName("A를 포함하지 않는 카드의 총합을 구한다.")
    void calculateScore() {
        final int expected = 20;

        final int actual = cards.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드리스트에 카드를 추가한다.")
    void addCard() {
        final int expected = 3;

        cards.addCard(new Diamond(CardNumber.TWO));
        final int actual = cards.getCards().size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("처음 받은 카드가 2장이 아니라면, 예외를 발생한다.")
    void checkFirstReceivedCardsSize() {
        assertThatThrownBy(() -> new Cards(Arrays.asList(
                new Diamond(CardNumber.JACK),
                new Heart(CardNumber.KING),
                new Heart(CardNumber.KING))
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음 제공받는 카드는 2장이어야 합니다.");
    }
}
