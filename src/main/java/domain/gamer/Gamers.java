package domain.gamer;

import domain.cards.gamercards.DealerCards;
import domain.cards.gamercards.PlayerCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Gamers {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;
    private static final int DEALER_INDEX = 0;
    private static final int PLAYER_FIRST_INDEX = 1;
    private static final String DEALER_NAME = "딜러";

    private final List<Gamer> gamers;

    public Gamers(List<String> playersNames) {
        validate(playersNames);
        gamers = new ArrayList<>();
        gamers.add(new Dealer(new DealerCards(new ArrayList<>())));
        for (String playerName : playersNames) {
            PlayerCards emptyHand = new PlayerCards(new ArrayList<>());
            gamers.add(new Player(playerName, emptyHand));
        }
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

    public Dealer callDealer() {
        return (Dealer) gamers.get(DEALER_INDEX);
    }

    public List<Player> callPlayers() {
        return IntStream.range(PLAYER_FIRST_INDEX, gamers.size())
                .mapToObj(index -> (Player) gamers.get(index))
                .collect(Collectors.toList());
    }

    public List<Gamer> getGamers() {
        return Collections.unmodifiableList(gamers);
    }
}
