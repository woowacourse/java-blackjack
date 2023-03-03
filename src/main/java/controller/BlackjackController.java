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
        String whetherDrawCard = "y";
        while (player.canAdd() && (whetherDrawCard = InputView.readWhetherDrawCardOrNot(player)).equals("y")){
            player.addCard(this.deck.draw());
            OutputView.printCardsStatusOfUser(player);
        }
        if (whetherDrawCard.equals("n")) {
            OutputView.printCardsStatusOfUser(player);
        }
    }
}
