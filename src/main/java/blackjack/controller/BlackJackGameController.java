package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Game;
import blackjack.domain.GamePlayer;
import blackjack.domain.GameResult;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGameController {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = Game.from(new GamePlayer(makePlayers(), new Dealer()));
        printStartCards(game);
        play(game);
        finish(game);
    }

    private Players makePlayers() {
        try {
            return Players.from(inputView.readPlayersName());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
            return makePlayers();
        }
    }

    private List<String> getCardNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCardNumberToString() + card.getCardSymbolToString()).collect(
                        Collectors.toList());
    }

    private void finish(Game game) {
        outputView.printDealerResult(getCardNames(game.showDealerAllCards()), game.getDealerScore());

        for (int i = 0; i < game.getPlayersCount(); i++) {
            printOnePlayerResult(game, i);
        }

        GameResult gameResult = new GameResult(game);
        outputView.printEndMsg();
        outputView.printDealerWinningResult(gameResult.getDealerResult());
        outputView.printPlayerWinningResult(gameResult.getPlayerResult());
    }

    private void printOnePlayerResult(Game game, int i) {
        String playerName = game.showPlayerNameByIndex(i);
        List<String> cards = getCardNames(game.showPlayerCardsByIndex(i));
        int index = game.getPlayerScoreByIndex(i);
        outputView.printPlayerResult(playerName, cards, index);
    }

    private void play(Game game) {
        for (int i = 0; i < game.getPlayersCount(); i++) {
            playersPlay(game, i);
        }
        dealerPlay(game);
    }

    private void dealerPlay(Game game) {
        while (game.isHitDealer()) {
            outputView.printDealerHit();
            game.giveCardToDealer();
        }
    }

    private void printStartCards(Game game) {
        outputView.printStartMsg(game.showPlayersName());
        outputView.printDealerCards(getCardNames(game.showDealerCards()), System.lineSeparator());
        for (int i = 0; i < game.getPlayersCount(); i++) {
            String playerName = game.showPlayerNameByIndex(i);
            List<String> cards = getCardNames(game.showPlayerCardsByIndex(i));
            outputView.printPlayerCards(playerName, cards, LINE_SEPARATOR);
        }
    }

    private void playersPlay(Game game, int i) {
        while (isCheckPlayerCommand(game, i)) {
            game.giveCardToPlayerByIndex(i);
            outputView.printPlayerCards(game.showPlayerNameByIndex(i),
                    getCardNames(game.showPlayerCardsByIndex(i)), System.lineSeparator());
        }
    }

    private boolean isCheckPlayerCommand(Game game, int i) {
        if (game.isHitPlayerByIndex(i)) {
            return inputView.readTryCommand(game.showPlayerNameByIndex(i)).equals("y");
        }
        outputView.printPlayerCards(game.showPlayerNameByIndex(i),
                getCardNames(game.showPlayerCardsByIndex(i)), System.lineSeparator());
        return false;
    }
}
