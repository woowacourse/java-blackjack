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

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(List<String> playerNames) {
        validNotNull(playerNames);
        validOverMinimumCount(playerNames);
        validUnderMaximumCount(playerNames);
        this.dealer = new Dealer();
        this.players = Collections.unmodifiableList(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
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

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
            .map(Player::getName)
            .collect(Collectors.toList()));
    }

    public List<User> getParticipants() {
        List<User> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.stream()
            .map(player -> (User) player)
            .collect(Collectors.toList()));
        return participants;
    }

    public String getDealerName() {
        return dealer.getName();
    }
}
