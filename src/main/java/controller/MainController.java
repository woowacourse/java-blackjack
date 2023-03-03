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

        for (Player player : blackJackGame.getParticipants().getPlayers()) {
            boolean repeat = true;
            while (repeat) {
                PlayerCommand command = PlayerCommand.from(inputView.readHit(player.getName()));
                if (command.isHit()) {
                    blackJackGame.giveCardTo(player);
                }
                outputView.printSingleState(player);
                repeat = player.canReceive() && command.isHit();
            }
        }

        Dealer dealer = blackJackGame.getParticipants().getDealer();
        while (dealer.isFill()) {
            outputView.printFillDealerCards();
            blackJackGame.giveCardTo(dealer);
        }

        outputView.printFinalState(blackJackGame.getParticipants());

        WinningResult winningResult = new WinningResult(blackJackGame.getParticipants());
        outputView.printFinalResult();
        outputView.printDealerResult(winningResult.getDealerResult());
        outputView.printPlayerResult(winningResult.getPlayersResult());
    }

    private BlackJackGame createBlackJackGame() {
        Cards cards = new Cards(cardsShuffler);
        Participants participants = new Participants(inputView.readPlayerNames(), cards);
        BlackJackGame blackJackGame = new BlackJackGame(participants, cards);

        outputView.printInitialMessage(blackJackGame.getParticipants().getPlayerNames());
        outputView.printAllState(blackJackGame.getParticipants());
        return blackJackGame;
    }
}
