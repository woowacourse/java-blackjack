package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final String SPACE = " ";
    private static final String BLANK = "";
    private static final String DELIMITER = ",";
    private static final String OVER_MAXIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE =
        String.format("참여 인원이 너무 많습니다. %d명 이내로만 가능합니다.", MAXIMUM_PLAYER_COUNT);
    private static final String UNDER_MINIMUM_PLAYER_COUNT_EXCEPTION_MESSAGE =
        String.format("참여 인원은 %d명 이상이어야 합니다.", MINIMUM_PLAYER_COUNT);
    private static final String NOT_DEALER_EXCEPTION_MESSAGE = "딜러가 없습니다.";

    private final List<User> participants;

    public Participants(String playersName) {
        List<String> playerNames = createPlayerNames(playersName);
        List<User> participants = new ArrayList<>();
        participants.add(new Dealer());
        participants.addAll(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
        this.participants = Collections.unmodifiableList(participants);
    }

    private List<String> createPlayerNames(String playersName) {
        List<String> playerNames = Arrays
            .asList(playersName.replace(SPACE, Participants.BLANK).split(DELIMITER));
        validOverMinimumCount(playerNames);
        validUnderMaximumCount(playerNames);
        return playerNames;
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

    public Dealer getDealer() {
        return participants.stream()
            .filter(participants -> participants instanceof Dealer)
            .map(dealer -> (Dealer) dealer)
            .findFirst()
            .orElseThrow(() -> new IllegalAccessError(NOT_DEALER_EXCEPTION_MESSAGE));
    }

    public List<Player> getPlayers() {
        return participants.stream()
            .filter(participants -> participants instanceof Player)
            .map(player -> (Player) player)
            .collect(Collectors.toList());
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
