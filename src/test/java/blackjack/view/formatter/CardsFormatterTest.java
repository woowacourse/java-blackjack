package blackjack.view.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.deck.Card;
import blackjack.model.deck.Score;
import blackjack.model.deck.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsFormatterTest {

    @Test
    @DisplayName("카드 목록을 형식에 맞게 포맷한다.")
    void format() {
        List<Card> cards = List.of(Card.from(Shape.DIA, Score.THREE), Card.from(Shape.CLOVER, Score.NINE),
                Card.from(Shape.DIA, Score.EIGHT));
        assertThat(CardsFormatter.format(cards)).isEqualTo("3다이아몬드, 9클로버, 8다이아몬드");
    }
}
