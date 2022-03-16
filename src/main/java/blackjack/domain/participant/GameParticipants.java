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
    private static final String DEALER_NOT_FOUND_EXCEPTION_MESSAGE = "해당 게임에 딜러가 존재하지 않습니다.";

    private final List<Participant> value;

    private GameParticipants(final List<Participant> value) {
        this.value = Collections.unmodifiableList(value);
    }

    public static GameParticipants of(final List<String> playerNames,
                                      final CardBundleSupplier strategy) {

        List<Participant> participants = new ArrayList<>();
        validatePlayerNames(playerNames);

        participants.add(Dealer.of(strategy.getInitialCardBundle()));
        participants.addAll(initializePlayers(playerNames, strategy));

        return new GameParticipants(participants);
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

    private static List<Participant> initializePlayers(final List<String> playerNames,
                                                       final CardBundleSupplier strategy) {
        return playerNames.stream()
                .map(name -> Player.of(name, strategy.getInitialCardBundle()))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Participant> getValue() {
        return value;
    }

    public Dealer getDealer() {
        return (Dealer) value.stream()
                .filter(participant -> participant instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(DEALER_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public List<Player> getPlayers() {
        return value.stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "GameParticipants{" + "value=" + value + '}';
    }
}
