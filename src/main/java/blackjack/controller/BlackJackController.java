package blackjack.controller;

import blackjack.model.BlackJackGame;
import blackjack.model.Dealer;
import blackjack.model.DeckInitializer;
import blackjack.model.Participant;
import blackjack.model.Participants;
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
        BlackJackGame blackJackGame = new BlackJackGame(
                new DeckInitializer(),
                dealer,
                participants
        );
        blackJackGame.initializeGame();
        outputView.outputFirstCardDistributionResult(participants, dealer);
        inputMoreCard(blackJackGame);
        giveMoreDealerCard(blackJackGame, dealer);
        outputView.outputFinalCardStatus(dealer, participants);
        outputView.outputFinalResult(dealer, participants);
    }

    private void inputMoreCard(BlackJackGame blackJackGame) {
        while (blackJackGame.hasReady()) {
            Participant participant = blackJackGame.getCurrentTurnParticipant();
            boolean isReceive = Parser.parseCommand(inputView.inputCallOrStay(participant.getName()));
            blackJackGame.receiveCard(isReceive);
            outputView.printPlayerCardStatus(participant.getName(), participant);
            if (participant.isBust()) {
                outputView.printParticipantBust(participant.getName());
                blackJackGame.skipTurn();
            }
        }
    }

    private void giveMoreDealerCard(BlackJackGame blackJackGame, Dealer dealer) {
        while (blackJackGame.isDrawableDealerCard()) {
            blackJackGame.drewDealerCards();
            outputView.outputDealerGetCard();
            outputView.printPlayerCardStatus("딜러", dealer);
        }
        outputView.outputDealerCardFinish();
    }
}
