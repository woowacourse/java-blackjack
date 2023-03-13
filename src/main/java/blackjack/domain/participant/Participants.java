package blackjack.domain.participant;

import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Participants {

    private static final String EMPTY_ERROR_MESSAGE = "참가자들이 존재하지 않습니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 이름이 존재합니다.";
    private static final String NOT_EXIST_DEALER_ERROR_MESSAGE = "딜러가 존재하지 않습니다.";

    private final List<Participant> participants = new ArrayList<>();

    private Participants(final Dealer dealer, final List<String> playerNames) {
        validate(playerNames);

        participants.add(dealer);
        participants.addAll(makePlayers(playerNames));
    }

    public static Participants of(final Dealer dealer, final List<String> playerNames) {
        return new Participants(dealer, playerNames);
    }

    private void validate(final List<String> playerNames) {
        validateEmptyNames(playerNames);
        validateDuplicateName(playerNames);
    }

    private void validateEmptyNames(final List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(final List<String> names) {
        if (isDuplicateName(names)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateName(final List<String> names) {
        final HashSet<String> uniqueNames = new HashSet<>(names);

        return uniqueNames.size() != names.size();
    }

    private List<Player> makePlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(name -> Player.of(Name.from(name), new ArrayList<>()))
                .collect(Collectors.toUnmodifiableList());
    }

    public void draw(final Deck deck, final int count) {
        for (int i = 0; i < count; i++) {
            participants.forEach(participant -> participant.drawCard(deck.draw()));
        }
    }

    public List<String> getNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public List<Participant> getAll() {
        return participants;
    }

    public Participant getDealer() {
        return participants.stream()
                .filter(Participant::isDealer)
                .map(Dealer.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_DEALER_ERROR_MESSAGE));
    }

    public List<Participant> getPlayers() {
        return participants.subList(1, participants.size());
    }
}
