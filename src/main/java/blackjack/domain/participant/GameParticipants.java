package blackjack.domain.participant;

import blackjack.strategy.CardBundleSupplier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GameParticipants {

    private static final String NO_PLAYER_EXCEPTION_MESSAGE = "플레이어가 없는 게임은 존재할 수 없습니다.";
    private static final String DUPLICATE_PLAYER_NAMES_EXCEPTION_MESSAGE = "플레이어명은 중복될 수 없습니다.";

    private final Participant dealer;
    private final List<Participant> players;

    private GameParticipants(final Participant dealer, final List<Participant> players) {
        this.dealer = dealer;
        this.players = Collections.unmodifiableList(players);
    }

    public static GameParticipants of(final List<String> playerNames, final CardBundleSupplier strategy) {
        final Participant dealer = Dealer.of(strategy.getInitialCardBundle());
        final List<Participant> players = initializePlayers(playerNames, strategy);

        return new GameParticipants(dealer, players);
    }

    private static List<Participant> initializePlayers(final List<String> playerNames,
                                                       final CardBundleSupplier strategy) {
        validatePlayerNames(playerNames);
        return playerNames.stream()
                .map(name -> Player.of(name, strategy.getInitialCardBundle()))
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validatePlayerNames(final List<String> playerNames) {
        validatePlayerExists(playerNames);
        validateNoDuplicateNames(playerNames);
    }

    private static void validatePlayerExists(final List<String> playerNames) {
        if (playerNames.size() == 0) {
            throw new IllegalArgumentException(NO_PLAYER_EXCEPTION_MESSAGE);
        }
    }

    private static void validateNoDuplicateNames(final List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException(DUPLICATE_PLAYER_NAMES_EXCEPTION_MESSAGE);
        }
    }

    public List<Participant> getValue() {
        List<Participant> participants = new ArrayList<>();

        participants.add(dealer);
        participants.addAll(players);

        return Collections.unmodifiableList(participants);
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "GameParticipants{" +
                "dealer=" + dealer +
                ", players=" + players +
                '}';
    }
}
