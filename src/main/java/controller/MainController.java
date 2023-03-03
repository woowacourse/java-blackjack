package controller;

import domain.BlackJackGame;
import domain.PlayerCommand;
import domain.WinningResult;
import domain.card.Cards;
import domain.card.shuffler.CardsShuffler;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import view.InputView;
import view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CardsShuffler cardsShuffler;

    public MainController(final InputView inputView, final OutputView outputView, CardsShuffler cardsShuffler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardsShuffler = cardsShuffler;
    }

    public void run() {
        BlackJackGame blackJackGame = createBlackJackGame();
        playGame(blackJackGame);
        showResult(blackJackGame);
    }

    private BlackJackGame createBlackJackGame() {
        Cards cards = new Cards(cardsShuffler);
        Participants participants = new Participants(inputView.readPlayerNames(), cards);
        BlackJackGame blackJackGame = new BlackJackGame(participants, cards);

        outputView.printInitialMessage(blackJackGame.getPlayers());
        outputView.printAllState(blackJackGame.getParticipants());

        return blackJackGame;
    }

    private void playGame(final BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.getPlayers()) {
            distributeCardToPlayer(blackJackGame, player);
        }

        distributeCardToDealer(blackJackGame);
    }

    private void distributeCardToPlayer(final BlackJackGame blackJackGame, final Player player) {
        boolean isRepeat = true;
        while (isRepeat) {
            PlayerCommand command = PlayerCommand.from(inputView.readHit(player.getName()));
            distributeByCommand(blackJackGame, player, command);
            isRepeat = player.canReceive() && command.isHit();
        }
    }

    private void distributeByCommand(final BlackJackGame blackJackGame, final Player player, final PlayerCommand command) {
        if (command.isHit()) {
            blackJackGame.giveCardTo(player);
        }
        outputView.printSingleState(player);
    }

    private void distributeCardToDealer(final BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        while (dealer.isFill()) {
            outputView.printFillDealerCards();
            blackJackGame.giveCardTo(dealer);
        }
    }

    private void showResult(final BlackJackGame blackJackGame) {
        outputView.printFinalState(blackJackGame.getParticipants());

        WinningResult winningResult = new WinningResult(blackJackGame.getParticipants());
        outputView.printFinalResult();
        outputView.printDealerResult(winningResult.getDealerResult());
        outputView.printPlayerResult(winningResult.getPlayersResult());
    }
}
