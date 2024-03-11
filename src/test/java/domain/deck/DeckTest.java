package domain.deck;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.deck.strategy.SettedDeckGenerator;
import domain.deck.strategy.ShuffledDeckGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void createDecksTest() {
        // given
        int expectedSize = 312;

        // when
        ShuffledDeckGenerator decksGenerator = new ShuffledDeckGenerator();
        Deck deck = Deck.createByStrategy(decksGenerator);

        // then
        assertThat(deck.getCards()).hasSize(expectedSize);
    }

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void emptyDecksTest() {
        // given
        SettedDeckGenerator decksGenerator = new SettedDeckGenerator(List.of(new Card(Symbol.CLOVER, Rank.BIG_ACE)));
        Deck deck = Deck.createByStrategy(decksGenerator);
        deck.draw();

        // then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 모두 소진되었습니다.");
    }
}
