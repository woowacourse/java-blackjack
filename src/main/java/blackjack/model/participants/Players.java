package blackjack.model.participants;

import blackjack.model.blackjackgame.PlayersResults;
import blackjack.model.generator.CardGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Players {
    private final List<Player> players;
    private final PlayersResults playersResults = new PlayersResults();

    public Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public void distributeCards(final CardGenerator cardGenerator) {
        players.forEach(player -> player.addCards(cardGenerator.drawFirstCardsDealt()));
    }

    public int getSize() {
        return players.size();
    }

    public Player getPlayer(final int index) {
        validateIndex(index);
        return players.get(index);
    }

    public boolean canPlayerGetMoreCard(final int index) {
        Player player = getPlayer(index);
        return player.checkCanGetMoreCard();
    }

    public void updatePlayer(final int index, final CardGenerator cardGenerator) {
        validateIndex(index);
        Player findPlayer = players.get(index);
        findPlayer.addCard(cardGenerator.drawCard());
    }

    public void calculatePlayersResults(final Dealer dealer) {
        players.forEach(player -> playersResults.add(player, player.getProfit(dealer)));
    }

    private void validateIndex(final int index) {
        if (index < 0 || index >= players.size()) {
            throw new IllegalArgumentException("유효하지 않는 인덱스 접근입니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public PlayersResults getPlayersResults() {
        return playersResults;
    }
}
