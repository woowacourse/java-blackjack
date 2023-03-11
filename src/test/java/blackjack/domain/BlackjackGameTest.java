package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.util.CardPickerGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("처음 딜러와 플레이어의 카드를 2장 나눠준다.")
    void firstAllHit() {
        //given
        List<Integer> testData = settingTestData();
        BlackjackGame game = BlackjackGame.of(
                List.of("pobi", "ako"),
                List.of("1000","2000"),
                Deck.create(new TestCardPickerGenerator(testData))
        );
        //when
        for (Participant participant : game.getParticipants()) {
            game.getTwoHitCards(participant);
        }
        //then
        for (Participant participant : game.getParticipants()) {
            assertThat(participant.getCardsCount()).isEqualTo(2);
        }
    }

    /**
     * dealer : TEN(10) 6(6) => 17점
     * pobi : 2(2) 3(3) => 5점
     * ako : 10(10) 10(10) => 20점
     */
    @Test
    @DisplayName("딜러가 17점일 때 포비(5점),아코(20점)의 결과는 각각 LOSE,WIN")
    void playerResultTest() {
        //given
        List<Integer> testData = settingTestData();
        BlackjackGame game = BlackjackGame.of(
                List.of("pobi", "ako"),
                List.of("1000","2000"),
                Deck.create(new TestCardPickerGenerator(testData))
        );
        for (Participant participant : game.getParticipants()) {
            game.getTwoHitCards(participant);
        }

        //when
        BlackjackGameResult blackjackGameResult= game.generatePlayersResult(new BlackJackReferee());
        //then
        Set<Player> players = blackjackGameResult.getGameResult().keySet();
        for(Participant participant : players) {
            String playerName = participant.getParticipantName().getName();
            if (playerName.equals("pobi")){
                assertThat(blackjackGameResult.getGameResult().get(participant)).isEqualTo(WinningResult.LOSE);
            }
            if (participant.equals("ako")) {
                assertThat(blackjackGameResult.getGameResult().get(participant)).isEqualTo(WinningResult.WIN);
            }
        }
    }

    @Test
    @DisplayName("포비가 5점이고 아코가 20점일 때 딜러가 17점 이면 결과는 각각 LOSE,WIN")
    void dealerResultTest() {
        //given
        List<Integer> testData = settingTestData();
        BlackjackGame game = BlackjackGame.of(
                List.of("pobi", "ako"),
                List.of("1000","2000"),
                Deck.create(new TestCardPickerGenerator(testData))
        );
        for (Participant participant : game.getParticipants()) {
            game.getTwoHitCards(participant);
        }

        //when
        BlackjackGameResult blackjackGameResult= game.generatePlayersResult(new BlackJackReferee());

        //then
        Set<Player> players = blackjackGameResult.getGameResult().keySet();
        for(Participant participant : players) {
            String playername = participant.getParticipantName().getName();
            if (playername.equals("pobi")){
                assertThat(blackjackGameResult.getGameResult().get(participant)).isEqualTo(WinningResult.LOSE);
            }
            if (playername.equals("ako")) {
                assertThat(blackjackGameResult.getGameResult().get(participant)).isEqualTo(WinningResult.WIN);
            }
        }
    }

    private static List<Integer> settingTestData() {
        List<Integer> testData = new ArrayList<>();
        testData.add(0);
        testData.add(0);
        testData.add(0);
        testData.add(0);
        testData.add(0);
        testData.add(4);
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
