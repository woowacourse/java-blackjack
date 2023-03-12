package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Cards;
import blackjack.domain.gameresult.ResultReader;
import blackjack.domain.betting.Revenue;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.gameresult.WinningResult;
import blackjack.util.CardPickerGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("처음 딜러와 플레이어의 카드를 2장 나눠준다.")
    void firstAllHit() {
        //given
        List<Integer> testData = settingTestData();
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(testData);
        Participants participants = Participants.generate(List.of("pobi", "ako"), List.of("1000", "1000"));
        ResultReader resultReader = new ResultReader();
        BlackjackGame game = new BlackjackGame(participants, Cards.generator(testCardPickerGenerator), resultReader);

        //when
        game.settingGame();

        //then
        for (Participant participant : participants.getParticipants()) {
            assertThat(participant.getHand().getReceivedCards().size()).isEqualTo(2);
        }
    }

    /**
     * dealer : A(11) 6(6) => 17점 pobi : 2(2) 3(3) => 5점 ako : 10(10) 10(10) => 20점
     */
    @Test
    @DisplayName("딜러가 17점일 때 포비(5점),아코(20점)의 결과는 각각 LOSE, WIN")
    void playerResultTest() {
        //given
        List<Integer> testData = settingTestData();
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(testData);
        Participants participants = Participants.generate(List.of("pobi", "ako"), List.of("1000", "1000"));
        ResultReader resultReader = new ResultReader();
        BlackjackGame game = new BlackjackGame(participants, Cards.generator(testCardPickerGenerator), resultReader);
        game.settingGame();

        //when
        Map<Player, WinningResult> result = game.generateBlackjackResult();

        //then

        for (Player player : result.keySet()) {
            String playername = player.getParticipantName().getName();
            if (playername.equals("pobi")) {
                assertThat(result.get(player)).isEqualTo(WinningResult.LOSE);
            }
            if (playername.equals("ako")) {
                assertThat(result.get(player)).isEqualTo(WinningResult.WIN);
            }
        }
    }

    @Test
    @DisplayName("포비와 아코 모두 배팅 금액이 1000원일 때 포비가 5점이고 아코가 20점일 때 딜러가 17점 이면 "
        + "포비의 수익은 -1000원이고 아코의 수익은 1000원이다. 또한 딜러의 수익은 0원이다")
    void dealerResultTest() {
        //given
        List<Integer> testData = settingTestData();
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(testData);
        List<String> playerNames = List.of("pobi", "ako");
        List<String> bettingMoneys = List.of("1000", "1000");
        Participants participants = Participants.generate(playerNames, bettingMoneys);
        ResultReader resultReader = new ResultReader();
        BlackjackGame game = new BlackjackGame(participants, Cards.generator(testCardPickerGenerator), resultReader);
        game.settingGame();

        //when
        Map<Player, WinningResult> playerWinningResult = game.generateBlackjackResult();
        Map<Player, Revenue> playerRevenueResult = game.generatePlayersRevenue(playerWinningResult);
        int dealerRevenue = game.generateDealerRevenue(playerRevenueResult);

        //then
        for (Player player : playerRevenueResult.keySet()) {
            String playername = player.getParticipantName().getName();
            if (playername.equals(playerNames.get(0))) {
                assertThat(playerRevenueResult.get(player).getRevenue())
                    .isEqualTo(Integer.parseInt(bettingMoneys.get(0)) * -1);
            }
            if (playername.equals(playerNames.get(1))) {
                assertThat(playerRevenueResult.get(player).getRevenue())
                    .isEqualTo(Integer.parseInt(bettingMoneys.get(1)));
            }
        }
        assertThat(dealerRevenue).isEqualTo(0);
    }

    private static List<Integer> settingTestData() {
        List<Integer> testData = new ArrayList<>();
        testData.add(1);
        testData.add(21);
        testData.add(4);
        testData.add(10);
        testData.add(33);
        testData.add(34);
        return testData;
    }

    class TestCardPickerGenerator implements CardPickerGenerator {

        List<Integer> randomIndex;

        TestCardPickerGenerator(List<Integer> randomIndex) {
            this.randomIndex = randomIndex;
        }

        @Override
        public int generator(final int number) {
            return randomIndex.remove(0);
        }
    }

}