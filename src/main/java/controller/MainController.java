package controller;

import domain.PlayerCommand;
import domain.card.Cards;
import domain.card.shuffler.RandomCardsShuffler;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import view.InputView;
import view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cards cards = new Cards(new RandomCardsShuffler());
        Participants participants = new Participants(inputView.readPlayerNames(), cards);
        outputView.printInitialMessage(participants.getPlayerNames());
        outputView.printInitialState(participants);

        for (Player player : participants.getPlayers()) {
            boolean repeat = true;
            while (repeat) {
                PlayerCommand command = PlayerCommand.from(inputView.readHit(player.getName()));
                player.receiveAdditionalCard(command, cards);
                repeat = player.calculateScore() < 21 && command.isHit();
                outputView.printSinglePlayerCards(player);
            }
        }

        Dealer dealer = participants.getDealer();
        if (dealer.calculateScore() <= 16) {
            outputView.printFillDealerCards();
            dealer.fillCards(cards);
        }
    }
}
