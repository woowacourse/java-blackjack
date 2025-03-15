package blackjack.model.game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.model.BettingMoney;
import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;
import blackjack.model.card.initializer.CardDeckInitializer;
import blackjack.model.player.Player;

public class BlackJackGame {

    private static final int INITIAL_DRAW_AMOUNT = 2;

    private final CardDeck cardDeck;
    private final BlackJackRule blackJackRule;
    private final PlayersBetting playersBetting;

    public BlackJackGame(final CardDeckInitializer cardDeckInitializer) {
        this.cardDeck = CardDeck.initializeFrom(cardDeckInitializer);
        this.blackJackRule = new BlackJackRule();
        this.playersBetting = new PlayersBetting();
    }

    public void dealInitialCards(final List<Player> players) {
        players.forEach(player -> {
            drawCard(player, INITIAL_DRAW_AMOUNT);
        });
    }

    public Cards openInitialCards(final Player player) {
        return blackJackRule.openInitialCards(player);
    }

    public boolean canDrawMoreCard(final Player player) {
        return blackJackRule.canDrawMoreCard(player);
    }

    public void drawCard(final Player player, final int amount) {
        player.receiveCards(cardDeck.draw(amount));
    }

    public Map<Player, Map<GameResult, Integer>> calculateResult(final List<Player> players) {
        Player dealer = players.getFirst();
        List<Player> users = players.stream()
                .skip(1L)
                .toList();
        return blackJackRule.calculateResult(dealer, users);
    }

    public Map<Player, Integer> calculateOptimalPoints(final List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        blackJackRule::calculateOptimalPoint,
                        (x, y) -> y,
                        LinkedHashMap::new
                ));
    }

    public void bet(final Player player, final int bettingAmount) {
        playersBetting.depositBettingMoney(player, new BettingMoney(bettingAmount));
    }

    public Map<Player, BigDecimal> calculatePlayerWinnings(final Map<Player, Map<GameResult, Integer>> gameResults) {
        Player dealer = findDealer(gameResults);
        Map<Player, BigDecimal> winnings = createPlayersWinnings(dealer);
        BigDecimal dealerWinnings = winnings.get(dealer);
        for (Player player : getUsers(gameResults)) {
            BigDecimal playerWinnings = calculateWinnings(gameResults.get(player),
                    playersBetting.withdrawMoney(player).getAmount());
            winnings.put(player, playerWinnings);
            dealerWinnings = dealerWinnings.subtract(playerWinnings);
        }
        winnings.put(dealer, dealerWinnings);
        return winnings;
    }

    private Player findDealer(final Map<Player, Map<GameResult, Integer>> gameResults) {
        return gameResults.keySet().stream()
                .filter(Player::isDealer)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    private Map<Player, BigDecimal> createPlayersWinnings(final Player dealer) {
        Map<Player, BigDecimal> winnings = new LinkedHashMap<>();
        winnings.put(dealer, BigDecimal.ZERO);
        return winnings;
    }

    private List<Player> getUsers(final Map<Player, Map<GameResult, Integer>> gameResults) {
        return gameResults.keySet().stream()
                .filter(player -> !player.isDealer())
                .toList();
    }

    private BigDecimal calculateWinnings(final Map<GameResult, Integer> results, final BigDecimal bettingMoneyAmount) {
        GameResult gameResult = results.keySet()
                .iterator()
                .next();
        return computePlayerWinning(gameResult, bettingMoneyAmount);
    }

    private BigDecimal computePlayerWinning(final GameResult result, final BigDecimal bettingMoneyAmount) {
        if (result == GameResult.WIN) {
            return bettingMoneyAmount.setScale(0, RoundingMode.FLOOR);
        }
        if (result == GameResult.LOSE) {
            return bettingMoneyAmount.negate().setScale(0, RoundingMode.FLOOR);
        }
        return BigDecimal.ZERO;
    }

}
