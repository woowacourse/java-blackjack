package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.MatchResult;

import java.util.*;

public class Players {

    private final Queue<Player> players;

    public Players(List<Player> players) {
        this.players = new LinkedList<>(players);
    }

    public boolean isAllFinished() {
        return this.players
                .stream()
                .allMatch(Player::isFinished);
    }

    public boolean isPresentPlayerFinished() {
        return findPresentPlayer().isFinished();
    }

    public void drawCardPresentPlayer(Card card) {
        players.peek().drawCard(card);
    }

    public void makePresentPlayerStay() {
        players.peek().stay();
    }

    public void passToNextPlayer() {
        players.offer(players.poll());
    }

    public Player findPresentPlayer() {
        return players.peek();
    }

    public boolean isAllBlackJack() {
        return players.stream()
                .allMatch(Player::isBlackJack);
    }

    public Map<Player, MatchResult> match(Dealer dealer) {
        Map<Player, MatchResult> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            gameResult.put(player, MatchResult.judge(player, dealer));
        }
        return gameResult;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
