package blackjack.domain.player;

import blackjack.domain.card.Cards;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants of(Cards cards, List<Information> information) {
        List<Participant> participants = information.stream()
            .map(info -> Participant.of(info.name(), info.batMoney(), cards.next(), cards.next()))
            .collect(Collectors.toList());

        return new Participants(participants);
    }

    public static Participants of(Cards cards, Information... information) {
        return of(cards, Arrays.asList(information));
    }

    public List<Participant> participants() {
        return Collections.unmodifiableList(participants);
    }

    public boolean anyoneBlackjack() {
        return participants.stream()
            .anyMatch(Player::isBlackJack);
    }

    public int totalBatMoney() {
        return participants.stream()
            .mapToInt(Participant::batMoney)
            .sum();
    }
}
