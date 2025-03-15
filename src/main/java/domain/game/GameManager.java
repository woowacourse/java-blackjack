package domain.game;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;
    private final CardDeck cardDeck;

    public GameManager(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = new CardDeck();
        shuffle();
    }

    public void shuffle() {
        cardDeck.shuffle();
    }

    public void distributeSetUpCards() {
        dealer.setUpCardDeck(cardDeck.poll(), cardDeck.poll());
        players.forEach(player -> player.setUpCardDeck(cardDeck.poll(), cardDeck.poll()));
    }

    public void distributeExtraCardToParticipant(Participant participant) {
        participant.takeMoreCard(cardDeck.poll());
    }

    public GameResult evaluateFinalScore() {
        ScoreInfo dealerScoreInfo = new ScoreInfo(dealer.calculateScore(), dealer.getCardCount());

        Map<Player, Winning> playerWinnings = players.stream()
                .collect(Collectors.toMap(player -> player
                        , player -> Winning.determine(
                                new ScoreInfo(player.calculateScore(), player.getCardCount()), dealerScoreInfo
                        )
                        , (player1, player2) -> player1, LinkedHashMap::new));

        return new GameResult(playerWinnings);
    }
}
