package domain;

import domain.bet.Bet;
import domain.bet.BlackJackBetCalculator;
import domain.card.CardGenerator;
import domain.gamer.Dealer;
import domain.gamer.GamerGenerator;
import domain.gamer.Player;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.GameResult.calculateResult;
import static domain.GameResult.getAllGameResults;

public class GameManager {
    public static final int BLACKJACK_NUMBER = 21;
    public static final int START_RECEIVE_CARD = 2;
    private static final int DEALER_HIT_SCORE = 16;

    private final Dealer dealer;
    private final List<Player> players;
    private final CardGenerator cardGenerator;

    private GameManager(final Dealer dealer, final List<Player> players, CardGenerator cardGenerator) {
        this.dealer = dealer;
        this.players = players;
        this.cardGenerator = cardGenerator;
    }

    public static GameManager create(final List<String> playerNames, CardGenerator cardGenerator) {
        Dealer dealer = GamerGenerator.generateDealer();
        List<Player> players = GamerGenerator.generatePlayer(playerNames, cardGenerator);
        return new GameManager(dealer, players, cardGenerator);
    }

    public void initOpeningCards() {
        IntStream.range(0, START_RECEIVE_CARD).forEach(count -> {
            dealCardToDealer();
            players.forEach(this::dealCardToPlayer);
        });
    }

    public void dealerHitUntilStand() {
        while (dealer.isLessThen(DEALER_HIT_SCORE)) {
            dealCardToDealer();
        }
    }

    public Map<String, GameResult> calculatePlayerGameResult() {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> calculateResult(dealer, player)
                ));
    }

    public int getDealerBetResult(Map<String, Bet> playerBets) {
        BlackJackBetCalculator calculator = new BlackJackBetCalculator(playerBets);
        return calculator.getDealerBetResult(dealer, players);
    }

    public Map<String, Integer> getPlayerBetResult(Map<String, Bet> playerBets) {
        BlackJackBetCalculator calculator = new BlackJackBetCalculator(playerBets);
        return calculator.getPlayerBetResult(dealer, players);
    }

    public void dealCardToPlayer(Player player) {
        player.receiveCard(cardGenerator.peekCard());
    }

    public void dealCardToDealer() {
        dealer.receiveCard(cardGenerator.peekCard());
    }

    public int getDealerHitCount() {
        return dealer.getReceivedCardCount();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getAbleToHitPlayers() {
        return players.stream()
                .filter(player -> !player.isBust())
                .toList();
    }
}
