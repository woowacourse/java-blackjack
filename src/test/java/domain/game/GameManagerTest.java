package domain.game;

import domain.participant.Participant;
import domain.participant.ParticipantInfo;
import domain.participant.ParticipantMoney;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static domain.helper.MockCardShufflerHelper.createBustShuffler;
import static domain.helper.MockCardShufflerHelper.createBustShufflerAfterDrawCard;
import static domain.helper.MockCardShufflerHelper.createDealerBlackJackShuffler;
import static domain.helper.MockCardShufflerHelper.createOneWinAndOneLoseShuffler;
import static domain.helper.ParticipantTestHelper.makeOneParticipantInfo;
import static domain.helper.ParticipantTestHelper.makeTwoParticipantInfo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameManagerTest {

    @Test
    @DisplayName("create()는 덱과 참가자 정보를 받으면 게임 관리자를 생성한다.")
    void create_givenDeckAndParticipants_thenSuccess() {
        // given
        final Map<Participant, ParticipantMoney> participantInfo =
                makeTwoParticipantInfo(Player.create("pobi"), Player.create("crong"), Participant.createDealer());

        // when, then
        final GameManager gameManager = assertDoesNotThrow(() -> GameManager.create((card) -> card, participantInfo));

        assertThat(gameManager)
                .isExactlyInstanceOf(GameManager.class);
    }

    @Test
    @DisplayName("handFirstCards()는 호출하면, 플레이어에게 카드를 건네준다.")
    void handFirstCards_whenCall_thenSuccess() {
        // given
        final Player pobi = Player.create("pobi");
        final Player crong = Player.create("crong");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> participantInfo =
                makeTwoParticipantInfo(pobi, crong, dealer);
        final GameManager gameManager = GameManager.create((card) -> card, participantInfo);
        final List<Participant> participants = List.of(pobi, crong, dealer);

        // when
        gameManager.handFirstCards();

        // then
        for (Participant participant : participants) {
            assertThat(participant.getHand().size())
                    .isSameAs(2);
        }
    }

    @Test
    @DisplayName("judgeFirstBettingResult()는 호출하면, 첫 턴의 결과를 판단하여 플레이어의 돈을 딜러에게 혹은 플레이어에게 보너스를 준다.")
    void judgeFirstBettingResult_whenCall_thenJudgeBettingMoney() {
        // given
        final Map<Participant, ParticipantMoney> participantInfo =
                makeTwoParticipantInfo(Player.create("pobi"), Player.create("crong"), Participant.createDealer());
        final GameManager gameManager = GameManager.create(createDealerBlackJackShuffler(), participantInfo);
        gameManager.handFirstCards();
        final ParticipantInfo expected = makePlayerBettingLoseInfo();

        // when
        gameManager.judgeFirstBettingResult();

        // then
        ParticipantInfo actual = gameManager.getParticipantInfo();
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("handCard()는 호출하면, 참가자에게 카드 1장을 건네준다.")
    void handCard_whenCall_thenGiveCardToParticipant() {
        // given
        final Player targetPlayer = Player.create("pobi");
        final GameManager gameManager = GameManager.create(
                createBustShufflerAfterDrawCard(),
                makeOneParticipantInfo(targetPlayer, Participant.createDealer()));

        // when
        gameManager.handCard(targetPlayer);

        // then
        assertThat(targetPlayer.getHand().size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("losePlayerMoney()는 호출하면, 참가자의 돈을 딜러에게 넘긴다.")
    void losePlayerMoney_whenCall_thenGivePlayerMoneyToDealer() {
        // given
        final Player targetPlayer = Player.create("pobi");
        final Participant dealer = Participant.createDealer();
        final GameManager gameManager = GameManager.create(
                createBustShufflerAfterDrawCard(),
                makeOneParticipantInfo(targetPlayer, dealer));

        // when
        gameManager.losePlayerMoney(targetPlayer);

        // then
        final Map<Participant, ParticipantMoney> allParticipantInfo =
                gameManager.getParticipantInfo().getParticipantInfo();

        assertThat(allParticipantInfo.get(targetPlayer))
                .isEqualTo(ParticipantMoney.create(-10000));

        assertThat(allParticipantInfo.get(dealer))
                .isEqualTo(ParticipantMoney.create(10000));
    }

    @Test
    @DisplayName("calculateProfit()는 딜러가 버스트면, 플레이어의 초기 베팅 금액을 돌려준다.")
    void calculateProfit_whenDealerBust_thenGiveBackBettingMoney() {
        // given
        final Player pobi = Player.create("pobi");
        final Player crong = Player.create("crong");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> initMoneyInfo = makeTwoParticipantInfo(pobi, crong, dealer);
        final GameManager gameManager = GameManager.create(createBustShuffler(), initMoneyInfo);

        // when
        gameManager.calculateProfit(initMoneyInfo);

        // then
        final Map<Participant, ParticipantMoney> allParticipantInfo = gameManager.getParticipantInfo().getParticipantInfo();
        assertThat(allParticipantInfo)
                .isEqualTo(initMoneyInfo);
    }

    @Test
    @DisplayName("calculateProfit()는 딜러와 플레이어의 승패를 판단하여 최종 수익을 계산한다.")
    void calculateProfit_givenInitMoneyInfo_thenCalculateFinalProfit() {
        // given
        final Player pobi = Player.create("pobi");
        final Player crong = Player.create("crong");
        final Participant dealer = Participant.createDealer();
        final Map<Participant, ParticipantMoney> initMoneyInfo = makeTwoParticipantInfo(pobi, crong, dealer);
        final GameManager gameManager = GameManager.create(createOneWinAndOneLoseShuffler(), initMoneyInfo);
        gameManager.handFirstCards();

        // when
        gameManager.calculateProfit(initMoneyInfo);

        // then
        assertThat(gameManager.getParticipantInfo())
                .isEqualTo(makeBettingInfo());
    }

    private ParticipantInfo makePlayerBettingLoseInfo() {
        final Map<Participant, ParticipantMoney> expected = new LinkedHashMap<>();
        expected.put(Participant.createDealer(), ParticipantMoney.create(30000));
        expected.put(Player.create("pobi"), ParticipantMoney.create(-10000));
        expected.put(Player.create("crong"), ParticipantMoney.create(-20000));
        return ParticipantInfo.create(expected);
    }

    private ParticipantInfo makeBettingInfo() {
        final Map<Participant, ParticipantMoney> expected = new LinkedHashMap<>();
        expected.put(Participant.createDealer(), ParticipantMoney.create(10000));
        expected.put(Player.create("pobi"), ParticipantMoney.create(10000));
        expected.put(Player.create("crong"), ParticipantMoney.create(-20000));
        return ParticipantInfo.create(expected);
    }
}
