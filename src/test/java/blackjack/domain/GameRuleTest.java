package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameRuleTest {

    @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 딜러가 이긴다.")
    @Test
    void testWinnerEvaluation_dealerWin() {
        //given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
        Dealer dealer = new Dealer(cardDeck2, cardDump);

        //when
        GameRule gameRule = new GameRule();
        GameResult dealerResult = gameRule.evaluateDealerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 플레이어가 이긴다.")
    @Test
    void testWinnerEvaluation_dealerLose() {
        //given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.FIVE));

        Player player = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
        Dealer dealer = new Dealer(cardDeck2, cardDump);

        //when
        GameRule gameRule = new GameRule();
        GameResult dealerResult = gameRule.evaluateDealerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러와 플레이어 점수가 같으면 무승부다.")
    @Test
    void testWinnerEvaluation_draw() {
        //given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardDeck2, cardDump);

        //when
        GameRule gameRule = new GameRule();
        GameResult dealerResult = gameRule.evaluateDealerWin(player, dealer);
        GameResult playerResult = gameRule.evaluatePlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.DRAW);
        assertThat(playerResult).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("플레이어가 버스트인 경우, 딜러가 이기고 플레이어가 패배한다.")
    @Test
    void testWinnerEvaluation_playerBusted() {
        //given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Player player = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardDeck2, cardDump);

        //when
        GameRule gameRule = new GameRule();
        GameResult dealerResult = gameRule.evaluateDealerWin(player, dealer);
        GameResult playerResult = gameRule.evaluatePlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.WIN);
        assertThat(playerResult).isEqualTo(GameResult.LOSE);

    }

    @DisplayName("딜러가 버스트인 경우, 딜러가 지고 플레이어가 승리한다")
    @Test
    void testWinnerEvaluation_dealerBusted() {
        //given
        CardDeck cardDeck1 = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("player1", cardDeck1, cardDump);

        CardDeck cardDeck2 = new CardDeck();
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardDeck2.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Dealer dealer = new Dealer(cardDeck2, cardDump);

        //when
        GameRule gameRule = new GameRule();
        GameResult dealerResult = gameRule.evaluateDealerWin(player, dealer);
        GameResult playerResult = gameRule.evaluatePlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.LOSE);
        assertThat(playerResult).isEqualTo(GameResult.WIN);
    }
}
