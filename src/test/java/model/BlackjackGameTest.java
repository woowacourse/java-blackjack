package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardType;
import model.participant.Dealer;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("처음 게임 실행 시 각 플레이어에게 2장의 카드를 분배한다.")
    void test1() {
        // given
        List<String> playerNames = List.of("미소", "율무", "헤일러");

        // when
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        blackjackGame.distributeTwoCardsToParticipants();

        // then
        for (Player player : blackjackGame.getPlayers().getPlayers()) {
            assertThat(player.getCards().size()).isEqualTo(2);
        }
    }

    @Test
    @DisplayName("Players 카드가 Bust가 아니라면 한 장 더 받는다.")
    void test2() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.THREE));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.THREE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.THREE));

        // when
        blackjackGame.runPlayerTurn(miso);

        // then
        assertThat(miso.getCards().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Players 카드가 Bust가 아니라면 한 장 더 받는다. (ACE 포함)")
    void test3() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.ACE));

        // when
        blackjackGame.runPlayerTurn(miso);

        // then
        assertThat(miso.getCards().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Players 카드가 Bust라면 카드를 받지 않는다.")
    void test4() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.KING));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.KING));

        // when
        blackjackGame.runPlayerTurn(miso);

        // then
        assertThat(miso.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Dealer 카드가 Bust가 아니라면 한 장 더 받는다.")
    void test5() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.THREE));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.THREE));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.THREE));

        // when
        blackjackGame.runDealerTurn();

        // then
        assertThat(dealer.getCards().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Dealer 카드가 Bust가 아니라면 한 장 더 받는다.")
    void test6() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.ACE));

        // when
        blackjackGame.runDealerTurn();

        // then
        assertThat(dealer.getCards().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Dealer 카드가 Bust라면 카드를 받지 않는다.")
    void test7() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.KING));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.KING));

        // when
        blackjackGame.runDealerTurn();

        // then
        assertThat(dealer.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (승)")
    void test8() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.ACE));

        // when
        Map<Player, GameResult> playerGameResult = blackjackGame.calculatePlayerGameResult();

        // then
        for (Entry<Player, GameResult> entry : playerGameResult.entrySet()) {
            assertThat(entry.getKey()).isEqualTo(miso);
            assertThat(entry.getValue()).isEqualTo(GameResult.WIN);
        }
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (패)")
    void test9() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.JACK));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.ACE));

        // when
        Map<Player, GameResult> playerGameResult = blackjackGame.calculatePlayerGameResult();

        // then
        for (Entry<Player, GameResult> entry : playerGameResult.entrySet()) {
            assertThat(entry.getKey()).isEqualTo(miso);
            assertThat(entry.getValue()).isEqualTo(GameResult.LOSE);
        }
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (무)")
    void test10() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));

        // when
        Map<Player, GameResult> playerGameResult = blackjackGame.calculatePlayerGameResult();

        // then
        for (Entry<Player, GameResult> entry : playerGameResult.entrySet()) {
            assertThat(entry.getKey()).isEqualTo(miso);
            assertThat(entry.getValue()).isEqualTo(GameResult.DRAW);
        }
    }

    @Test
    @DisplayName("Dealer 게임 결과를 반환한다. (승)")
    void test11() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.JACK));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.ACE));
        Map<Player, GameResult> playerGameResult = blackjackGame.calculatePlayerGameResult();

        // when
        Map<GameResult, Integer> dealerGameResult = blackjackGame.calculateDealerGameResult(playerGameResult);

        // then
        assertThat(dealerGameResult.get(GameResult.WIN)).isEqualTo(1);
        assertThat(dealerGameResult.get(GameResult.LOSE)).isEqualTo(0);
        assertThat(dealerGameResult.get(GameResult.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("Dealer 게임 결과를 반환한다. (패)")
    void test12() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.ACE));
        Map<Player, GameResult> playerGameResult = blackjackGame.calculatePlayerGameResult();

        // when
        Map<GameResult, Integer> dealerGameResult = blackjackGame.calculateDealerGameResult(playerGameResult);

        // then
        assertThat(dealerGameResult.get(GameResult.WIN)).isEqualTo(0);
        assertThat(dealerGameResult.get(GameResult.LOSE)).isEqualTo(1);
        assertThat(dealerGameResult.get(GameResult.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("Players 게임 결과를 반환한다. (무)")
    void test13() {
        // given
        List<String> playerNames = List.of("미소");
        BlackjackGame blackjackGame = new BlackjackGame(playerNames);
        Player miso = blackjackGame.getPlayers().getPlayers().getFirst();
        miso.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));
        Dealer dealer = blackjackGame.getDealer();
        dealer.receiveCard(new Card(CardType.SPADE, CardNumber.KING));
        dealer.receiveCard(new Card(CardType.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardType.DIAMOND, CardNumber.SEVEN));
        Map<Player, GameResult> playerGameResult = blackjackGame.calculatePlayerGameResult();

        // when
        Map<GameResult, Integer> dealerGameResult = blackjackGame.calculateDealerGameResult(playerGameResult);

        // then
        assertThat(dealerGameResult.get(GameResult.WIN)).isEqualTo(0);
        assertThat(dealerGameResult.get(GameResult.LOSE)).isEqualTo(0);
        assertThat(dealerGameResult.get(GameResult.DRAW)).isEqualTo(1);
    }
}
