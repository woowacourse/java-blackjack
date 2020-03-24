package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Symbol;
import domain.card.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
    private Cards playerCards;
    private Cards dealerCards;

    @BeforeEach
    void init() {
        playerCards = new Cards();
        dealerCards = new Cards();
        playerCards.add(new Card(Type.CLUB, Symbol.ACE));
        dealerCards.add(new Card(Type.CLUB, Symbol.TEN));
    }

    @Test
    @DisplayName("Win Test")
    void win() {
        Assertions.assertThat(WinningResult.of(playerCards,dealerCards))
                .isEqualTo(WinningResult.WIN);
    }

    @Test
    @DisplayName("Draw test")
    void draw() {
        playerCards.add(new Card(Type.HEART, Symbol.TWO));
        dealerCards.add(new Card(Type.CLUB, Symbol.THREE));
        Assertions.assertThat(WinningResult.of(playerCards, dealerCards))
                .isEqualTo(WinningResult.DRAW);
    }

    @Test
    @DisplayName("Lose test")
    void lose() {
        dealerCards.add(new Card(Type.CLUB, Symbol.TWO));
        Assertions.assertThat(WinningResult.of(playerCards, dealerCards))
                .isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("Blackjack test")
    void blackjack() {
        playerCards.add(new Card(Type.CLUB, Symbol.JACK));
        Assertions.assertThat(WinningResult.of(playerCards, dealerCards))
                .isEqualTo(WinningResult.BLACKJACK);
    }
}
