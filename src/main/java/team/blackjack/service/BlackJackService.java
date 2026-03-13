package team.blackjack.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Card;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;
import team.blackjack.domain.Players;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.RevenueResult;
import team.blackjack.service.dto.ScoreResult;

public class BlackJackService {
    private BlackjackGame blackjackGame;

    public void initGame(List<String> playerNames) {
        final Dealer dealer = new Dealer();
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        blackjackGame = new BlackjackGame(dealer, new Players(players));
    }

    public void drawInitialCards() {
        blackjackGame.drawInitialCards();
    }

    public boolean isPlayerBust(String name) {
        return blackjackGame.isPlayerBust(name);
    }

    public void hitPlayer(String name) {
        blackjackGame.hitPlayer(name);
    }

    public boolean shouldDealerHit() {
        return blackjackGame.shouldDealerHit();
    }

    public void hitDealer() {
        blackjackGame.hitDealer();
    }

    public ScoreResult calculateAllParticipantScore() {
        final List<String> playerNames = getAllPlayerNames();
        final Map<String, Integer> playerScores = this.blackjackGame.getAllPlayerScore();
        final Map<String, List<String>> playerCards = this.blackjackGame.getAllPlayerCards()
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(Card::getCardName)
                                .toList()
                ));

        final int dealerScore = this.blackjackGame.getDealerScore();
        final List<String> dealerCardNames = this.blackjackGame.getDealerCards().stream()
                .map(Card::getCardName)
                .toList();

        return new ScoreResult(
                dealerCardNames,
                dealerScore,
                playerNames,
                playerCards,
                playerScores
        );
    }

    public List<String> getAllPlayerNames() {
        return this.blackjackGame.getAllPlayerNames();
    }

    public List<String> findPlayerCardNamesByName(String name) {
        return this.blackjackGame.getPlayerCardsByName(name).stream()
                .map(Card::getCardName)
                .toList();
    }

    public DrawResult getDrawResult() {
        final List<String> playerNames = getAllPlayerNames();
        final List<Card> cards = this.blackjackGame.getDealerCards();
        final Map<String, List<String>> playerCards = this.blackjackGame.getAllPlayerCards()
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(Card::getCardName)
                                .toList()
                ));

        return new DrawResult(playerNames, cards.getFirst().getCardName(), playerCards);
    }

    public RevenueResult getRevenueResult() {
        final double dealerRevenue = blackjackGame.calculateDealerRevenue();
        final Map<String, Double> allPlayerRevenue = blackjackGame.calculateAllPlayerRevenue();

        return new RevenueResult(dealerRevenue, allPlayerRevenue);
    }

    public void batMoney(String playerName, int battingMoney) {
        this.blackjackGame.batMoney(playerName, battingMoney);
    }
}
