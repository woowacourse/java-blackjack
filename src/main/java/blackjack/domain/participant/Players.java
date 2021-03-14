package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    public static final int MAX_PLAYER = 7;

    private final List<Player> players;
    private int index;

    public Players(final List<String> names) {
        this.players = convertToPlayers(names);
        validatePlayerCount(this.players);
        validateDuplicate(this.players);
        this.index = 0;
    }

    private List<Player> convertToPlayers(final List<String> names) {
        return names.stream()
            .map(Name::new)
            .map(Player::new)
            .collect(Collectors.toList())
            ;
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
        }
    }

    private void validateDuplicate(final List<Player> players) {
        int setSize = this.players.stream()
            .map(Player::getName)
            .collect(Collectors.toSet())
            .size();
        if (setSize != players.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void betCurrentPlayer(final double betAmount) {
        getCurrentTurnPlayer().betting(betAmount);
    }

    public void initDraw(final CardDeck cardDeck) {
        this.players.forEach(player -> player.initDraw(cardDeck));
    }

    public void drawCurrentTurnPlayer(final Card card) {
        getCurrentTurnPlayer().draw(card);
    }

    public void stayCurrentTurnPlayer() {
        getCurrentTurnPlayer().stay();
    }

    public String getCurrentPlayerName() {
        return getCurrentTurnPlayer().getName();
    }

    public Player getCurrentTurnPlayer() {
        return this.players.get(this.index);
    }

    public boolean isCurrentPlayerDone() {
        return this.players.get(this.index).isFinished();
    }

    public boolean isAllPlayerDone() {
        return this.index == this.players.size();
    }

    public void resetIndex() {
        this.index = 0;
    }

    public void passTurnToNextPlayer() {
        this.index += 1;
    }

    public List<Player> toList() {
        return new ArrayList<>(this.players);
    }
}
