package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {

    private static final int MIN_PLAYERS_SIZE = 2;
    private static final int MAX_PLAYERS_SIZE = 8;

    private final List<Player> players;
    private int currentPlayerTurnIndex;

    public Players(final List<Player> players) {
        Objects.requireNonNull(players, "players는 null이 들어올 수 없습니다.");
        this.players = new ArrayList<>(players);
        checkPlayersSize(this.players);
    }

    private void checkPlayersSize(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 0명이 될 수 없습니다.");
        }
    }

    public static Players createPlayers(final List<String> names, final Function<String, Integer> betMoney,
                                        final CardDeck cardDeck) {
        checkDuplicationNames(names);
        checkPlayerNamesSize(names);
        return new Players(createPlayersByBettingAndDrawCards(toNames(names), betMoney, cardDeck));
    }

    private static void checkDuplicationNames(final List<String> names) {
        if (calculateDistinctCount(names) != names.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    private static int calculateDistinctCount(final List<String> names) {
        return (int) names.stream()
                .distinct()
                .count();
    }

    private static List<Name> toNames(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private static void checkPlayerNamesSize(final List<String> names) {
        if (names.size() < MIN_PLAYERS_SIZE || names.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException(String.format("플레이어는 %s명 이상 %s명 이하만 들어올 수 있습니다.",
                    MIN_PLAYERS_SIZE, MAX_PLAYERS_SIZE));
        }
    }

    private static List<Player> createPlayersByBettingAndDrawCards(final List<Name> names,
                                                                   final Function<String, Integer> betMoney,
                                                                   final CardDeck cardDeck) {
        return names.stream()
                .map(name -> new Player(name, betMoney.apply(name.getName()), cardDeck))
                .collect(Collectors.toList());
    }

    public Player hitCurrentTurnPlayer(final Card card) {
        final Player currentTurnPlayer = currentTurnPlayer();
        currentTurnPlayer.hit(card);
        checkCanTurnNextAndTurnNext(currentTurnPlayer);
        return currentTurnPlayer;
    }

    public Player currentTurnPlayer() {
        checkAllTurnEnd();
        return players.get(currentPlayerTurnIndex);
    }

    private void checkAllTurnEnd() {
        if (isAllTurnEnd()) {
            throw new IllegalStateException("모든 턴이 종료되었습니다.");
        }
    }

    public boolean isAllTurnEnd() {
        return players.size() <= currentPlayerTurnIndex;
    }

    private void checkCanTurnNextAndTurnNext(final Player currentTurnPlayer) {
        if (currentTurnPlayer.isFinished()) {
            currentPlayerTurnIndex++;
        }
    }

    public void stayCurrentTurnPlayer() {
        checkAllTurnEnd();
        currentTurnPlayer().stay();
        currentPlayerTurnIndex++;
    }

    public int dealerProfit(final Dealer dealer) {
        return calculateAllPlayersProfit(dealer) * -1;
    }

    private int calculateAllPlayersProfit(final Dealer dealer) {
        return players.stream()
                .mapToInt(player -> player.profit(dealer))
                .sum();
    }

    public List<Player> players() {
        return List.copyOf(players);
    }

    public String currentTurnPlayerName() {
        return currentTurnPlayer().getName();
    }
}
