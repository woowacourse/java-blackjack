package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 뭉치에서 카드를 한 장 뽑아서 준다.")
    void drawCard() {
        // given
        CardDeck deck = new CardDeck(new BlackJackCardsGenerator());

        // when
        Card actual = deck.draw();

        // then
        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("두 장의 카드를 한번에 뽑을 수 있다.")
    void drawDouble() {
        // given
        CardDeck deck = new CardDeck(new BlackJackCardsGenerator());

        // when
        List<Card> cards = deck.drawDouble();

        // then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드가 다 떨어지면 새로 카드를 만든다.")
    void createNewDeck() {
        // given
        CardDeck deck = new CardDeck(() -> new ArrayList<>(List.of(new Card(Pattern.CLOVER, Denomination.THREE))));

        // then
        assertThatNoException().isThrownBy(deck::drawDouble);
    }
}
