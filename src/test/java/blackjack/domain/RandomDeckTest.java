package blackjack.domain;

import blackjack.domain.card.Card;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RandomDeckTest {
    private RandomDeck randomDeck;

    @BeforeEach
    void setting() {
        randomDeck = new RandomDeck();
    }

    @Test
    @DisplayName("덱에서 카드를 뽑을 수 있다.")
    void createDeck() {
        final RandomDeck randomDeck = new RandomDeck();
        assertThat(randomDeck.drawCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드를 가져오면 해당 덱에서는 카드가 한장 사라진다.")
    void drawCardTest() {
        //given
        assertThat(randomDeck).extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(52);

        //when
        Card card = randomDeck.drawCard();

        //then
        assertThat(randomDeck).extracting("cards", InstanceOfAssertFactories.collection(List.class))
                .size()
                .isEqualTo(51);

    }

    @Test
    @DisplayName("Deck은 52개의 다른 카드를 반환한다")
    void returnDifferentCardTest() {
        //given
        final HashSet<Card> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            cards.add(randomDeck.drawCard());
        }

        //when,then
        assertThat(cards)
                .size()
                .isEqualTo(52);
    }

    @Test
    @DisplayName("52개 이상을 덱에서 뽑으면 예외가 발생한다.")
    void drawExceptionTest() {
        //given
        for (int i = 0; i < 52; i++) {
            randomDeck.drawCard();
        }

        //when,then
        assertThatThrownBy(() -> randomDeck.drawCard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 업슝");
    }
}
