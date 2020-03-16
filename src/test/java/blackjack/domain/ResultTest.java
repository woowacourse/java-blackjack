package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import blackjack.domain.game.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        dealer.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.SPADE, Symbol.JACK),
                        new Card(Suit.HEART, Symbol.TEN)
                ));
        player1.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.SPADE, Symbol.ACE),
                        new Card(Suit.CLUB, Symbol.TEN)
                ));
        player2.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Symbol.FOUR),
                        new Card(Suit.CLUB, Symbol.KING)
                ));
        player3.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.CLUB, Symbol.TEN),
                        new Card(Suit.CLUB, Symbol.KING)
                ));
    }

    @Test
    @DisplayName("승, 무, 패 결과가 잘 나오는지 확인")
    void checkResult() {
        assertThat(Result.of(dealer, player1)).isEqualTo(Result.WIN);
        assertThat(Result.of(dealer, player2)).isEqualTo(Result.LOSE);
        assertThat(Result.of(dealer, player3)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("명칭을 잘 불러오는지 확인")
    void checkgetName() {
        assertThat(Result.WIN.getName()).isEqualTo("승");
        assertThat(Result.DRAW.getName()).isEqualTo("무");
        assertThat(Result.LOSE.getName()).isEqualTo("패");
    }
}
