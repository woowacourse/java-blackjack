package domain.fixture;

import domain.area.CardArea;
import domain.player.Name;
import domain.player.Participant;

public class ParticipantFixture {

    public static Participant 말랑(final CardArea cardArea) {
        return new Participant(Name.of("말랑"), cardArea);
    }

    public static Participant 코다(final CardArea cardArea) {
        return new Participant(Name.of("코다"), cardArea);
    }
}
