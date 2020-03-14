package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.UserCards;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Player player3;


    @BeforeEach
    void setup() {
        dealer = new Dealer();
        player1 = new Player("pobi");
        player2 = new Player("hiro");
        player3 = new Player("rebecca");
        dealer.receiveInitialCards(new UserCards(
                Arrays.asList(
                        new Card(Suit.SPADE, Symbol.JACK),
                        new Card(Suit.HEART, Symbol.TEN)
                )));
        player1.receiveInitialCards(new UserCards(
                Arrays.asList(
                        new Card(Suit.SPADE, Symbol.ACE),
                        new Card(Suit.CLUB, Symbol.TEN)
                )));
        player2.receiveInitialCards(new UserCards(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Symbol.FOUR),
                        new Card(Suit.CLUB, Symbol.KING)
                )));
        player3.receiveInitialCards(new UserCards(
                Arrays.asList(
                        new Card(Suit.CLUB, Symbol.TEN),
                        new Card(Suit.CLUB, Symbol.KING)
                )));
    }

    @Test
    void checkResult() {
        assertThat(Result.of(dealer, player1)).isEqualTo(Result.WIN);
        assertThat(Result.of(dealer, player2)).isEqualTo(Result.LOSE);
        assertThat(Result.of(dealer, player3)).isEqualTo(Result.DRAW);
    }
}
