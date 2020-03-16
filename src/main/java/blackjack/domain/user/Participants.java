package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";
    private static final String OVER_MAXIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE =
        String.format("참여 인원이 너무 많습니다. %d명 이내로만 가능합니다.", MAXIMUM_PLAYER_COUNT);
    private static final String UNDER_MINIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE =
        String.format("참여 인원은 %d명 이상이어야 합니다.", MINIMUM_PLAYER_COUNT);
    private static final String NOT_DEALER_EXCEPTION_MESSAGE = "딜러가 없습니다.";

    private final List<User> participants;

    public Participants(List<String> playerNames) {
        validNotNull(playerNames);
        validOverMinimumCount(playerNames);
        validUnderMaximumCount(playerNames);
        this.participants = getParticipants(playerNames);
    }

    private void validNotNull(List<String> playerNames) {
        if (playerNames == null) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    private void validOverMinimumCount(List<String> playerNames) {
        if (playerNames.size() < MINIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(UNDER_MINIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private void validUnderMaximumCount(List<String> playerNames) {
        if (playerNames.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(OVER_MAXIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private List<User> getParticipants(List<String> playerNames) {
        List<User> participants = new ArrayList<>();
        participants.add(new Dealer());
        participants.addAll(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return participants.stream()
            .filter(participants -> participants instanceof Dealer)
            .map(dealer -> (Dealer) dealer)
            .findFirst()
            .orElseThrow(() -> new IllegalAccessError(NOT_DEALER_EXCEPTION_MESSAGE));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(participants.stream()
            .filter(participants -> participants instanceof Player)
            .map(player -> (Player) player)
            .collect(Collectors.toList()));
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(getPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList()));
    }

    public List<User> getParticipants() {
        return participants;
    }

    public String getDealerName() {
        return getDealer().getName();
    }
}
