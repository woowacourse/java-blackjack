package blackjack.domain.player;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.dto.OutComeResult;
import blackjack.dto.PlayerCards;
import blackjack.dto.PlayerScoreResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;
    private int currentTurnIndex;

    public Players(final List<Player> players) {
        Objects.requireNonNull(players, "players는 null로 생성할 수 없습니다.");
        this.players = new ArrayList<>(players);
        validateDuplicationPlayers(this.players);
    }

    private void validateDuplicationPlayers(final List<Player> players) {
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

    public List<PlayerCards> getPlayerFirstCards() {
        return players.stream()
                .map(PlayerCards::toPlayerFirstCards)
                .collect(Collectors.toUnmodifiableList());
    }

    public void turnToNextPlayer() {
        validateAllTurnEnd();
        currentTurnPlayer().endTurn();
        currentTurnIndex++;
    }

    private void validateAllTurnEnd() {
        if (isAllTurnEnd()) {
            throw new IllegalStateException("모든 턴이 종료되었습니다.");
        }
    }

    public boolean isAllTurnEnd() {
        return players.size() <= currentTurnIndex;
    }

    public PlayerCards drawCurrentPlayer(final Card card) {
        final Player currentPlayer = currentTurnPlayer();
        currentPlayer.draw(card);
        checkCanTurnNext(currentPlayer);
        return PlayerCards.toPlayerCards(currentPlayer);
    }

    private void checkCanTurnNext(final Player currentPlayer) {
        if (!currentPlayer.canDraw()) {
            currentTurnIndex++;
        }
    }

    private Player currentTurnPlayer() {
        validateAllTurnEnd();
        return players.get(currentTurnIndex);
    }

    public PlayerCards getCurrentTurnPlayerCards() {
        return PlayerCards.toPlayerCards(currentTurnPlayer());
    }

    public List<PlayerScoreResult> getPlayerScoreResults() {
        return players.stream()
                .map(PlayerScoreResult::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public OutComeResult outcomeResult(Player dealer) {
        return OutComeResult.from(calculateOutcomeResultWithDealer(dealer));
    }

    private Map<String, GameOutcome> calculateOutcomeResultWithDealer(final Player dealer) {
        return players.stream()
                .collect(Collectors.toUnmodifiableMap(Player::getName, player -> player.fightResult(dealer)));
    }
}
