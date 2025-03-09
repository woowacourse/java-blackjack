package domain.game;

import static java.util.stream.Collectors.toMap;

import domain.card.CardPack;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Gamblers {

    private final Dealer dealer;
    private final List<Player> players;

    public Gamblers(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void distributeSetUpCards(CardPack cardPack) {
        dealer.takeCards(cardPack.poll(), cardPack.poll());
        players.forEach(player -> player.takeCards(cardPack.poll(), cardPack.poll()));
    }

    public void distributeExtraCards(CardPack cardPack, TakeMoreCardSelector selector) {
        for (Player player : players) {
            while (player.canTakeMoreCard()) {
                if (selector.isYes(player.getName())) {
                    player.takeCards(cardPack.poll());
                    selector.takenResult(player.getName(), player.getCards());
                    continue;
                }
                selector.takenResult(player.getName(), player.getCards());
                break;
            }
        }

        if (dealer.canTakeMoreCard()) {
            dealer.takeCards(cardPack.poll());
            selector.dealerTakenResult();
        }
    }

    public WinningCounts evaluateDealerWinnings() {
        Map<Player, Winning> playerWinnings = evaluatePlayerWinnings();
        int winCount = 0, drawCount = 0, loseCount = 0;

        for (Winning winning : playerWinnings.values()) {
            if (winning == Winning.WIN) {
                loseCount++;
            }
            if (winning == Winning.DRAW) {
                drawCount++;
            }
            if (winning == Winning.LOSE) {
                winCount++;
            }
        }
        return new WinningCounts(winCount, drawCount, loseCount);
    }

    public Map<Player, Winning> evaluatePlayerWinnings() {
        int dealerScore = dealer.calculateScore();
        return players.stream()
            .collect(toMap(Function.identity(),
                player -> Winning.determine(player.calculateScore(), dealerScore)));
    }
}
