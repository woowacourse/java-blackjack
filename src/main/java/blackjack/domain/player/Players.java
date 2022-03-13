package blackjack.domain.player;

import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private static final int MAX_GAMER = 8;
    private static final String NULL_PARTICIPANT_ERROR_MESSAGE = "참가자로 null이 입력되었습니다..";
    private static final String SAME_DEALER_NAME_ERROR_MESSAGE = "플레이어의 이름가 딜러면 안된다.";
    private static final String PLAYER_SIZE_ERROR_MESSAGE = "플레이어 수는 1명 이상 " + MAX_GAMER + "명 이하여야 합니다.";
    private static final String DUPLICATE_NAME_ERROR_MESSAGE = "플레이어의 이름이 중복되었습니다.";
    private static final String NO_SUCH_DEALER_ERROR_MESSAGE = "딜러가 없습니다.";

    private final List<Player> players;

    public Players(List<Player> participants) {
        Dealer dealer = new Dealer();
        validateNull(participants);
        validateHasDealerName(dealer, participants);
        validateSize(participants);
        validateDuplicateName(participants);

        List<Player> players = new ArrayList<>(List.of(dealer));
        players.addAll(participants);
        this.players = players;
    }

    private void validateNull(List<Player> participants) {
        if (participants == null) {
            throw new NullPointerException(NULL_PARTICIPANT_ERROR_MESSAGE);
        }
    }

    private void validateHasDealerName(Dealer dealer, List<Player> participants) {
        boolean hasSameDealerName = participants.stream()
                .anyMatch(participant -> participant.getName().equals(dealer.getName()));

        if (hasSameDealerName) {
            throw new IllegalArgumentException(SAME_DEALER_NAME_ERROR_MESSAGE);
        }
    }

    private void validateSize(List<Player> players) {
        if (players.isEmpty() || MAX_GAMER < players.size()) {
            throw new IllegalArgumentException(PLAYER_SIZE_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(List<Player> players) {
        int distinctNameCount = (int) players.stream()
                .map(gamer -> gamer.getName().get())
                .distinct()
                .count();

        if (players.size() != distinctNameCount) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    public void dealCards(Deck deck) {
        for (Player player : players) {
            player.hit(deck.pick());
            player.hit(deck.pick());
        }
    }

    public List<Player> get() {
        return players;
    }

    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(NO_SUCH_DEALER_ERROR_MESSAGE));
    }

    public List<Player> getParticipants() {
        return players.stream()
                .filter(player -> !player.isDealer())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Players)) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
