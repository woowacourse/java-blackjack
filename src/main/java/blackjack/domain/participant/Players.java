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

    public Players(final List<Player> players) {
        this.players = players;
        validatePlayerCount(this.players);
        this.index = 0;
    }

    private void validatePlayerCount(final List<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
        }
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

    public void passTurnToNextPlayer() {
        this.index += 1;
    }

    public List<BetAmount> getBetAmounts(final Dealer dealer) {
        return this.players
            .stream()
            .map(player -> player.profit(player.judgeByDealerState(dealer)))
            .collect(Collectors.toList());
    }

    public List<Player> toList() {
        return new ArrayList<>(this.players);
    }
}
