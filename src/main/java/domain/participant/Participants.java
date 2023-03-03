package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;
    private static final int DEALER_ORDER = 0;
    private static final int PARTICIPANT_START_ORDER = 1;

    private final List<Participant> participants;

    private Participants(final List<String> playerNames) {
        validateDuplicateNames(playerNames);
        validatePlayerCount(playerNames);
        this.participants = new ArrayList<>();
        initParticipants(playerNames);
    }

    private void initParticipants(final List<String> playerNames) {
        participants.add(Dealer.create());
        List<Player> players = makePlayers(playerNames);
        participants.addAll(players);
    }

    private List<Player> makePlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(Player::create)
                .collect(Collectors.toUnmodifiableList());
    }

    public static Participants create(final List<String> playerNames) {
        return new Participants(playerNames);
    }

    public void addCard(final int participantOrder, final Card card) {
        Participant participant = participants.get(participantOrder);
        participant.addCard(card);
    }

    private void validateDuplicateNames(final List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);
        if (playerNames.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerCount(final List<String> playerNames) {
        int playerCount = playerNames.size();
        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }

    public boolean canDealerGiveCard() {
        Dealer dealer = (Dealer) participants.get(DEALER_ORDER);
        return dealer.canGiveCard();
    }

    public Participant getDealer() {
        return participants.get(DEALER_ORDER);
    }

    public List<Participant> getPlayer() {
        return participants.subList(PARTICIPANT_START_ORDER, participants.size());
    }

    public int size() {
        return participants.size();
    }
}
