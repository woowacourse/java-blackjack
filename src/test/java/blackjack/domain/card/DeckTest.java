package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("기본 생성자로 덱을 생성한다.")
    @Test
    public void testCreateDeck() {
        //given & when
        Deck deck = createDeck();

        //then
        assertThat(deck).isNotNull();
    }

    @DisplayName("덱에서 카드를 뽑을 수 있다.")
    @Test
    public void testDrawCard() {
        //given
        Deck deck = createDeck();

        //when
        Card firstCard = deck.drawCard();
        Card secondCard = deck.drawCard();

        //then
        assertThat(firstCard).isNotEqualTo(secondCard);
    }

    @DisplayName("카드는 52장 이어야한다.")
    @Test
    public void testDeckSize() {
        //given
        Deck deck = createDeck();

        //when
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        //then
        assertThatThrownBy(() -> deck.drawCard())
                .isInstanceOf(IllegalStateException.class);
    }

    private Deck createDeck() {
        return new Deck(new ShuffledDeckGenerateStrategy());
    }


}
