package controller;

import domain.BlackjackGame;
import domain.Deck;
import domain.user.Player;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private final BlackjackGame blackjackGame;
    private final Deck deck;

    public BlackjackController() {
        this.blackjackGame = new BlackjackGame(InputView.readPlayersName());
        this.deck = new Deck();
    }

    public void run() {
        this.blackjackGame.initGame(this.deck);
        OutputView.printCardsStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.getPlayers().forEach(this::giveCardUntilImpossible);
        addCardToDealerIfPossible();
        OutputView.printCardsStatusWithScore(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.calculateResult();
    }

    private void addCardToDealerIfPossible() {
        if (blackjackGame.getDealer().canAdd()) {
            OutputView.announceAddCardToDealer();
            blackjackGame.addCardToDealerIfPossible(this.deck);
        }
    }

    private void giveCardUntilImpossible(Player player) {
        while (player.canAdd() && InputView.readWhetherDrawCardOrNot(player).equals("y")){
            player.addCard(this.deck.draw());
            OutputView.printCardsStatusOfUser(player);
        }
        OutputView.printCardsStatusOfUser(player);
    }
}
