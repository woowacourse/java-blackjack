package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.participant.Dealer;
import domain.participant.ParticipantMoney;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static domain.helper.MockCardShufflerHelper.createDealerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createDealerBustShufflerWithTwoPlayer;
import static domain.helper.MockCardShufflerHelper.createOneWinAndOneLoseShuffler;
import static domain.helper.ParticipantTestHelper.makeTwoParticipantInfo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameManagerTest {

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다.")
    void create_givenDeckAndParticipants_thenSuccess() {
        // given
        final Players players = Players.create(List.of("pobi", "crong"));
        final Participants participants = Participants.create(players);

        // when, then
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create((card) -> card, participants));

        assertThat(gameManager)
                .isExactlyInstanceOf(GameManager.class);
    }

    @Test
    @DisplayName("dealingFirstTurn()는 호출하면, 참가자들에게 카드를 건네준다.")
    void dealingFirstTurn_whenCall_thenSuccess() {
        // given
        final Players players = Players.create(List.of("pobi", "crong"));
        final Participants participants = Participants.create(players);
        final GameManager gameManager = GameManager.create((card) -> card, participants);

        // when
        gameManager.dealingFirstTurn();

        // then
        final Dealer dealer = participants.getDealer();
        assertThat(dealer.getHand().size())
                .isSameAs(2);

        for (Player player : participants.getPlayers()) {
            assertThat(player.getHand().size())
                    .isSameAs(2);
        }
    }

    @Test
    @DisplayName("judgeFirstBettingResult()는 호출하면, 첫 턴의 결과를 바탕으로 딜러나 플레이어에게 돈을 준다.")
    void judgeFirstBettingResult_whenCall_thenJudgeBettingMoney() {
        // given
        final Players players = Players.create(List.of("pobi", "crong"));
        final List<Player> allPlayers = players.getPlayers();

        final List<Integer> playerMoney = List.of(10000, 20000);
        for (int i = 0; i < allPlayers.size(); i++) {
            allPlayers.get(i).bet(ParticipantMoney.create(playerMoney.get(i)));
        }

        final Participants participants = Participants.create(players);
        final GameManager gameManager = GameManager.create(createDealerBlackJackShuffler(), participants);
        gameManager.dealingFirstTurn();

        // when
        gameManager.judgeFirstBettingResult();

        // then
        final Dealer dealer = participants.getDealer();
        assertThat(dealer.getMoney())
                .isEqualTo(30000);
        assertThat(players.getPlayers().get(0).getMoney())
                .isEqualTo(-10000);
        assertThat(players.getPlayers().get(1).getMoney())
                .isEqualTo(-20000);
    }

    @Test
    @DisplayName("getCard()는 호출하면, 카드 1장을 꺼내준다.")
    void getCard_whenCall_thenGiveCardToParticipant() {
        // given
        final Players players = Players.create(List.of("pobi"));
        final Participants participants = Participants.create(players);
        final GameManager gameManager = GameManager.create((card) -> card, participants);

        // when
        final Card actual = gameManager.getCard();

        // then
        assertThat(actual)
                .isEqualTo(Card.create(CardPattern.HEART, CardNumber.ACE));
    }

    @Test
    @DisplayName("calculateProfit()는 딜러가 버스트면, 플레이어의 초기 베팅 금액을 돌려준다.")
    void calculateProfit_whenDealerBust_thenGiveBackBettingMoney() {
        // given
        final Players players = Players.create(List.of("pobi", "crong"));
        final List<Player> allPlayers = players.getPlayers();

        final List<Integer> playerMoney = List.of(10000, 20000);
        IntStream.range(0, allPlayers.size())
                .forEach((index) ->
                        allPlayers.get(index).bet(ParticipantMoney.create(playerMoney.get(index))));

        final Participants participants = Participants.create(players);
        final GameManager gameManager = GameManager.create(createDealerBustShufflerWithTwoPlayer(), participants);

        final Dealer dealer = participants.getDealer();
        final Player player1 = allPlayers.get(0);
        final Player player2 = allPlayers.get(1);

        gameManager.dealingFirstTurn();
        dealer.addCard(gameManager.getCard());

        final Map<Player, ParticipantMoney> initMoneyInfo =
                makeTwoParticipantInfo(player1, player2);

        // when
        gameManager.calculateProfit(initMoneyInfo);

        // then
        assertThat(dealer.getMoney())
                .isEqualTo(-30000);
        assertThat(player1.getMoney())
                .isEqualTo(10000);
        assertThat(player2.getMoney())
                .isEqualTo(20000);
    }

    @Test
    @DisplayName("calculateProfit()는 딜러와 플레이어의 승패를 판단하여 최종 수익을 계산한다.")
    void calculateProfit_givenInitMoneyInfo_thenCalculateFinalProfit() {
        // given
        final Players players = Players.create(List.of("pobi", "crong"));
        final List<Player> allPlayers = players.getPlayers();

        final List<Integer> playerMoney = List.of(10000, 20000);
        IntStream.range(0, allPlayers.size())
                .forEach((index) ->
                        allPlayers.get(index).bet(ParticipantMoney.create(playerMoney.get(index))));

        final Participants participants = Participants.create(players);
        final GameManager gameManager = GameManager.create(createOneWinAndOneLoseShuffler(), participants);
        gameManager.dealingFirstTurn();

        final Dealer dealer = participants.getDealer();
        final Player player1 = allPlayers.get(0);
        final Player player2 = allPlayers.get(1);
        final Map<Player, ParticipantMoney> initMoneyInfo =
                makeTwoParticipantInfo(player1, player2);

        // when
        gameManager.calculateProfit(initMoneyInfo);

        // then
        assertThat(dealer.getMoney())
                .isEqualTo(10000);
        assertThat(player1.getMoney())
                .isEqualTo(10000);
        assertThat(player2.getMoney())
                .isEqualTo(-20000);
    }
}
