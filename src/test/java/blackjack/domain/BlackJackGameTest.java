package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @Test
    @DisplayName("BlackJackGame의 Player에게 최초 카드 두장을 준다.")
    void initializeBlackJackGame() {
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(),
                List.of(new Gamer("judy"),
                        new Gamer("huni")));

        Deck deck = Deck.init();
        blackJackGame.giveFirstCards(deck);

        assertThat(deck.size()).isEqualTo(46);
    }

    @Test
    @DisplayName("game의 승패를 계산한다.")
    void calculateResult() {
        // given
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        Gamer judy = new Gamer("judy");
        judy.receiveCard(new Card(Suit.CLOVER, Denomination.SIX));

        Gamer huni = new Gamer("huni");
        huni.receiveCard(new Card(Suit.DIAMOND, Denomination.FOUR));

        BlackJackGame blackJackGame = new BlackJackGame(dealer, List.of(judy, huni));

        //when
        Map<Player, Result> gameResult = blackJackGame.calculateResultBoard();

        //then
        assertAll(
                () -> assertThat(gameResult.get(judy)).isEqualTo(Result.WIN),
                () -> assertThat(gameResult.get(huni)).isEqualTo(Result.LOSE)
        );
    }

}
