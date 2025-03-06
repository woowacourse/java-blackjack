package domain;

import java.util.Collections;
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

    public GameResult evaluateFinalScore() {
        int dealerScore = dealer.calculateScore();
        Map<Player, Winning> playerWinnings = players.stream()
            .collect(Collectors.toMap(player -> player,
                player -> Winning.determine(player.calculateScore(), dealerScore)));

        return new GameResult(playerWinnings);
    }
}
