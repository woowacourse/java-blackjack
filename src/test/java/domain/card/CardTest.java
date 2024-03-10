package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void createCardTest() {
        // given
        Symbol heart = Symbol.HEART;
        Rank queen = Rank.QUEEN;

        String expectedSymbol = Symbol.HEART.getValue();
        String expectedRank = Rank.QUEEN.getValue();

        // when
        Card card = new Card(heart, queen);
        String symbol = card.getSymbolValue();
        String rank = card.getRankValue();

        // then
        assertThat(symbol).isEqualTo(expectedSymbol);
        assertThat(rank).isEqualTo(expectedRank);
    }
}
