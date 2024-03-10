package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.strategy.SettedDecksGenerator;
import domain.strategy.ShuffledDecksGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DecksTest {

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void createDecksTest() {
        // given
        int expectedSize = 312;

        // when
        ShuffledDecksGenerator decksGenerator = new ShuffledDecksGenerator();
        Decks decks = Decks.createByStrategy(decksGenerator);

        // then
        assertThat(decks.getCards()).hasSize(expectedSize);
    }

    @DisplayName("52 * 6개의 카드를 생성한다.")
    @Test
    void emptyDecksTest() {
        // given
        SettedDecksGenerator decksGenerator = new SettedDecksGenerator(List.of(new Card(Symbol.CLOVER, Rank.BIG_ACE)));
        Decks decks = Decks.createByStrategy(decksGenerator);
        decks.draw();

        // then
        assertThatThrownBy(decks::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 모두 소진되었습니다.");
    }
}
