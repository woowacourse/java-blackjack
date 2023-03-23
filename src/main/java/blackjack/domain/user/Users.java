package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.PlayerNameProfitRates;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Users {

    private final Dealer dealer;
    private final Players players;

    public Users(final List<String> playerNames, final Queue<CardGroup> firstCardGroups) {
        dealer = new Dealer(firstCardGroups.poll());
        this.players = new Players(playerNames, firstCardGroups);
    }

    public Map<Name, CardGroup> getUserNameAndFirstOpenCardGroups() {
        final Map<Name, CardGroup> userNameAndFirstOpenCardGroups = new LinkedHashMap<>();
        userNameAndFirstOpenCardGroups.put(dealer.getName(), dealer.getFirstOpenCardGroup());
        final Map<PlayerName, CardGroup> playerFirstOpenCardGroups = players.getFirstOpenCardGroup();
        userNameAndFirstOpenCardGroups.putAll(playerFirstOpenCardGroups);
        return Collections.unmodifiableMap(userNameAndFirstOpenCardGroups);
    }

    public CardGroup getCardGroupBy(final PlayerName playername) {
        return players.getCardGroupBy(playername);
    }

    public boolean isDealerUnderDrawLimit() {
        return dealer.isUnderDrawLimit();
    }

    public List<PlayerName> getPlayerNames() {
        return players.getPlayerNames();
    }

    public void drawDealer(final Card card) {
        dealer.drawCard(card);
    }

    public void drawCard(final PlayerName name, final Card card) {
        players.drawCard(name, card);
    }

    public Map<Name, CardResult> getUserNameAndCardResults() {
        final Map<Name, CardResult> userNameAndResults = new LinkedHashMap<>();
        userNameAndResults.put(dealer.getName(), new CardResult(dealer.getCardGroups(), dealer.getScore()));
        final Map<PlayerName, CardResult> playerNameAndResults = players.getPlayerNameAndCardResults();
        userNameAndResults.putAll(playerNameAndResults);
        return Collections.unmodifiableMap(userNameAndResults);
    }

    public PlayerNameProfitRates getPlayerNameAndProfitRates() {
        return players.getPlayerNameAndProfitRates(dealer);
    }

    public boolean isDrawable(final Name playerName) {
        return players.isDrawable(playerName);
    }
}
