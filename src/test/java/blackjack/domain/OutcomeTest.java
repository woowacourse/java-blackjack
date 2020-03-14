package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.card.UserCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutcomeTest {

    private UserCards playerCards;
    private UserCards dealerCards;

    @BeforeEach
    void resetVariable() {
        playerCards = new UserCards();
        dealerCards = new UserCards();
    }

    @DisplayName("승 산정 : player가 dealer보다 큼")
    @Test
    void calculateWinPlayerMoreThanDealer() {
        playerCards.add(new Card(Type.CLUB, Symbol.TEN));
        playerCards.add(new Card(Type.CLUB, Symbol.ACE));
        dealerCards.add(new Card(Type.SPADE, Symbol.QUEEN));
        dealerCards.add(new Card(Type.SPADE, Symbol.KING));
        assertThat(Outcome.from(playerCards, dealerCards)).isEqualTo(Outcome.PLAYER_WIN);
    }

    @DisplayName("승 산정 : dealer만 Bust")
    @Test
    void calculateWinDealerBust() {
        playerCards.add(new Card(Type.CLUB, Symbol.ACE));
        dealerCards.add(new Card(Type.SPADE, Symbol.QUEEN));
        dealerCards.add(new Card(Type.SPADE, Symbol.JACK));
        dealerCards.add(new Card(Type.SPADE, Symbol.KING));
        assertThat(Outcome.from(playerCards, dealerCards)).isEqualTo(Outcome.PLAYER_WIN);
    }

    @DisplayName("무 산정")
    @Test
    void calculateDraw() {
        playerCards.add(new Card(Type.CLUB, Symbol.JACK));
        playerCards.add(new Card(Type.CLUB, Symbol.KING));
        dealerCards.add(new Card(Type.SPADE, Symbol.JACK));
        dealerCards.add(new Card(Type.SPADE, Symbol.KING));
        assertThat(Outcome.from(playerCards, dealerCards)).isEqualTo(Outcome.PLAYER_DRAW);
    }

    @DisplayName("패 산정 : player가 dealer보다 작음")
    @Test
    void calculateLosePlayerLessThanDealer() {
        playerCards.add(new Card(Type.CLUB, Symbol.QUEEN));
        playerCards.add(new Card(Type.CLUB, Symbol.THREE));
        dealerCards.add(new Card(Type.SPADE, Symbol.QUEEN));
        dealerCards.add(new Card(Type.SPADE, Symbol.KING));
        assertThat(Outcome.from(playerCards, dealerCards)).isEqualTo(Outcome.PLAYER_LOSE);
    }

    @DisplayName("패 산정 : player Bust")
    @Test
    void calculateWinPlayerBust() {
        playerCards.add(new Card(Type.CLUB, Symbol.QUEEN));
        playerCards.add(new Card(Type.CLUB, Symbol.JACK));
        playerCards.add(new Card(Type.CLUB, Symbol.KING));
        dealerCards.add(new Card(Type.SPADE, Symbol.QUEEN));
        assertThat(Outcome.from(playerCards, dealerCards)).isEqualTo(Outcome.PLAYER_LOSE);

        dealerCards.add(new Card(Type.SPADE, Symbol.JACK));
        dealerCards.add(new Card(Type.SPADE, Symbol.KING));
        assertThat(Outcome.from(playerCards, dealerCards)).isEqualTo(Outcome.PLAYER_LOSE);
    }
}