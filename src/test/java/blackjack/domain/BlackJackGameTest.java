package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.domain.result.CompareResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @Test
    @DisplayName("BlackJackGame의 Player에게 최초 카드 두장을 준다.")
    void initializeBlackJackGame() {
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(),
                new Gamers(List.of(new Gamer("judy", new Bet(1000)),
                        new Gamer("huni", new Bet(1000)))));

        Deck deck = Deck.init();
        blackJackGame.handOutStartingCards(deck);

        assertThat(deck.size()).isEqualTo(46);
    }

    @Test
    @DisplayName("game의 승패를 계산한다.")
    void calculateResult() {
        // given
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        Gamer judy = new Gamer("judy", new Bet(1000));
        judy.receiveCard(new Card(Suit.CLOVER, Denomination.SIX));

        Gamer huni = new Gamer("huni", new Bet(1000));
        huni.receiveCard(new Card(Suit.DIAMOND, Denomination.FOUR));

        BlackJackGame blackJackGame = new BlackJackGame(dealer, new Gamers(List.of(judy, huni)));

        //when
        Map<Player, Bet> gameResult = blackJackGame.calculateResultBoard();

        //then
        assertAll(
                () -> assertThat(gameResult.get(judy).getAmount()).isEqualTo(1000),
                () -> assertThat(gameResult.get(huni).getAmount()).isEqualTo(-1000)
        );
    }

    @Test
    @DisplayName("dealer의 승패를 계산한다.")
    void calculateDealerResult() {
        // given
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        Gamer judy = new Gamer("judy", new Bet(1000));
        judy.receiveCard(new Card(Suit.CLOVER, Denomination.SIX));

        Gamer huni = new Gamer("huni", new Bet(1000));
        huni.receiveCard(new Card(Suit.DIAMOND, Denomination.FOUR));

        BlackJackGame blackJackGame = new BlackJackGame(dealer, new Gamers(List.of(judy, huni)));

        //when
        int dealerResult = blackJackGame.calculateDealerResultBoard();
        assertThat(dealerResult).isEqualTo(0);
    }
}
