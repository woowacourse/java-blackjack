package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @Test
    @DisplayName("52장의 트럼프 카드를 모두 생성하는지 테스트")
    void createAllCardTest() {
        Deck deck = Deck.createAllCard();

        for (int i = 0; i < 52; i++) {
            Assertions.assertThatNoException().isThrownBy(deck::draw);
        }
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
        Deck deck = new Deck(List.of(new Card(Shape.DIAMOND, Letter.JACK)));

        //when
        Card actual = deck.draw();
        Card expected = new Card(Shape.DIAMOND, Letter.JACK);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
