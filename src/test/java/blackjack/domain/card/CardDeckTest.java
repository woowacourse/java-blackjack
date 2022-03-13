package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("CardDeck 생성시 카드가 중복되면 예외가 발생한다.")
    void duplicatedCards() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        List<Card> cards = List.of(card1, card1);

        // then
        assertThatThrownBy(() -> new CardDeck(cards))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 카드는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("카드 뭉치에서 카드를 한 장 뽑아서 준다.")
    void drawCard() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        CardDeck deck = new CardDeck(List.of(card1, card2));

        // when
        Card actual = deck.draw();

        // then
        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("두 장의 카드를 한 번에 뽑을 수 있다.")
    void drawDouble() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        CardDeck deck = new CardDeck(List.of(card1, card2));

        // when
        List<Card> cards = deck.drawDouble();

        // then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드가 다 떨어지면 더 이상 뽑을 수 없다.")
    void notDraw() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        CardDeck deck = new CardDeck(List.of(card1));
        deck.draw();

        // then
        assertThatThrownBy(deck::draw)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 더 이상 뽑을 수 있는 카드가 없습니다.");

    }
}
