package blackjack.domain.gambler;

import blackjack.domain.Name;
import blackjack.domain.Names;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Participants {

    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";
    private static final String OUT_OF_RANGE_EXCEPTION_MESSAGE =
            String.format("참여 인원은 %d명 이상, %d명 이하이어야 합니다.", MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT);

    private final User dealer;
    private final List<User> players;

    public Participants(Names playerNames) {
        validNotNull(playerNames);
        validCount(playerNames);
        this.dealer = new Dealer();
        this.players = Collections.unmodifiableList(playerNames.getNames().stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    private void validNotNull(Names playerNames) {
        if (Objects.isNull(playerNames)) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    private void validCount(Names playerNames) {
        if (playerNames.isSizeInRange(MINIMUM_PLAYER_COUNT, MAXIMUM_PLAYER_COUNT)) {
            return;
        }
        throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION_MESSAGE);
    }

    public User getDealer() {
        return dealer;
    }

    public List<User> getPlayers() {
        return players;
    }

    public List<User> getParticipants() {
        List<User> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return Collections.unmodifiableList(participants);
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
                .map(User::getName)
                .map(Name::toString)
                .collect(Collectors.toList()));
    }

    public Name getDealerName() {
        return dealer.getName();
    }
}
