package blackjack.domain.paticipant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;
    private int currentPlayerTurnIndex;

    public Players(final List<Player> players) {
        Objects.requireNonNull(players, "players는 null이 들어올 수 없습니다.");
        this.players = new ArrayList<>(players);
        checkPlayersSize(this.players);
        checkDuplicationPlayers(this.players);
    }

    public Players(final List<String> names, final Function<String, Integer> betMoney, final CardDeck cardDeck) {
        this(names.stream()
                .map(name -> Player.createPlayer(name, betMoney.apply(name), cardDeck))
                .collect(Collectors.toList()));
    }

    private void checkPlayersSize(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 0명이 될 수 없습니다.");
        }
    }

    private void checkDuplicationPlayers(final List<Player> players) {
        if (calculateDistinctCount(players) != players.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    private int calculateDistinctCount(final List<Player> players) {
        return (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();
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

    public double dealerProfit(final Dealer dealer) {
        return - players.stream()
                .mapToDouble(player -> player.profit(dealer))
                .sum();
    }

    public List<Player> players() {
        return List.copyOf(players);
    }

    public String currentTurnPlayerName() {
        return currentTurnPlayer().getName();
    }
}
