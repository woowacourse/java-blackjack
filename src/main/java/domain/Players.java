package domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validateNotEmptyPlayers(players);
        this.players = players;
    }

    private void validateNotEmptyPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public static Players from(List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(collectingAndThen(toUnmodifiableList(), Players::new));
    }

    public void receiveCard(Deck deck) {
        players.forEach(player -> player.receiveCard(deck.draw()));
    }

    public Player getCurrentDrawablePlayer() {
        return players.stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("카드를 받을 수 있는 플레이어가 없습니다."));
    }

    public boolean hasDrawablePlayer() {
            return players.stream()
                    .anyMatch(Participant::isDrawable);
    }

    public void handOutCardToCurrentPlayer(Card card) {
        getCurrentDrawablePlayer().receiveCard(card);
    }

    public void standCurrentPlayer() {
        getCurrentDrawablePlayer().stand();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Map<String, GameOutcome> battleWith(Dealer dealer) {
        int dealerScore = dealer.score();
        return players.stream()
                .collect(toUnmodifiableMap(Player::name
                        , player-> GameOutcome.of(player.score(), dealerScore)));
    }
}
