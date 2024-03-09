package blackjack.domain.participant;

import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.domain.result.BlackjackResult;
import blackjack.domain.result.HandResult;
import blackjack.domain.result.Referee;
import blackjack.testutil.CustomDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsTest {
    @DisplayName("올바르게 블랙잭 결과를 생성한다.")
    @Test
    void generateResultTest() {
        List<Name> playerNames = List.of(new Name("감자"), new Name("고구마"));
        List<Number> cardNumbers = List.of(Number.ACE, Number.JACK, Number.TWO, Number.TWO, Number.FIVE, Number.SIX);
        HandGenerator handGenerator = new HandGenerator(new CustomDeck(cardNumbers));
        Participants participants = new Participants(playerNames, handGenerator);
        BlackjackResult blackjackResult = participants.generateResult(Referee.getInstance());

        assertThat(blackjackResult.getPlayersResult()).containsExactly(
                Map.entry(participants.getPlayers().getValues().get(0), HandResult.WIN),
                Map.entry(participants.getPlayers().getValues().get(1), HandResult.LOSE));
    }
}
