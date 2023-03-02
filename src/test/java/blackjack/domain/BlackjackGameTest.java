package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.util.CardPickerGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("처음 딜러와 플레이어의 카드를 2장 나눠준다.")
    void firstAllHit() {
        //given
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(0);
        Participants participants = Participants.generate(List.of("pobi", "ako"));
        BlackjackGame game = new BlackjackGame(participants, Cards.generator());

        //when
        game.settingGame(testCardPickerGenerator);

        //then
        for (Participant participant : participants.getParticipants()) {
            assertThat(participant.getReceivedCards().size()).isEqualTo(2);
        }
    }

    class TestCardPickerGenerator implements CardPickerGenerator {

        int randomIndex;

        TestCardPickerGenerator(int randomIndex) {
            this.randomIndex = randomIndex;
        }

        @Override
        public int generator(final int number) {
            return randomIndex;
        }
    }

}