package game;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumber;
import card.CardShape;
import deck.Deck;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Dealer;
import participant.Player;
import participant.Players;
import participant.Profit;

class BlackjackGameTest {

    int misoBettingMoney = 10000;
    int yulmuBettingMoney = 20000;
    Player miso = new Player("미소", misoBettingMoney);
    Player yulmu = new Player("율무", yulmuBettingMoney);

    Dealer dealer;
    Players players;
    Deck deck;
    BlackjackGame blackjackGame;

    @BeforeEach
    void beforeEach() {
        dealer = new Dealer();
        players = new Players(List.of(miso, yulmu));
        deck = new Deck(() -> List.of(
                new Card(CardShape.SPADE, CardNumber.THREE),
                new Card(CardShape.SPADE, CardNumber.TEN),
                new Card(CardShape.CLOVER, CardNumber.TEN),
                new Card(CardShape.HEART, CardNumber.TEN),
                new Card(CardShape.DIAMOND, CardNumber.TEN),
                new Card(CardShape.DIAMOND, CardNumber.THREE)
        ));
        blackjackGame = new BlackjackGame();
    }

    @Test
    @DisplayName("각 플레이어에게 2장의 카드를 분배한다.")
    void test1() {
        // given

        // when
        blackjackGame.distributeTwoCardsToParticipants(dealer, players, deck);

        // then
        for (Player player : players.getPlayers()) {
            assertThat(player.getCards().size())
                    .isEqualTo(2);
        }
    }

    @Test
    @DisplayName("Players 카드가 Bust가 아니라면 한 장 더 받는다.")
    void test2() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.THREE));
        miso.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));

        // when
        blackjackGame.runParticipantTurn(miso, deck);

        // then
        assertThat(miso.getCards().size())
                .isEqualTo(4);
    }

    @Test
    @DisplayName("Players 카드가 Bust가 아니라면 한 장 더 받는다. (ACE 포함)")
    void test3() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));

        // when
        blackjackGame.runParticipantTurn(miso, deck);

        // then
        assertThat(miso.getCards().size())
                .isEqualTo(4);
    }

    @Test
    @DisplayName("Players 카드가 Bust라면 카드를 받지 않는다.")
    void test4() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.KING));
        miso.receiveCard(new Card(CardShape.DIAMOND, CardNumber.KING));

        // when
        blackjackGame.runParticipantTurn(miso, deck);

        // then
        assertThat(miso.getCards().size())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("Dealer 카드가 Bust가 아니라면 한 장 더 받는다.")
    void test5() {
        // given
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));

        // when
        blackjackGame.runParticipantTurn(dealer, deck);

        // then
        assertThat(dealer.getCards().size())
                .isEqualTo(4);
    }

    @Test
    @DisplayName("Dealer 카드가 Bust가 아니라면 한 장 더 받는다. (ACE 포함)")
    void test6() {
        // given
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));

        // when
        blackjackGame.runParticipantTurn(dealer, deck);

        // then
        assertThat(dealer.getCards().size())
                .isEqualTo(4);
    }

    @Test
    @DisplayName("Dealer 카드가 Bust라면 카드를 받지 않는다.")
    void test7() {
        // given
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.KING));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.KING));

        // when
        blackjackGame.runParticipantTurn(dealer, deck);

        // then
        assertThat(dealer.getCards().size())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("Player의 돈을 업데이트한다.")
    void test8() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.KING));

        // when
        blackjackGame.updatePlayerMoney(dealer, players);

        // then
        assertThat(miso.getEarnedMoney())
                .isEqualTo(misoBettingMoney + misoBettingMoney * GameResult.BLACKJACK.getRate());
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (블랙잭)")
    void test9() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        blackjackGame.updatePlayerMoney(dealer, players);

        // when
        Map<Playable, Profit> gameResults = blackjackGame.calculateParticipantGameResults(dealer, players);
        Profit profit = gameResults.get(miso);

        // then
        assertThat(profit.getAmount())
                .isEqualTo((int) (misoBettingMoney * GameResult.BLACKJACK.getRate()));
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (승)")
    void test10() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        blackjackGame.updatePlayerMoney(dealer, players);

        // when
        Map<Playable, Profit> gameResults = blackjackGame.calculateParticipantGameResults(dealer, players);
        Profit profit = gameResults.get(miso);

        // then
        assertThat(profit.getAmount())
                .isEqualTo((int) (misoBettingMoney * GameResult.WIN.getRate()));
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (패)")
    void test11() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        blackjackGame.updatePlayerMoney(dealer, players);

        // when
        Map<Playable, Profit> gameResults = blackjackGame.calculateParticipantGameResults(dealer, players);
        Profit profit = gameResults.get(miso);

        // then
        assertThat(profit.getAmount())
                .isEqualTo((int) (misoBettingMoney * GameResult.LOSE.getRate()));
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (무)")
    void test12() {
        // given
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        blackjackGame.updatePlayerMoney(dealer, players);

        // when
        Map<Playable, Profit> gameResults = blackjackGame.calculateParticipantGameResults(dealer, players);
        Profit profit = gameResults.get(miso);

        // then
        assertThat(profit.getAmount())
                .isEqualTo((int) (misoBettingMoney * GameResult.DRAW.getRate()));
    }

    @Test
    @DisplayName("Dealer 게임 결과를 반환한다.")
    void test13() {
        // given
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.FIVE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.TEN));
        yulmu.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        yulmu.receiveCard(new Card(CardShape.HEART, CardNumber.TEN));
        blackjackGame.updatePlayerMoney(dealer, players);

        // when
        Map<Playable, Profit> gameResults = blackjackGame.calculateParticipantGameResults(dealer, players);

        // then
        assertThat(gameResults.get(dealer).getAmount())
                .isEqualTo((int) ((misoBettingMoney * GameResult.WIN.getRate() +
                        yulmuBettingMoney * GameResult.WIN.getRate()) * -1));
    }
}
