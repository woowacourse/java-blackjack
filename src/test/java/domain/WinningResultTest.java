package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Symbol;
import domain.card.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
    @Test
    @DisplayName("WinningResult 통합 테스트")
    void WinningResultTest() {
        Cards playerCards = new Cards();
        Cards dealerCards = new Cards();
        playerCards.add(new Card(Type.CLUB, Symbol.ACE));
        dealerCards.add(new Card(Type.CLUB, Symbol.TEN));
        Assertions.assertThat(WinningResult.of(playerCards,dealerCards))
                .isEqualTo(WinningResult.WIN);

        dealerCards.add(new Card(Type.CLUB, Symbol.TWO));
        Assertions.assertThat(WinningResult.of(playerCards,dealerCards))
                .isEqualTo(WinningResult.LOSE);

        playerCards.add(new Card(Type.CLUB, Symbol.JACK));
        Assertions.assertThat(WinningResult.of(playerCards,dealerCards))
                .isEqualTo(WinningResult.BLACKJACK);
    }
}
