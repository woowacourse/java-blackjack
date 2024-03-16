package blackjack.model.participants;

import blackjack.model.blackjackgame.PlayersBlackjackResults;
import blackjack.model.blackjackgame.Profit;
import blackjack.model.generator.CardGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Players {
    private final List<Player> players;

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

    public PlayersBlackjackResults calculatePlayersResults(final Dealer dealer) {
        Map<Player, Profit> results = new LinkedHashMap<>();
        players.forEach(player -> results.put(player, player.calculateGameOutcomeProfit(dealer)));
        return new PlayersBlackjackResults(results);
    }

    private void validateIndex(final int index) {
        if (index < 0 || index >= players.size()) {
            throw new IllegalArgumentException("유효하지 않는 인덱스 접근입니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
