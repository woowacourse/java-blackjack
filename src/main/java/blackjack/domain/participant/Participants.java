package blackjack.domain.participant;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Participants {
    private static final String EMPTY_ERROR_MESSAGE = "참가자들이 존재하지 않습니다.";
    private static final String PLAYERS_COUNT_LIMIT_MASSAGE = "참여자는 8명 이하여야 합니다.";
    private static final int PLAYERS_COUNT_LIMIT = 8;
    private static final int DEALER_COEFFICIENT = -1;
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, Map<String, Integer> players) {
        validate(players.keySet());
        this.dealer = dealer;
        this.players = makePlayer(players);
    }

    public int getDealerRevenue() {
        return DEALER_COEFFICIENT * players.stream()
                .map(Player::getRevenue)
                .reduce(0, Integer::sum);
    }

    private void validate(Set<String> players) {
        validateNonPlayer(players);
        validatePlayerLimit(players);
    }

    private void validateNonPlayer(Set<String> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validatePlayerLimit(Set<String> players) {
        if (players.size() > PLAYERS_COUNT_LIMIT) {
            throw new IllegalArgumentException(PLAYERS_COUNT_LIMIT_MASSAGE);
        }
    }

    private List<Player> makePlayer(Map<String, Integer> players) {
        return players.entrySet()
                .stream()
                .map(entry -> new Player(new Name(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public void openDealerOneCard() {
        this.dealer.reverseAllExceptOne();
    }

    public void openDealerAllCards() {
        this.dealer.openAllCard();
    }

}
