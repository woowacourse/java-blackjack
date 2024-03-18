package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.money.Money;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private static final int MAX_PLAYERS_SIZE = 4;

    private final Dealer dealer;
    private final List<BettingPlayer> bettingPlayers;

    private Players(Dealer dealer, List<BettingPlayer> bettingPlayers) {
        this.dealer = dealer;
        validateSize(bettingPlayers);
        validateUniqueName(bettingPlayers);
        this.bettingPlayers = bettingPlayers;
    }

    public static Players from(List<String> names, List<Integer> bets) {
        List<BettingPlayer> bettingPlayers = new ArrayList<>();
        for (int index = 0; index < names.size(); index++) {
            String name = names.get(index);
            Integer bet = bets.get(index);
            bettingPlayers.add(new BettingPlayer(name, bet));
        }
        return new Players(new Dealer(), bettingPlayers);
    }

    private void validateSize(List<BettingPlayer> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("최소 한 명의 플레이어가 있어야 합니다.");
        }
        if (players.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException("최대 4명의 플레이어만 참여 가능합니다.");
        }
    }

    private void validateUniqueName(List<BettingPlayer> players) {
        if (isDuplicatedName(players)) {
            throw new IllegalArgumentException("중복된 이름을 사용할 수 없습니다.");
        }
    }

    private boolean isDuplicatedName(List<BettingPlayer> players) {
        return players.size() != players.stream().distinct().count();
    }

    public void drawStartCards(Deck deck) {
        dealer.drawStartCards(deck);
        for (BettingPlayer player : bettingPlayers) {
            player.drawStartCards(deck);
        }
    }

    public Money calculateDealerPrize() {
        return bettingPlayers.stream()
                .map(bettingPlayer -> bettingPlayer.calculatePrize(dealer))
                .reduce(Money.ZERO, Money::subtract);
    }

    public List<BettingPlayer> getBettingPlayers() {
        return Collections.unmodifiableList(bettingPlayers);
    }

    public List<Player> getPlayers() {
        return bettingPlayers.stream()
                .map(BettingPlayer::getPlayer)
                .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
