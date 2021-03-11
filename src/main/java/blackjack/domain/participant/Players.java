package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Players {

    public static final int MAX_PLAYER = 7;

    private final Queue<Player> players;

    public Players(final List<String> names) {
        this.players = convertToPlayers(names);
        validatePlayerCount(this.players);
        validateDuplicate(this.players);
    }

    private Queue<Player> convertToPlayers(final List<String> names) {
        Queue<Player> players = new LinkedList<>();
        for (String name : names) {
            players.offer(new Player(new Name(name)));
        }
        return players;
    }

    private void validatePlayerCount(final Queue<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
        }
    }

    private void validateDuplicate(final Queue<Player> players) {
        int setSize = this.players.stream()
            .map(Player::getName)
            .collect(Collectors.toSet())
            .size();
        if (setSize != players.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void initDraw(final CardDeck cardDeck) {
        this.players.forEach(player -> player.initDraw(cardDeck));
    }

    public boolean isAllPlayerFinished() {
        return this.players
            .stream()
            .filter(Player::isFinished)
            .count() == this.players.size();
    }

    public void passTurnToNextPlayer() {
        this.players.offer(this.players.poll());
    }

    public void drawFirstOrderPlayer(final Card card) {
        getFirstOrderPlayer().draw(card);
    }

    public void stayFirstOrderPlayer() {
        getFirstOrderPlayer().stay();
    }

    public Player getFirstOrderPlayer() {
        return this.players.peek();
    }

    public boolean isFinishedCurrentPlayer() {
        return getFirstOrderPlayer().isFinished();
    }

    public String getFirstOrderPlayerName() {
        return getFirstOrderPlayer().getName();
    }

    public List<Player> toList() {
        return new ArrayList<>(this.players);
    }
}
