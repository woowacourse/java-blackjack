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
    private Player pobi;
    private Player hiro;
    private Player rebecca;
    private Player Jay;

    @BeforeEach
    void setup() {
        dealer = new Dealer();
        pobi = new Player("pobi");
        hiro = new Player("hiro");
        rebecca = new Player("rebecca");
        Jay = new Player("Jay");
        dealer.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.SPADE, Symbol.EIGHT),
                        new Card(Suit.HEART, Symbol.TEN)
                ));
        pobi.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.SPADE, Symbol.ACE),
                        new Card(Suit.CLUB, Symbol.TEN)
                ));
        hiro.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.CLUB, Symbol.TEN),
                        new Card(Suit.CLUB, Symbol.KING)
                ));
        rebecca.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.DIAMOND, Symbol.FOUR),
                        new Card(Suit.CLUB, Symbol.KING)
                ));
        Jay.receiveInitialCards(
                Arrays.asList(
                        new Card(Suit.CLUB, Symbol.EIGHT),
                        new Card(Suit.CLUB, Symbol.KING)
                ));

    }

    @Test
    @DisplayName("승, 무, 패 결과가 잘 나오는지 확인")
    void checkResult() {
        assertThat(Result.of(dealer, pobi)).isEqualTo(Result.BLACKJACK);
        assertThat(Result.of(dealer, hiro)).isEqualTo(Result.WIN);
        assertThat(Result.of(dealer, rebecca)).isEqualTo(Result.LOSE);
        assertThat(Result.of(dealer, Jay)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("명칭을 잘 불러오는지 확인")
    void checkgetName() {
        assertThat(Result.BLACKJACK.getName()).isEqualTo("블랙잭");
        assertThat(Result.WIN.getName()).isEqualTo("승");
        assertThat(Result.LOSE.getName()).isEqualTo("패");
        assertThat(Result.DRAW.getName()).isEqualTo("무");
    }
}
