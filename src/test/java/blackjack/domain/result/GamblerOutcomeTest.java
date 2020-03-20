package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.card.GamblerCards;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamblerOutcomeTest {

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

        Gambler gamblerBust = new Gambler(new Name("Bust"), BettingMoney.of("1000"), bust);
        Gambler gamblerTwenty = new Gambler(new Name("twenty"), BettingMoney.of("1000"), twenty);
        Gambler gamblerBlackJack = new Gambler(new Name("blackJack"), BettingMoney.of("1000"),
            blackJack);

        Dealer dealerBust = new Dealer(bust);
        Dealer dealerTwentyOne = new Dealer(twentyOne);
        Dealer dealerTwenty = new Dealer(twenty);
        Dealer dealerNineteen = new Dealer(nineteen);
        Dealer dealerBlackJack = new Dealer(blackJack);

        assertThat(PlayerOutcome.of(gamblerTwenty, dealerNineteen)).isEqualTo(PlayerOutcome.WIN);
        assertThat(PlayerOutcome.of(gamblerBust, dealerTwentyOne)).isEqualTo(PlayerOutcome.LOSE);

        assertThat(PlayerOutcome.of(gamblerTwenty, dealerTwenty)).isEqualTo(PlayerOutcome.DRAW);
        assertThat(PlayerOutcome.of(gamblerBust, dealerBust)).isEqualTo(PlayerOutcome.LOSE);

        assertThat(PlayerOutcome.of(gamblerTwenty, dealerTwentyOne)).isEqualTo(PlayerOutcome.LOSE);
        assertThat(PlayerOutcome.of(gamblerTwenty, dealerBust)).isEqualTo(PlayerOutcome.WIN);

        assertThat(PlayerOutcome.of(gamblerBlackJack, dealerTwentyOne))
            .isEqualTo(PlayerOutcome.BLACKJACK);
        assertThat(PlayerOutcome.of(gamblerBlackJack, dealerBlackJack))
            .isEqualTo(PlayerOutcome.DRAW);
    }
}