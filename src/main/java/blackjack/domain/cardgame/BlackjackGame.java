package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final CardDeck cardDeck;
    private final Map<Player, Money> playerBetting;

    public BlackjackGame() {
        this.cardDeck = new CardDeck();
        this.playerBetting = new LinkedHashMap<>();
    }

    public void giveCard(final Participant participant) {
        participant.addCard(cardDeck.draw());
    }

    public void initializeHand(final Dealer dealer, final List<Player> players) {
        for (final Player player : players) {
            giveCard(player);
            giveCard(player);
        }
        giveCard(dealer);
        giveCard(dealer);
    }

    public void bet(final Player player, final Money money) {
        playerBetting.put(player, money);
    }

    public Map<Player, Money> findPlayerProfits(final Dealer dealer) {
        return playerBetting.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> findProfit(dealer, entry.getKey(), entry.getValue()),
                        (x, y) -> x, LinkedHashMap::new
                ));
    }

    private Money findProfit(final Dealer dealer, final Player player, final Money bettingMoney) {
        final Status status = judgePlayerStatus(dealer, player);
        return bettingMoney.multiply(status.getMultiplier());
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
        final Map<Player, Money> playerProfits = findPlayerProfits(dealer);
        return -playerProfits.values()
                .stream()
                .mapToInt(Money::getValue)
                .sum();
    }
}
