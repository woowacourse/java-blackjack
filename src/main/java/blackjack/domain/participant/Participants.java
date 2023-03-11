package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MAXIMUM_PLAYER_COUNT = 5;

    private final List<Participant> participants;

    public Participants(final Dealer dealer, final List<Player> players) {
        validate(players);

        final List<Participant> recruits = new ArrayList<>();
        recruits.add(dealer);
        recruits.addAll(players);

        this.participants = recruits;
    }

    private void validate(final List<Player> players) {
        validateCount(players);
        validateDuplicate(players);
    }

    private void validateCount(final List<Player> players) {
        if (players.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException("플레이어는 " + MAXIMUM_PLAYER_COUNT + "명을 초과할 수 없습니다");
        }
    }

    private void validateDuplicate(final List<Player> players) {
        final HashSet<Player> uniquePlayers = new HashSet<>(players);

        if (players.size() != uniquePlayers.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void drawCard(final Deck deck, final int count) {
        for (int i = 0; i < count; i++) {
            participants.forEach(participant -> participant.drawCard(deck.draw()));
        }
    }

    public Dealer getDealer() {
        return participants.stream()
                .filter(Participant::isDealer)
                .map(Dealer.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러는 존재해야 합니다."));
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .map(Player.class::cast)
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
