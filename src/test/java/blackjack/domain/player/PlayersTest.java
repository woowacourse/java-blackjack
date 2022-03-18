package blackjack.domain.player;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;

public class PlayersTest {

    @Nested
    @DisplayName("생성자는")
    class Constructor {

        @Test
        @DisplayName("8명을 초과할 때 예외를 발생시킨다.")
        void throwExceptionOverEight() {
            Deck deck = Deck.create();
            Assertions.assertThatThrownBy(() -> new Players(List.of(
                new Player(new Name("a"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("b"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("c"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("d"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("e"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("f"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("g"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("h"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("q"), HoldCards.drawTwoCards(deck))
            ))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인원수는 8명을 넘을 수 없습니다.");
        }
    }
}
