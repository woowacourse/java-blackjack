package controller;

import domain.BlackjackGame;
import domain.Deck;
import domain.PlayerResultRepository;
import domain.user.Player;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private final PlayerResultRepository playerResultRepository;
    private final Deck deck;

    public BlackjackController() {
        this.playerResultRepository = new PlayerResultRepository();
        this.deck = new Deck();
    }

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.readPlayersName());
        blackjackGame.initGame(this.deck);
        OutputView.printCardsStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.getPlayers().forEach(this::giveCardUntilImpossible);
        addCardToDealerIfPossible(blackjackGame);
        OutputView.printCardsStatusWithScore(blackjackGame.getDealer(), blackjackGame.getPlayers());
        blackjackGame.calculateAllResults(playerResultRepository);
        OutputView.printResults(playerResultRepository.getRepository());
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
