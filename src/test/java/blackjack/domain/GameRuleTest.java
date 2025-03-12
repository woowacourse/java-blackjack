package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameRuleTest {

    @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 딜러가 이긴다.")
    @Test
    void testWinnerEvaluation_dealerWin() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("player");
        player.initCardDeck(cardDeck);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);

        assertThat(dealerResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 플레이어가 이긴다.")
    @Test
    void testWinnerEvaluation_dealerLose() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.FIVE));

        Player player = new Player("player");
        player.initCardDeck(cardDeck);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);

        assertThat(dealerResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러와 플레이어 점수가 같으면 무승부다.")
    @Test
    void testWinnerEvaluation_draw() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("player");
        player.initCardDeck(cardDeck);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        assertThat(dealerResult).isEqualTo(GameResult.DRAW);
        assertThat(playerResult).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("플레이어가 버스트인 경우, 딜러가 이기고 플레이어가 패배한다.")
    @Test
    void testWinnerEvaluation_playerBusted() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Player player = new Player("player");
        player.initCardDeck(cardDeck);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        assertThat(dealerResult).isEqualTo(GameResult.WIN);
        assertThat(playerResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 버스트인 경우, 딜러가 지고 플레이어가 승리한다")
    @Test
    void testWinnerEvaluation_dealerBusted() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("player");
        player.initCardDeck(cardDeck);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.JACK));

        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        assertThat(dealerResult).isEqualTo(GameResult.LOSE);
        assertThat(playerResult).isEqualTo(GameResult.WIN);
    }
}
