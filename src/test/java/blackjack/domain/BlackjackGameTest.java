package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDump;
import blackjack.domain.card.CardHand;
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
        Player player = TestUtil.createPlayerFromName("player");

        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardHand());
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
        Player player = TestUtil.createPlayerFromName("player");

        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardHand());
        BlackjackGame game = new BlackjackGame(players, dealer, CardDump.shuffledDump());

        // when
        game.dealerTurn();

        // then
        int score = dealer.getScore().intValue();
        assertThat(score).isGreaterThan(16);
    }

    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (2승)")
    @Test
    void testGameReport1() {
        // given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        Player player1 = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        Player player2 = TestUtil.createPlayerOf("player", cardHand2);

        List<Player> players = List.of(player1, player2);

        CardHand cardHand3 = new CardHand();
        cardHand3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand3.add(new Card(CardSuit.CLUB, CardRank.JACK)); //19
        Dealer dealer = new Dealer(cardHand3);

        BlackjackGame game = new BlackjackGame(players, dealer, CardDump.shuffledDump());
        Map<GameResult, Integer> dealerResult = game.getDealerResult(dealer, players);
        assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(2);
    }

    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (1승 1패)")
    @Test
    void testGameReport2() {
        // given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 20
        Player player1 = TestUtil.createPlayerOf("player1", cardHand1);


        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); // 16
        Player player2 = TestUtil.createPlayerOf("player2", cardHand2);

        List<Player> players = List.of(player1, player2);

        CardHand CardHand3 = new CardHand();
        CardHand3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        CardHand3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(CardHand3);

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
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 20
        Player player1 = TestUtil.createPlayerOf("player1", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(cardHand2);

        BlackjackGame game = new BlackjackGame(List.of(player1), dealer, CardDump.shuffledDump());
        GameResult playerResult = game.getPlayerResult(player1, dealer);

        assertThat(playerResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어 입장에서 딜러와의 게임 결과를 확인한다. (플레이어 패배)")
    @Test
    void testGameReportFromPlayer_playerLose() {
        // given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.FIVE)); // 14
        Player player1 = TestUtil.createPlayerOf("player1", cardHand1);

        CardHand CardHand3 = new CardHand();
        CardHand3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        CardHand3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(CardHand3);

        BlackjackGame game = new BlackjackGame(List.of(player1), dealer, CardDump.shuffledDump());
        GameResult playerResult = game.getPlayerResult(player1, dealer);

        assertThat(playerResult).isEqualTo(GameResult.LOSE);
    }

}