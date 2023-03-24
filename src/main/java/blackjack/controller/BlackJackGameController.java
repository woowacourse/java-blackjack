package blackjack.controller;

import blackjack.domain.BettingAmount;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Game;
import blackjack.domain.GamePlayer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.TryCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = Game.from(new GamePlayer(new Dealer(), makePlayers()));
        gameStart(game.getDealer(), game.getPlayers());
        gamePlay(game);
        gameEnd(game.getDealer(), game.getPlayers());
        gameResult(game);
    }

    private Players makePlayers() {
        try {
            return Players.from(inputView.readPlayersName());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return makePlayers();
        }
    }

    private void gameStart(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            BettingAmount bettingAmount = readBettingAmount(player.getName());
            player.setBettingAmount(bettingAmount);
        }
        Map<String, List<String>> playersToPrintFormat = playersToPrintFormat(players);
        String dealerFirstCardToPrintFormat = dealerFirstCardToPrintFormat(dealer);
        outputView.printGameStartMessage(playersToPrintFormat, dealerFirstCardToPrintFormat);
    }

    private BettingAmount readBettingAmount(String name) {
        try {
            return new BettingAmount(inputView.readBettingAmount(name));
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return readBettingAmount(name);
        }
    }

    private void gamePlay(Game game) {
        playersHitOrStand(game, game.getPlayers());
        dealerHitOrStand(game, game.getDealer());
    }

    private void playersHitOrStand(Game game, Players players) {
        for (Player player : players.getPlayers()) {
            tryPlayerHitOrStand(game, player);
        }
    }

    private void tryPlayerHitOrStand(Game game, Player player) {
        try {
            playerHitOrStand(game, player);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            tryPlayerHitOrStand(game, player);
        }
    }

    private void playerHitOrStand(Game game, Player player) {
        while (player.isHitPossible()) {
            String tryCommand = inputView.readTryCommand(player.getName());
            if (!TryCommand.isHit(tryCommand)) {
                outputView.printCards(player.getName(), cardsToPrintFormat(player.getAllCards()));
                break;
            }
            game.hit(player);
            outputView.printCards(player.getName(), cardsToPrintFormat(player.getAllCards()));
        }
    }

    private void dealerHitOrStand(Game game, Dealer dealer) {
        while (dealer.isHitPossible()) {
            outputView.printDealerHit();
            game.hit(dealer);
        }
    }

    private void gameEnd(Dealer dealer, Players players) {
        outputView.printDealerResult(cardsToPrintFormat(dealer.getAllCards()), dealer.getScore());
        outputView.printPlayerResult(playersToPrintFormat(players), players.getPlayersScore());
    }

    private void gameResult(Game game) {
        GameResult gameResult = new GameResult(game.getDealer(), game.getPlayers());
        outputView.printFinalProfit(gameResult.getDealerResult(), gameResult.getPlayersResult());
    }

    private Map<String, List<String>> playersToPrintFormat(Players players) {
        Map<String, List<String>> playersPrintFormat = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            String playerName = player.getName();
            List<String> cards = cardsToPrintFormat(player.getAllCards());
            playersPrintFormat.put(playerName, cards);
        }
        return playersPrintFormat;
    }

    private String dealerFirstCardToPrintFormat(Dealer dealer) {
        Card dealerCard = dealer.getFirstCard();
        return dealerCard.getCardNumberToString() + dealerCard.getCardSymbolToString();
    }

    private List<String> cardsToPrintFormat(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCardNumberToString() + card.getCardSymbolToString())
                .collect(Collectors.toList());
    }
}
