package blackjack.domain.user;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;
import blackjack.domain.result.WinningStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Users {

    private static final String NOT_CONTAIN_USER_BY_NAME = "해당 이름의 유저를 찾을 수 없습니다.";

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
        return Map.copyOf(usersFirstOpenCardGroups);
    }

    public Map<String, CardGroup> getStatus() {
        final Map<String, CardGroup> usersStatus = new LinkedHashMap<>();
        usersStatus.put(dealer.getName(), dealer.getCardGroups());
        final Map<String, CardGroup> playersStatus = players.getStatus();
        usersStatus.putAll(playersStatus);
        return usersStatus;
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

    public Player getUser(final String name) {
        return players.getPlayers()
                .stream()
                .filter(player -> player.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME));
    }

    public Map<String, WinningStatus> getWinningResult() {
        return players.getWinningResult(dealer);
    }
}
