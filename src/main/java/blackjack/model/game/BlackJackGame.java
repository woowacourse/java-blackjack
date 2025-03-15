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
    private static final int SINGLE_DRAW_AMOUNT = 1;

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
        BigDecimal dealerWinnings = BigDecimal.ZERO;
        Map<Player, BigDecimal> playerWinnings = new LinkedHashMap<>();
        Player dealer = gameResults.keySet()
                .stream()
                .filter(Player::isDealer)
                .findAny()
                .orElseThrow(IllegalStateException::new);
        playerWinnings.put(dealer, dealerWinnings);

        for (Player player : gameResults.keySet()) {
            if (player.isDealer()) {
                continue;
            }
            Map<GameResult, Integer> playerGameResult = gameResults.get(player);
            BettingMoney bettingMoney = playersBetting.withdrawMoney(player);
            BigDecimal bettingMoneyAmount = bettingMoney.getAmount();
            for (GameResult gameResult : playerGameResult.keySet()) {
                if (gameResult == GameResult.WIN) {
                    BigDecimal playerWinning = bettingMoneyAmount.multiply(BigDecimal.ONE)
                            .setScale(0, RoundingMode.FLOOR);
                    playerWinnings.put(player, playerWinning);
                    dealerWinnings = dealerWinnings.subtract(playerWinning);
                }
                if (gameResult == GameResult.LOSE) {
                    BigDecimal playerWinning = bettingMoneyAmount.multiply(BigDecimal.valueOf(-1))
                            .setScale(0, RoundingMode.FLOOR);
                    playerWinnings.put(player, playerWinning);
                    dealerWinnings = dealerWinnings.subtract(playerWinning);
                }
                if (gameResult == GameResult.DRAW) {
                    playerWinnings.put(player, BigDecimal.ZERO);
                }
            }
        }
        playerWinnings.put(dealer, dealerWinnings);
        return playerWinnings;
    }

}
