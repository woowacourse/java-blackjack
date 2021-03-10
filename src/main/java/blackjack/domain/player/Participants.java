package blackjack.domain.player;

import blackjack.domain.card.Cards;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants of(Cards cards, Information... information) {
        List<Participant> participants = Arrays.stream(information)
            .map(info -> Participant.of(info.name(), info.batMoney(), cards.next(), cards.next()))
            .collect(Collectors.toList());

        return new Participants(participants);
    }

    public void setBatMoney(Participant participant, int batMoney) {

    }
}
