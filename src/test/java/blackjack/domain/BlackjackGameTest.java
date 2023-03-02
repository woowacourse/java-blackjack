package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.util.CardPickerGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("처음 딜러와 플레이어의 카드를 2장 나눠준다.")
    void firstAllHit() {
        //given
        List<Integer> testData = settingTestData();
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(testData);
        Participants participants = Participants.generate(List.of("pobi", "ako"));
        BlackjackGame game = new BlackjackGame(participants, Cards.generator());

        //when
        game.settingGame(testCardPickerGenerator);

        //then
        for (Participant participant : participants.getParticipants()) {
            assertThat(participant.getReceivedCards().size()).isEqualTo(2);
        }
    }

    /**
     * dealer : A(11) 6(6) => 17점
     * pobi : 2(2) 3(3) => 5점
     * ako : 10(10) 10(10) => 20점
     */
    @Test
    @DisplayName("딜러가 17점일 때 포비(5점),아코(20점)의 결과는 각각 LOSE,WIN")
    void playerResultTest() {
        //given
        List<Integer> testData = settingTestData();
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(testData);
        Participants participants = Participants.generate(List.of("pobi", "ako"));
        BlackjackGame game = new BlackjackGame(participants, Cards.generator());
        game.settingGame(testCardPickerGenerator);

        //when
        Map<Participant, WinningResult> result = game.generatePlayersResult();

        //then
        Set<Participant> players = result.keySet();
        for(Participant participant : players) {
            String playername = participant.getParticipantName().getName();
            if (playername.equals("pobi")){
                assertThat(result.get(participant)).isEqualTo(WinningResult.LOSE);
            }
            if (playername.equals("ako")) {
                assertThat(result.get(participant)).isEqualTo(WinningResult.WIN);
            }
        }
    }

    @Test
    @DisplayName("포비가 5점이고 아코가 20점일 때 딜러가 17점 이면 결과는 각각 WIN,LOSE")
    void dealerResultTest() {
        //given
        List<Integer> testData = settingTestData();
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(testData);
        Participants participants = Participants.generate(List.of("pobi", "ako"));
        BlackjackGame game = new BlackjackGame(participants, Cards.generator());
        game.settingGame(testCardPickerGenerator);

        //when
        List<WinningResult> result = game.generateDealerResult();

        //then
        assertThat(result.get(0)).isEqualTo(WinningResult.WIN);
        assertThat(result.get(1)).isEqualTo(WinningResult.LOSE);
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