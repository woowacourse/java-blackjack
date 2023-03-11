package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.participant.Participant;
import blackjack.util.CardPickerGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackGameResultTest {

    @Test
    @DisplayName("플래이어들의 승리를 딜러가 몇번 패배했는지 리턴")
    void getDealerLoseCount() {
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
        BlackjackGameResult blackjackGameResult = game.generatePlayersResult(new BlackJackReferee());

        //when
        int result = blackjackGameResult.getDealerLoseCount();

        //then

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("플래이어들의 패배를 딜러가 몇번 승리했는지 리턴")
    void getDealerWinCount() {
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
        BlackjackGameResult blackjackGameResult = game.generatePlayersResult(new BlackJackReferee());

        //when
        int result = blackjackGameResult.getDealerWinCount();

        //then
        assertThat(result).isEqualTo(1);
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
