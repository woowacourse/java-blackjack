package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final CardDeck cardDeck;
    private final Map<Player, BettingAmount> playerBetting;

    public BlackjackGame() {
        this.cardDeck = new CardDeck();
        this.playerBetting = new LinkedHashMap<>();
    }

    public void giveCard(final Player player) {
        player.addCard(cardDeck.draw());
    }

    public void initializeHand(final Dealer dealer, final List<Player> players) {
        for (final Player player : players) {
            giveCard(player);
            giveCard(player);
        }
        giveCard(dealer);
        giveCard(dealer);
    }

    public void bet(final Player player, final BettingAmount bettingAmount) {
        playerBetting.put(player, bettingAmount);
    }

    public Map<Player, Profit> findPlayerProfits(final Dealer dealer) {
        return playerBetting.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> findProfit(dealer, entry.getKey(), entry.getValue().getValue()),
                        (x, y) -> x, LinkedHashMap::new
                ));
    }

    private Profit findProfit(final Dealer dealer, final Player player, final int bettingAmount) {
        final Status status = judgePlayerStatus(dealer, player);

        if (Status.WIN.equals(status)) {
            return new Profit(bettingAmount);
        }
        if (Status.PUSH.equals(status)) {
            return new Profit(0);
        }
        if (Status.LOSE.equals(status)) {
            return new Profit(-bettingAmount);
        }
        if (Status.BLACKJACK.equals(status)) {
            return new Profit(bettingAmount + bettingAmount / 2);
        }

        throw new IllegalStateException("[ERROR] 유효하지 않은 경우의 수 입니다.");
    }

    private Status judgePlayerStatus(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return Status.LOSE;
        }
        if (dealer.isBust()) {
            return Status.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return Status.PUSH;
        }
        if (player.isBlackjack()) {
            return Status.BLACKJACK;
        }
        if (dealer.getScore() < player.getScore()) {
            return Status.WIN;
        }
        return Status.LOSE;
    }

    public int findDealerProfit(final Dealer dealer) {
        final Map<Player, Profit> playerProfits = findPlayerProfits(dealer);
        return -playerProfits.values()
                .stream()
                .mapToInt(Profit::getValue)
                .sum();
    }
}
