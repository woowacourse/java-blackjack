package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameFinalResultTest {

    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (2승)")
    @Test
    void testGameFinalResult1() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        Player player1 = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        Player player2 = new Player("player2", cardDeck2, cardDump);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); //19
        Dealer dealer = new Dealer(cardDeck3, cardDump);

        GameFinalResult finalResult = new GameFinalResult(new GameRule());
        Map<GameResult, Integer> dealerFinalResult = finalResult.getDealerFinalResult(dealer,
                List.of(player1, player2));

        assertThat(dealerFinalResult.get(GameResult.WIN)).isEqualTo(2);
    }

    @DisplayName("딜러와 플레이어 간의 카드 총합 값 비교를 통해 최종 게임 결과를 계산한다. (1승 1패)")
    @Test
    void testGameFinalResult2() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 20
        Player player1 = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); // 16
        Player player2 = new Player("player2", cardDeck2, cardDump);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(cardDeck3, cardDump);

        GameFinalResult finalResult = new GameFinalResult(new GameRule());
        Map<GameResult, Integer> dealerFinalResult = finalResult.getDealerFinalResult(dealer,
                List.of(player1, player2));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(dealerFinalResult.get(GameResult.WIN)).isEqualTo(1);
        softAssertions.assertThat(dealerFinalResult.get(GameResult.LOSE)).isEqualTo(1);
        softAssertions.assertAll();
    }

    @DisplayName("플레이어 입장에서 딜러와의 게임 결과를 확인한다. (플레이어 승리)")
    @Test
    void testGameFinalResultFromPlayer_playerWin() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 20
        Player player1 = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(cardDeck3, cardDump);

        GameFinalResult finalResult = new GameFinalResult(new GameRule());
        GameResult gameResult = finalResult.getGameResultFromPlayer(player1, dealer);

        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어 입장에서 딜러와의 게임 결과를 확인한다. (플레이어 패배)")
    @Test
    void testGameFinalResultFromPlayer_playerLose() {
        // given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.FIVE)); // 14
        Player player1 = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck3 = new CardDeck();
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck3.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 19
        Dealer dealer = new Dealer(cardDeck3, cardDump);

        GameFinalResult finalResult = new GameFinalResult(new GameRule());
        GameResult gameResult = finalResult.getGameResultFromPlayer(player1, dealer);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }
}
