package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.result.CardResult;
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
        final Map<Name, CardGroup> playerFirstOpenCardGroups = players.getFirstOpenCardGroup();
        userNameAndFirstOpenCardGroups.putAll(playerFirstOpenCardGroups);
        return Collections.unmodifiableMap(userNameAndFirstOpenCardGroups);
    }

    public CardGroup getCardGroupBy(final Name name) {
        return players.getCardGroupBy(name);
    }

    public boolean isDealerUnderDrawLimit() {
        return dealer.isUnderDrawLimit();
    }

    public List<Name> getPlayerNames() {
        return players.getPlayerNames();
    }

    public void drawDealer(final Card card) {
        dealer.drawCard(card);
    }

    public void drawCard(final Name userName, final Card card) {
        players.drawCard(userName, card);
    }

    public Map<Name, CardResult> getUserNameAndCardResults() {
        final Map<Name, CardResult> userNameAndResults = new LinkedHashMap<>();
        userNameAndResults.put(dealer.getName(), new CardResult(dealer.getCardGroups(), dealer.getScore()));
        final Map<Name, CardResult> playerNameAndResults = players.getPlayerNameAndCardResults();
        userNameAndResults.putAll(playerNameAndResults);
        return Collections.unmodifiableMap(userNameAndResults);
    }

    public Map<Name, Double> getPlayerNameAndProfitRates() {
        return players.getPlayerNameAndProfitRates(dealer);
    }

    public boolean isDrawable(final Name playerName) {
        return players.isDrawable(playerName);
    }
}
