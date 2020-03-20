package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.GamblerCards;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerOutcomeTest {

    @DisplayName("정적 팩터리")
    @Test
    void calculateWinPlayerMoreThanDealer() {
        GamblerCards bust = new GamblerCards();
        bust.add(new Card(Type.CLUB, Symbol.KING));
        bust.add(new Card(Type.CLUB, Symbol.QUEEN));
        bust.add(new Card(Type.CLUB, Symbol.JACK));

        GamblerCards twentyOne = new GamblerCards();
        twentyOne.add(new Card(Type.CLUB, Symbol.KING));
        twentyOne.add(new Card(Type.CLUB, Symbol.QUEEN));
        twentyOne.add(new Card(Type.CLUB, Symbol.ACE));

        GamblerCards twenty = new GamblerCards();
        twenty.add(new Card(Type.CLUB, Symbol.KING));
        twenty.add(new Card(Type.CLUB, Symbol.QUEEN));

        GamblerCards nineteen = new GamblerCards();
        nineteen.add(new Card(Type.CLUB, Symbol.KING));
        nineteen.add(new Card(Type.CLUB, Symbol.NINE));

        GamblerCards blackJack = new GamblerCards();
        blackJack.add(new Card(Type.CLUB, Symbol.KING));
        blackJack.add(new Card(Type.CLUB, Symbol.ACE));

        Player playerBust = new Player(new Name("Bust"), BettingMoney.of("1000"), bust);
        Player playerTwenty = new Player(new Name("twenty"), BettingMoney.of("1000"), twenty);
        Player playerBlackJack = new Player(new Name("blackJack"), BettingMoney.of("1000"),
            blackJack);

        Dealer dealerBust = new Dealer(bust);
        Dealer dealerTwentyOne = new Dealer(twentyOne);
        Dealer dealerTwenty = new Dealer(twenty);
        Dealer dealerNineteen = new Dealer(nineteen);
        Dealer dealerBlackJack = new Dealer(blackJack);

        assertThat(PlayerOutcome.of(playerTwenty, dealerNineteen)).isEqualTo(PlayerOutcome.WIN);
        assertThat(PlayerOutcome.of(playerBust, dealerTwentyOne)).isEqualTo(PlayerOutcome.LOSE);

        assertThat(PlayerOutcome.of(playerTwenty, dealerTwenty)).isEqualTo(PlayerOutcome.DRAW);
        assertThat(PlayerOutcome.of(playerBust, dealerBust)).isEqualTo(PlayerOutcome.LOSE);

        assertThat(PlayerOutcome.of(playerTwenty, dealerTwentyOne)).isEqualTo(PlayerOutcome.LOSE);
        assertThat(PlayerOutcome.of(playerTwenty, dealerBust)).isEqualTo(PlayerOutcome.WIN);

        assertThat(PlayerOutcome.of(playerBlackJack, dealerTwentyOne))
            .isEqualTo(PlayerOutcome.BLACKJACK);
        assertThat(PlayerOutcome.of(playerBlackJack, dealerBlackJack))
            .isEqualTo(PlayerOutcome.DRAW);
    }
}