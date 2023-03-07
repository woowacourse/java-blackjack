package blackjack.domain.user;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.WinningStatus;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Users {

    private final Dealer dealer;
    private final Players players;

    public Users(final List<String> playerNames, final Deck deck) {
        dealer = new Dealer(deck.drawFirstCardGroup());
        this.players = new Players(playerNames, deck);
    }

    public Map<String, CardGroup> getFirstOpenCardGroups() {
        final Map<String, CardGroup> usersFirstOpenCardGroups = new LinkedHashMap<>();
        usersFirstOpenCardGroups.put(dealer.getName(), dealer.getFirstOpenCardGroup());
        final Map<String, CardGroup> playerFirstOpenCardGroups = players.getFirstOpenCardGroup();
        usersFirstOpenCardGroups.putAll(playerFirstOpenCardGroups);
        return Collections.unmodifiableMap(usersFirstOpenCardGroups);
    }

    public Map<String, CardGroup> getStatus() {
        final Map<String, CardGroup> usersStatus = new LinkedHashMap<>();
        usersStatus.put(dealer.getName(), dealer.getCardGroups());
        final Map<String, CardGroup> playersStatus = players.getStatus();
        usersStatus.putAll(playersStatus);
        return Map.copyOf(usersStatus);
    }

    public boolean isDealerUnderDrawLimit() {
        return dealer.isUnderDrawLimit();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public void drawDealer(final Deck deck) {
        dealer.drawCard(deck);
    }

    public Map<String, WinningStatus> getPlayersWinningResults() {
        return players.getWinningResult(dealer);
    }

    public Map<WinningStatus, Long> getDealersWinningResults() {
        return getPlayersWinningResults()
                .values()
                .stream()
                .collect(collectingAndThen(groupingBy(WinningStatus::opposite, counting()),
                        Collections::unmodifiableMap));
    }

    public boolean isPlayerBust(final String name) {
        return players.isPlayerBust(name);
    }

    public void drawCard(final String userName, final Deck deck) {
        players.drawCard(userName, deck);
    }

    public boolean isBlackJackScore(final String name) {
        return players.isBlackJackScore(name);
    }

    public Map<String, CardResult> getUserNameAndCardResults() {
        final Map<String, CardResult> userNameAndResults = new LinkedHashMap<>();
        userNameAndResults.put(dealer.getName(), new CardResult(dealer.getCardGroups(), dealer.getScore()));
        final Map<String, CardResult> playerNameAndResults = players.getPlayerNameAndCardResults();
        userNameAndResults.putAll(playerNameAndResults);
        return Collections.unmodifiableMap(userNameAndResults);
    }
}
