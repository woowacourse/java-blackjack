package domain.player;

import domain.stake.Bet;

import java.util.*;

import static java.util.stream.Collectors.*;

public final class Players {

    private static final int MAX_PLAYERS_SIZE = 5;
    private static final String SIZE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레어이는 5명까지 참가 가능합니다.";
    private static final String DUPLICATE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어 이름은 중복일 수 없습니다.";

    private final List<Player> players;

    public Players(final List<String> playerNames) {
        validatePlayers(playerNames);
        this.players = createPlayers(playerNames);
    }

    private void validatePlayers(final List<String> playerNames) {
        validateSameName(playerNames);
        validateSize(playerNames);
    }

    private void validateSameName(final List<String> playerNames) {
        int removeDuplicateSize = new HashSet<>(playerNames).size();
        if (playerNames.size() != removeDuplicateSize) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void validateSize(final List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_GUIDE_MESSAGE);
        }
    }

    private List<Player> createPlayers(final List<String> players) {
        return players.stream()
                .map(name -> new Player(new PlayerName(name)))
                .collect(toList());
    }

    public Map<Player, Bet> calculateFinalResults(final Dealer dealer, final Map<Player, Bet> playerBets) {
        Map<Player, Status> statusResults = calculateResults(dealer);
        return calculateBets(dealer, statusResults, playerBets);
    }

    public Map<Player, Status> calculateResults(final Dealer dealer) {
        return players.stream()
                .collect(toMap(player -> player, player -> player.compareWithDealer(dealer)));
    }

    public Map<Player, Bet> calculateBets(Dealer dealer, final Map<Player, Status> statusResults, final Map<Player, Bet> playerBets) {
        Map<Player, Bet> prizeResult = new LinkedHashMap<>();
        for (Player player : players) {
            Status singleResult = statusResults.get(player);
            Bet singleBet = playerBets.get(player);
            prizeResult.merge(dealer, singleBet.getPrize(singleResult), Bet::add);
            prizeResult.merge(player, singleBet.getPrize(singleResult), Bet::add);
        }
        prizeResult.computeIfPresent(dealer, (ignored, stake) -> stake.negate());
        return prizeResult;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
