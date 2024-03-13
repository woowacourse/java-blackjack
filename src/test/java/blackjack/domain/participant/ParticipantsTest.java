package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.testutil.CustomDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsTest {
    @DisplayName("올바르게 참가자들의 최초 오픈 Hand를 반환한다.")
    @Test
    void generateResultTest() {
        Participants participants = createParticipants();
        Iterator<String> expectedCardSignatures = List.of("5하트", "A하트", "J하트", "2하트", "2하트").iterator();
        InitialHands initialHands = participants.getParticipantsInitialHand();

        for (InitialHand initialHand : initialHands.getValues()) {
            initialHand.getCards()
                    .stream()
                    .map(Card::getSignature)
                    .forEach(cardSignature -> assertThat(cardSignature).isEqualTo(expectedCardSignatures.next()));
        }
    }

    private static Participants createParticipants() {
        List<Name> playerNames = List.of(new Name("감자"), new Name("고구마"));
        List<Number> cardNumbers = List.of(Number.ACE, Number.JACK, Number.TWO, Number.TWO, Number.FIVE, Number.SIX);
        HandGenerator handGenerator = new HandGenerator(new CustomDeck(cardNumbers));
        return new Participants(playerNames, handGenerator);
    }
}
