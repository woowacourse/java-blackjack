package domain.participant;

import static java.util.stream.Collectors.toUnmodifiableMap;

import domain.GameOutcome;
import domain.card.Card;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validateNotEmptyPlayers(players);
        this.players = players;
    }

    private void validateNotEmptyPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public void receiveCard(Deck deck) {
        players.forEach(player -> player.receiveCard(deck.draw()));
    }

    public Player findCurrentDrawablePlayer() {
        return players.stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("카드를 받을 수 있는 플레이어가 없습니다."));
    }

    public boolean hasDrawablePlayer() {
        return players.stream()
                .anyMatch(Player::isDrawable);
    }

    public void handOutCardToCurrentPlayer(Card card) {
        findCurrentDrawablePlayer().receiveCard(card);
    }

    public void standCurrentPlayer() {
        findCurrentDrawablePlayer().stand();
    }

    public List<Player> values() {
        return new ArrayList<>(players);
    }

    public Map<String, Integer> calculateRevenues(Dealer dealer) {
        int dealerScore = dealer.score();
        return players.stream()
                .collect(toUnmodifiableMap(Player::name, player -> calculateRevenues(player, dealerScore)));
    }

    private int calculateRevenues(Player player, int dealerScore) {
        return GameOutcome.of(player.score(), dealerScore, player.hand().size())
                .calculateRevenue(player.bettingMoney());
    }
}
