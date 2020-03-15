package blackjack.domain.participants;

import blackjack.domain.card.Deck;
import blackjack.exceptions.InvalidParticipantsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants implements Iterable<Participant> {
    public static final int FIRST_CARDS_COUNT = 2;

    private final List<Participant> participants;

    public Participants(final Dealer dealer, final List<Player> players) {
        this.participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
    }

    public void initialDraw(Deck deck) {
        for (int i = 0; i < FIRST_CARDS_COUNT; i++) {
            participants.forEach(participant -> participant.draw(deck.pop()));
        }
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(Participant::isDealer)
                .findFirst()
                .orElseThrow(() ->
                        new InvalidParticipantsException("딜러가 없는 게임은 무효입니다.")
                );
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .map(participant -> (Player) participant)
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public Iterator<Participant> iterator() {
        return participants.iterator();
    }
}
