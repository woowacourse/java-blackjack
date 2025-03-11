package domain.game;

import static java.util.stream.Collectors.toMap;

import domain.card.CardPack;
import domain.participant.Dealer;
import domain.participant.Gambler;
import domain.participant.Player;
import java.util.ArrayList;
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

    public void distributeExtraCardsToPlayers(CardPack cardPack, GamblerAnswer playerAnswer) {
        players.forEach(player -> distributeExtraCards(player, playerAnswer, cardPack));
    }

    public void distributeExtraCardsToDealer(CardPack cardPack, GamblerAnswer dealerAnswer) {
        distributeExtraCards(dealer, dealerAnswer, cardPack);
    }

    private void distributeExtraCards(Gambler gambler, GamblerAnswer gamblerAnswer, CardPack cardPack) {
        while (gambler.canTakeMoreCard() && gamblerAnswer.isAnswerOK(gambler)) {
            gambler.takeCards(cardPack.poll());
            gamblerAnswer.notifyResult(gambler);
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
