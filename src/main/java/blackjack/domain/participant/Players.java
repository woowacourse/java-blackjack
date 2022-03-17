package blackjack.domain.participant;

import blackjack.domain.DrawCallback;
import blackjack.domain.card.Deck;
import blackjack.domain.result.BettingResult;
import blackjack.domain.result.PlayerResult;

import java.util.*;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players startWithTwoCards(final List<String> names, final List<Integer> amounts, final Deck deck) {
        validatePlayerNamesDuplicated(names);
        validateSameSize(names, amounts);
        Players players = new Players(refinePlayers(names, amounts));
        players.distributeCards(deck);
        return players;
    }

    public void takeTurn(Deck deck, DrawCallback callback) {
        for (Player player : players) {
            player.hitOrStand(deck, callback);
        }
    }

    public BettingResult compareScore(Dealer dealer) {
        final Map<Player, PlayerResult> result = new HashMap<>();

        for (final Player player : players) {
            final PlayerResult playerResult = dealer.judgeWinner(player);
            result.put(player, playerResult);
        }

        return BettingResult.of(result);
    }

    private void distributeCards(Deck deck) {
        for (final Player player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
        }
    }

    private static List<Player> refinePlayers(List<String> names, List<Integer> amounts) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), new Bet(amounts.get(i))));
        }

        return players;
    }

    private static void validatePlayerNamesDuplicated(final List<String> playerNames) {
        final Set<String> validNames = new HashSet<>(playerNames);
        if (validNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어명은 중복될 수 없습니다.");
        }
    }

    private static void validateSameSize(List<String> names, List<Integer> amounts) {
        if (names.size() != amounts.size()) {
            throw new IllegalArgumentException("플레이어 이름과 베팅의 수가 일치하지 않습니다.");
        }
    }

    public List<Player> getStatuses() {
        return List.copyOf(players);
    }
}
