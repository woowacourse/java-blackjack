package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.PickedNumberFinder;
import domain.strategy.PickedSymbolFinder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void createCardTest() {
        // given
        int heartIndex = 0;
        int queenIndex = 10;
        PickedSymbolFinder symbolFinder = new PickedSymbolFinder(heartIndex);
        PickedNumberFinder numberFinder = new PickedNumberFinder(queenIndex);

        // when
        Card card = Card.createByStrategy(symbolFinder, numberFinder);
        Symbol symbol = card.getSymbol();
        Number number = card.getNumber();

        // then
        assertThat(symbol).isEqualTo(Symbol.HEART);
        assertThat(number).isEqualTo(Number.QUEEN);
    }
}
