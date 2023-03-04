package controller;

import domain.BlackjackGame;
import domain.Deck;
import domain.user.Player;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private final Deck deck;

    public BlackjackController() {
        this.deck = new Deck();
    }

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.readPlayersName());
        blackjackGame.initGame(this.deck);
        OutputView.printCardsStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.getPlayers().forEach(this::giveCardUntilImpossible);
        addCardToDealerIfPossible(blackjackGame);
        OutputView.printCardsStatusWithScore(blackjackGame.getDealer(), blackjackGame.getPlayers());
        OutputView.printResults(blackjackGame.calculateAllResults());
    }

    private void addCardToDealerIfPossible(BlackjackGame blackjackGame) {
        if (blackjackGame.getDealer().canAdd()) {
            OutputView.announceAddCardToDealer();
            blackjackGame.addCardToDealerIfPossible(this.deck);
        }
    }

    private void giveCardUntilImpossible(Player player) {
        while (judgeWhetherDrawCard(player)){
            player.addCard(this.deck.draw());
            OutputView.printCardsStatusOfUser(player);
        }
        if (player.canAdd()) {
            OutputView.printCardsStatusOfUser(player);
        }
    }

    private static boolean judgeWhetherDrawCard(Player player) {
        return player.canAdd() && InputView.readWhetherDrawCardOrNot(player);
    }
}
