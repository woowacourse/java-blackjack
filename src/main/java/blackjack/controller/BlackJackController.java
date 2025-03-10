package blackjack.controller;

import blackjack.model.game.BlackJackGame;
import blackjack.model.game.DeckInitializer;
import blackjack.model.game.GameResult;
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
        GameResult gameResult = new GameResult(dealer, participants);
        outputView.outputFinalResult(gameResult.getWinLoseResult(), gameResult.getDealerWinCount(),
                gameResult.getDealerLoseCount());
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
