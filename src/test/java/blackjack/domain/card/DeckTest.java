package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DeckTest {
    @Test
    @DisplayName("덱을 생성한다.")
    void constructorDeckTest() {
        List<Card> cards = List.of(new Card(Shape.CLOVER, Letter.EIGHT));

        assertThatNoException().isThrownBy(() -> new Deck(cards));
    }

    @Test
    @DisplayName("중복된 카드를 주입하면 에러를 반환한다")
    void throwExceptionWhenDuplicateCard() {
        List<Card> cards = List.of(new Card(Shape.DIAMOND, Letter.JACK), new Card(Shape.DIAMOND, Letter.JACK));

        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드가 존재합니다.");
    }

    @Test
    @DisplayName("카드를 뽑는다")
    void drawCardTest() {
        //given
        List<Card> cards = List.of(new Card(Shape.DIAMOND, Letter.JACK));
        Deck deck = new Deck(cards);

        //when
        Card actual = deck.drawCard();
        Card expected = cards.get(0);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("덱 반환 테스트")
    void getCardsTest() {
        List<Card> cards = new ArrayList<>(
                Arrays.asList(
                        new Card(Shape.CLOVER, Letter.ACE),
                        new Card(Shape.DIAMOND, Letter.JACK)));
        Deck deck = new Deck(cards);

        assertThat(deck.getCards()).isEqualTo(cards);
    }
}
