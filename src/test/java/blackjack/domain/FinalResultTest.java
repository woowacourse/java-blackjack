package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalResultTest {
    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (2승)")
    @Test
    void testGameReport1() {
        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.add(new Card(CardSuit.SPADE, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player1 = new Player("player1");
        player1.initCardDeck(cardDeck1);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player2 = new Player("player2");
        player2.initCardDeck(cardDeck2);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.JACK));

        FinalResult finalResult = new FinalResult();
        Map<GameResult, Integer> dealerResult = finalResult.createDealerFinalResult(dealer, List.of(player1, player2));

        assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(2);
    }

    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (1승 1패)")
    @Test
    void testGameReport2() {
        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.ACE));

        Player player1 = new Player("player1");
        player1.initCardDeck(cardDeck1);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player2 = new Player("player2");
        player2.initCardDeck(cardDeck2);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.JACK));

        FinalResult finalResult = new FinalResult();
        Map<GameResult, Integer> dealerResult = finalResult.createDealerFinalResult(dealer, List.of(player1, player2));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(1);
        softAssertions.assertThat(dealerResult.get(GameResult.LOSE)).isEqualTo(1);
        softAssertions.assertAll();
    }

    @DisplayName("플레이어 입장에서 딜러와의 게임 결과를 확인한다. (플레이어 승리)")
    @Test
    void testGameReportFromPlayer_playerWin() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.ACE));

        Player player = new Player("player");
        player.initCardDeck(cardDeck);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.JACK));

        FinalResult finalResult = new FinalResult();
        GameResult gameResult = finalResult.createPlayerFinalResult(player, dealer);

        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어 입장에서 딜러와의 게임 결과를 확인한다. (플레이어 패배)")
    @Test
    void testGameReportFromPlayer_playerLose() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.FIVE));

        Player player = new Player("player");
        player.initCardDeck(cardDeck);

        CardDump cardDump = new CardDump();
        Dealer dealer = new Dealer(cardDump);

        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.getCardDeck().add(new Card(CardSuit.CLUB, CardRank.JACK));

        FinalResult finalResult = new FinalResult();
        GameResult gameResult = finalResult.createPlayerFinalResult(player, dealer);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }
}
