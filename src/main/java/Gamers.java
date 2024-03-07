import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gamers {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;
    private static final String DEALER_NAME = "딜러";

    private final List<Gamer> gamers;

    public Gamers(List<String> playersNames) {
        validate(playersNames);
        gamers = new ArrayList<>();

        for (String playerName : playersNames) {
            PlayerCards emptyHand = new PlayerCards(new ArrayList<>());
            gamers.add(new Player(playerName, emptyHand));
        }
        gamers.add(new Dealer(new DealerCards(new ArrayList<>())));
    }

    private void validate(List<String> playersNames) {
        validateSize(playersNames.size());
        validateDuplicatedNames(playersNames);
        validateNotDealerName(playersNames);
    }

    private void validateSize(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 최소 " + MIN_SIZE + "명에서 최대 " + MAX_SIZE + "명까지 참여할 수 있습니다.");
        }
    }

    private void validateDuplicatedNames(List<String> players) {
        long nonDuplicatedCount = players.stream()
                .distinct()
                .count();
        if (nonDuplicatedCount != players.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 간 중복된 이름을 가질 수 없습니다.");
        }
    }

    private void validateNotDealerName(List<String> playersNames) {
        if (playersNames.contains(DEALER_NAME)) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 \"딜러\"가 될 수 없습니다.");
        }
    }

    public List<Gamer> getGamers() {
        return Collections.unmodifiableList(gamers);
    }
}
