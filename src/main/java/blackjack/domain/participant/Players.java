package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.MatchResult;

import java.util.*;

public class Players {

    private final Queue<Player> players;

    public Players(List<Player> players) {
        this.players = new LinkedList<>(players);
    }

    public boolean isAllPlayerFinished() {
        return this.players
                .stream()
                .allMatch(Player::isFinished);
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

    public Map<Player, MatchResult> match(Dealer dealer) {
        Map<Player, MatchResult> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            gameResult.put(player, player.match(dealer));
        }
        return gameResult;
    }

    public Player getPresentPlayer() {
        return players.peek();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
