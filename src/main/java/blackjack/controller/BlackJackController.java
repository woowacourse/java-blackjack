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

    public BlackJackController(final InputView inputView, final OutputView outputView) {
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
        giveMoreCardToWantingParticipants(blackJackGame);
        giveMoreDealerCard(blackJackGame, dealer);
        outputView.outputFinalCardStatus(dealer, participants);
        outputView.outputFinalResult(dealer, participants);
    }

    private void giveMoreCardToWantingParticipants(final BlackJackGame blackJackGame) {
        while (blackJackGame.canGiveCardToParticipant()) {
            Participant participant = blackJackGame.getCurrentTurnParticipant();
            boolean isReceive = Parser.parseCommand(inputView.inputCallOrStay(participant.getName()));
            blackJackGame.giveCardToCurrentTurnParticipant(isReceive);
            outputView.printPlayerCardStatus(participant.getName(), participant);
            checkBust(blackJackGame, participant);
        }
    }

    private void checkBust(final BlackJackGame blackJackGame, final Participant participant) {
        if (participant.isBust()) {
            outputView.printParticipantBust(participant.getName());
            blackJackGame.skipTurn();
        }
    }

    private void giveMoreDealerCard(final BlackJackGame blackJackGame, final Dealer dealer) {
        while (blackJackGame.isDealerCardDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.outputDealerGetCard();
            outputView.printPlayerCardStatus("딜러", dealer);
        }
        outputView.outputDealerCardFinish();
    }
}
