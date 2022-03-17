package blackjack.domain;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardSymbol.CLUBS;
import static blackjack.domain.card.CardSymbol.HEARTS;

import blackjack.domain.card.Card;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("플레이어는 블랙잭이고, 딜러가 블랙잭이 아닐 경우 승리")
    void playerBlackjackWin() {
        Player player = new Player("pobi", new BetMoney(10000));
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(ACE, CLUBS));
        dealer.receiveCard(Card.valueOf(ACE, HEARTS));

        player.receiveCards(List.of(Card.valueOf(ACE, HEARTS), Card.valueOf(JACK, HEARTS)));
        Assertions.assertThat(GameResult.compareScore(dealer, player)).isEqualTo(GameResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어는 블랙잭이고, 딜러도 블랙잭일 경우 무승부")
    void playerBlackjackDraw() {
        Player player = new Player("pobi", new BetMoney(10000));
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(ACE, CLUBS));
        dealer.receiveCard(Card.valueOf(QUEEN, HEARTS));

        player.receiveCards(List.of(Card.valueOf(ACE, HEARTS), Card.valueOf(JACK, HEARTS)));
        Assertions.assertThat(GameResult.compareScore(dealer, player)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어가 딜러보다 점수가 높고 버스트가 아닐경우 승리")
    void playerWin() {
        Player player = new Player("pobi", new BetMoney(10000));
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(SEVEN, CLUBS));
        dealer.receiveCard(Card.valueOf(QUEEN, HEARTS));

        player.receiveCards(List.of(Card.valueOf(ACE, HEARTS), Card.valueOf(SEVEN, HEARTS)));
        Assertions.assertThat(GameResult.compareScore(dealer, player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트이고, 딜러는 버스트가 아닐경우 패배")
    void playerBust_dealerNoBust_Lose() {
        Player player = new Player("pobi", new BetMoney(10000));
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(SEVEN, CLUBS));
        dealer.receiveCard(Card.valueOf(QUEEN, HEARTS));

        player.receiveCards(List.of(Card.valueOf(QUEEN, HEARTS), Card.valueOf(THREE, HEARTS), Card.valueOf(KING,HEARTS)));
        Assertions.assertThat(GameResult.compareScore(dealer, player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트이고, 딜러도 버스트일경우 패배")
    void playerBust_dealerBust_Lose() {
        Player player = new Player("pobi", new BetMoney(10000));
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(TWO, CLUBS));
        dealer.receiveCard(Card.valueOf(QUEEN, HEARTS));
        dealer.receiveCard(Card.valueOf(KING, HEARTS));

        player.receiveCards(
                List.of(Card.valueOf(QUEEN, HEARTS), Card.valueOf(THREE, HEARTS), Card.valueOf(KING, HEARTS)));
        Assertions.assertThat(GameResult.compareScore(dealer, player)).isEqualTo(GameResult.LOSE);
    }
}
