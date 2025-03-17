package blackjack.user.player;

import blackjack.card.Card;
import blackjack.user.dealer.Dealer;
import java.util.Collections;
import java.util.List;

public class Players {

    private static final int PARTICIPANTS_MAX_SIZE = 25;

    private final List<Player> joinedPlayers;

    public Players(final List<Player> joinedPlayers) {
        validatePlayerNumber(joinedPlayers);
        validateDuplicatePlayerNames(joinedPlayers);

        this.joinedPlayers = joinedPlayers;
    }

    private void validatePlayerNumber(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 한 명 이상 참가해야 합니다.");
        }

        if (players.size() > PARTICIPANTS_MAX_SIZE) {
            throw new IllegalArgumentException("플레이어는 25명까지만 참가 가능합니다.");
        }
    }

    private void validateDuplicatePlayerNames(final List<Player> players) {
        long uniqueNameSize = players.stream()
            .map(Player::getName)
            .distinct().count();

        if (players.size() != uniqueNameSize) {
            throw new IllegalArgumentException("중복된 이름을 가진 플레이어가 있습니다.");
        }
    }

    public void addPickedCards(final Dealer dealer, final int count) {
        for (Player player : joinedPlayers) {
            List<Card> cards = dealer.pickCards(count);
            player.addCards(cards);
        }
    }

    public void addExtraCardToPlayer(final Dealer dealer, final PlayerName name, final int count) {
        Player player = findPlayerByName(name);
        List<Card> cards = dealer.pickCards(count);

        player.addCards(cards);
    }

    private Player findPlayerByName(final PlayerName name) {
        return joinedPlayers.stream()
            .filter(player -> player.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다."));
    }

    public void calculatePlayersProfit(final Dealer dealer) {
        for (Player player : joinedPlayers) {
            int profit = dealer.calculateProfitForPlayer(player);
            player.updateAmount(new BetAmount(player.getBetAmount().getPrincipal(), profit));
        }
    }

    public int calculateTotalProfit() {
        return joinedPlayers.stream()
            .map(Player::getBetAmount)
            .map(BetAmount::getProfit)
            .reduce(0, Integer::sum);
    }

    public List<PlayerName> getPlayerNames() {
        return joinedPlayers.stream()
            .map(Player::getName)
            .toList();
    }

    public List<Player> getJoinedPlayers() {
        return Collections.unmodifiableList(joinedPlayers);
    }
}
