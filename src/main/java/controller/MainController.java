package controller;

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

        Cards cards = new Cards(cardsShuffler);
        Participants participants = new Participants(inputView.readPlayerNames(), cards);
        outputView.printInitialMessage(participants.getPlayerNames());
        outputView.printAllState(participants);

        for (Player player : participants.getPlayers()) {
            boolean repeat = true;
            while (repeat) {
                PlayerCommand command = PlayerCommand.from(inputView.readHit(player.getName()));
                if (command.isHit()) {
                    player.receiveCard(cards.getCard());
                }
                outputView.printSingleState(player);
                repeat = player.canReceive() && command.isHit();
            }
        }

        Dealer dealer = participants.getDealer();
        while (dealer.isFill()) {
            outputView.printFillDealerCards();
            dealer.receiveCard(cards.getCard());
        }

        outputView.printFinalState(participants);

        WinningResult winningResult = new WinningResult(participants);
        outputView.printFinalResult();
        outputView.printDealerResult(winningResult.getDealerResult());
        outputView.printPlayerResult(winningResult.getPlayersResult());
    }
}
