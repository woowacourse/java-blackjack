package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDump;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("처음에 플레이어와 딜러는 각자 카드 2장씩 받는다.")
    @Test
    void test_distributeInitialCards() {
        // given
        Player player = new Player("player", new CardDeck());
        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardDeck());
        BlackjackGame game = new BlackjackGame(players, dealer, CardDump.shuffledDump());

        // when
        game.distributeInitialCards();

        // then
        assertThat(player.getCardSize()).isEqualTo(2);
        assertThat(dealer.getCardSize()).isEqualTo(2);
    }

    @DisplayName("딜러는 16이상이 될때까지 카드를 자동으로 더 받는다.")
    @Test
    void test_dealerTurn() {
        // given
        Player player = new Player("player", new CardDeck());
        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardDeck());
        BlackjackGame game = new BlackjackGame(players, dealer, CardDump.shuffledDump());

        // when
        game.dealerTurn();

        // then
        int score = dealer.calculateScore();
        assertThat(score).isGreaterThan(16);
    }

    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (2승)")
    @Test
    void testGameReport1() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        Player player1 = new Player("player1", cardDeck1);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        Player player2 = new Player("player2", cardDeck2);

        List<Player> players = List.of(player1, player2);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); //19
        Dealer dealer = new Dealer(cardDeck3);

        BlackjackGame game = new BlackjackGame(players, dealer, CardDump.shuffledDump());
        Map<GameResult, Integer> dealerResult = game.getDealerResult(dealer, players);
        assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(2);
    }

    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (1승 1패)")
    @Test
    void testGameReport2() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 20
        Player player1 = new Player("player1", cardDeck1);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); // 16
        Player player2 = new Player("player2", cardDeck2);

        List<Player> players = List.of(player1, player2);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(cardDeck3);

        BlackjackGame game = new BlackjackGame(players, dealer, CardDump.shuffledDump());
        Map<GameResult, Integer> dealerGameReport = game.getDealerResult(dealer, players);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(dealerGameReport.get(GameResult.WIN)).isEqualTo(1);
        softAssertions.assertThat(dealerGameReport.get(GameResult.LOSE)).isEqualTo(1);
        softAssertions.assertAll();
    }

    @DisplayName("플레이어 입장에서 딜러와의 게임 결과를 확인한다. (플레이어 승리)")
    @Test
    void testGameReportFromPlayer_playerWin() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 20
        Player player1 = new Player("player1", cardDeck1);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(cardDeck3);

        BlackjackGame game = new BlackjackGame(List.of(player1), dealer, CardDump.shuffledDump());
        GameResult playerResult = game.getPlayerResult(player1, dealer);

        assertThat(playerResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어 입장에서 딜러와의 게임 결과를 확인한다. (플레이어 패배)")
    @Test
    void testGameReportFromPlayer_playerLose() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.FIVE)); // 14
        Player player1 = new Player("player1", cardDeck1);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(cardDeck3);

        BlackjackGame game = new BlackjackGame(List.of(player1), dealer, CardDump.shuffledDump());
        GameResult playerResult = game.getPlayerResult(player1, dealer);

        assertThat(playerResult).isEqualTo(GameResult.LOSE);
    }

}