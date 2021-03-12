package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    public static final int MAX_PLAYER = 7;

    private final List<Player> players;

    public Players(final List<String> names) {
        this.players = convertToPlayers(names);
        validatePlayerCount(this.players);
        validateDuplicate(this.players);
    }

    private List<Player> convertToPlayers(final List<String> names) {
        return names.
            stream()
            .map(name -> new Player(new Name(name)))
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

    public boolean isAllPlayerBet(){
        return this.players
            .stream()
            .noneMatch(Player::isYetBet)
            ;
    }

    public String getCurrentBetPlayerName(){
        return getCurrentBetPlayer().getName();
    }

    public void betCurrentPlayer(final double betAmount) {
        getCurrentBetPlayer().betting(betAmount);
    }

    public Player getCurrentBetPlayer(){
        return this.players
            .stream()
            .filter(Player::isYetBet)
            .findFirst()
            .get()
            ;
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

    public void drawCurrentTurnPlayer(final Card card) {
        getCurrentTurnPlayer().draw(card);
    }

    public void stayCurrentTurnPlayer() {
        getCurrentTurnPlayer().stay();
    }

    public boolean isFinishedCurrentPlayer() {
        return getCurrentTurnPlayer().isFinished();
    }

    public String getCurrentPlayerName() {
        return getCurrentTurnPlayer().getName();
    }

    public Player getCurrentTurnPlayer() {
        return this.players
            .stream()
            .filter(player -> !player.isFinished())
            .findFirst()
            .get();
    }

    public List<Player> toList() {
        return new ArrayList<>(this.players);
    }
}
