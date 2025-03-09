package blackjack.controller;

import blackjack.model.game.BlackJackGame;
import blackjack.model.game.DeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = Parser.parseParticipants(inputView.inputParticipant());
        Dealer dealer = new Dealer();
        DeckInitializer deckInitializer = new DeckInitializer();
        BlackJackGame blackJackGame = new BlackJackGame(
                deckInitializer.generateDeck(),
                dealer,
                participants
        );
        blackJackGame.initializeGame();
        outputView.outputFirstCardDistributionResult(participants, dealer);
        giveMoreParticipantCard(blackJackGame);
        giveMoreDealerCard(blackJackGame, dealer);
        outputView.printFinalCardStatus(dealer, participants);
        outputView.printFinalResult(dealer, participants);
    }

    private void giveMoreParticipantCard(BlackJackGame blackJackGame) {
        while (blackJackGame.hasReadyParticipant()) {
            Participant participant = blackJackGame.getCurrentTurnParticipant();
            boolean isReceive = Parser.parseCommand(inputView.inputCallOrStay(participant.getName()));
            blackJackGame.receiveCard(isReceive);
            outputView.printPlayerCardStatus(participant.getName(), participant);
            if (participant.isBust()) {
                outputView.printParticipantBust(participant.getName());
                blackJackGame.changeNextTurn();
            }
        }
    }

    private void giveMoreDealerCard(BlackJackGame blackJackGame, Dealer dealer) {
        while (blackJackGame.isDrawableDealerCard()) {
            blackJackGame.drawDealerCards();
            outputView.outputDealerGetCard();
            outputView.printPlayerCardStatus("딜러", dealer);
        }
        outputView.printDealerCardDone();
    }
}
