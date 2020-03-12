package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants implements Iterable<Participant> {
    public static final int FIRST_CARDS_COUNT = 2;

    private final List<Participant> participants;

    public Participants(final Dealer dealer, final String names) {
        this.participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(Arrays.stream(names
            .split(","))
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    // 테스트용
    Participants(final Dealer dealer, final Player... players) {
        this.participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(Arrays.asList(players));
    }

    public void initialDraw(Deck deck) {
        for (int i = 0; i < FIRST_CARDS_COUNT; i++) {
            participants.forEach(participant -> participant.draw(deck));
        }
    }

    public String getNames() {
        return participants.stream()
            .map(Participant::getName)
            .collect(Collectors.joining(", "));
    }

    @Override
    public Iterator<Participant> iterator() {
        return participants.iterator();
    }
}
