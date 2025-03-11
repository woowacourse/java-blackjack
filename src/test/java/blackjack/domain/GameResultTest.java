package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {
    @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 딜러가 이긴다.")
    @Test
    void testWinnerEvaluation_dealerWin() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 플레이어 중 21 혹은 21에 가까운 숫자를 가진 플레이어가 이긴다.")
    @Test
    void testWinnerEvaluation_dealerLose() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.FIVE));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러와 플레이어 점수가 같으면 무승부다.")
    @Test
    void testWinnerEvaluation_draw() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.DRAW);
        assertThat(playerResult).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("플레이어가 버스트인 경우, 딜러가 이기고 플레이어가 패배한다.")
    @Test
    void testWinnerEvaluation_playerBusted() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.WIN);
        assertThat(playerResult).isEqualTo(GameResult.LOSE);

    }

    @DisplayName("딜러가 버스트인 경우, 딜러가 지고 플레이어가 승리한다")
    @Test
    void testWinnerEvaluation_dealerBusted() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.LOSE);
        assertThat(playerResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어가 블랙잭인 경우, 같은 21이어도 플레이어가 이긴다.")
    @Test
    void testWinnerEvaluation_playerBlackjack() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.FIVE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.SIX));

        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.LOSE);
        assertThat(playerResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어와 딜러 둘다 블랙잭인 겨웅 무승부다.")
    @Test
    void testWinnerEvaluation_BlackjackDraw() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.DRAW);
        assertThat(playerResult).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("딜러가 블랙잭이고 플레이어가 일반 21인 경우 딜러가 이긴다.")
    @Test
    void testWinnerEvaluation_dealerBlackjack() {
        //given
        CardHand cardHand1 = new CardHand();
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.FIVE));
        cardHand1.add(new Card(CardSuit.CLUB, CardRank.SIX));

        Player player = TestUtil.createPlayerOf("player", cardHand1);

        CardHand cardHand2 = new CardHand();
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardHand2.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Dealer dealer = new Dealer(cardHand2);

        //when
        GameResult dealerResult = GameResult.checkDealerWin(player, dealer);
        GameResult playerResult = GameResult.checkPlayerWin(player, dealer);

        //then
        assertThat(dealerResult).isEqualTo(GameResult.WIN);
        assertThat(playerResult).isEqualTo(GameResult.LOSE);
    }
}