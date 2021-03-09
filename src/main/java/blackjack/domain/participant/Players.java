package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.result.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    public Players(final List<Player> players) {
        validateDuplicate(players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList()));
        this.players = players;
    }

    public static Players of(final List<String> playerName) {
        validateDuplicate(playerName);
        final List<Player> players = playerName.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validateDuplicate(final List<String> playerName) {
        final int distinctName = (int) playerName.stream().distinct().count();
        if (distinctName != playerName.size()) {
            throw new IllegalArgumentException("입력된 플레이어의 이름이 중복됩니다.");
        }
    }

    public void receiveInitialCard(final CardDeck cardDeck) {
        for (Player player : players) {
            player.receiveInitialCard(cardDeck);
        }
    }

    public Map<Player, Result> generateEveryPlayerResult(final Dealer dealer) {
        final Map<Player, Result> playerResult = new LinkedHashMap<>();
        for (Player player : players) {
            playerResult.put(player, player.generateResult(dealer));
        }
        return Collections.unmodifiableMap(playerResult);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
