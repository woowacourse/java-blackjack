package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.card.CardPack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;
    private final CardPack cardPack;

    public GameManager(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        this.cardPack = new CardPack();
    }

    public void shuffle() {
        cardPack.shuffle();
    }

    public void distributeSetUpCards() {
        dealer.setUpCardDeck(cardPack.poll(), cardPack.poll());
        players.forEach(player -> player.setUpCardDeck(cardPack.poll(), cardPack.poll()));
    }

    public void distributeExtraCards(TakeMoreCardSelector selector) {
        for (Player player : players) {
            while (player.canTakeMoreCard()) {
                if (selector.isYes(player.getName())) {
                    player.takeMoreCard(cardPack.poll());
                    selector.takenResult(player.getName(), player.getCards());
                    continue;
                }
                selector.takenResult(player.getName(), player.getCards());
                break;
            }
        }

        if (dealer.canTakeMoreCard()) {
            dealer.takeMoreCard(cardPack.poll());
            selector.dealerTakenResult();
        }
    }

    public GameResult evaluateFinalScore() {
        int dealerScore = dealer.calculateScore();

        Map<Player, Winning> playerWinnings = players.stream()
                .collect(Collectors.toMap(player -> player
                        , player -> Winning.determine(player.calculateScore(), dealerScore)
                        , (player1, player2) -> player1, LinkedHashMap::new));

        return new GameResult(playerWinnings);
    }
}
