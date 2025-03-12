package blackjack.controller;

import blackjack.model.game.BlackJackGame;
import blackjack.model.game.DeckInitializer;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> participantNames = Parser.parseNames(inputView.inputParticipant());
        Participants participants = inputBetAmount(participantNames);
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

    private Participants inputBetAmount(List<String> participantNames) {
        return new Participants(participantNames.stream().map(participantName -> new Participant(
                participantName,
                Parser.parseBetAmount(inputView.inputBetAmount(participantName))
        )).toList());
    }

    private void giveMoreParticipantCard(BlackJackGame blackJackGame) {
        while (blackJackGame.hasReadyParticipant()) {
            String participantName = blackJackGame.getCurrentTurnParticipantName();
            boolean isReceive = Parser.parseCommand(inputView.inputCallOrStay(participantName));
            blackJackGame.receiveCard(isReceive);
            outputView.printPlayerCardStatus(participantName, blackJackGame.getCurrentTurnParticipant());
            handleBust(participantName, blackJackGame);
        }
    }

    private void handleBust(String participantName, BlackJackGame blackJackGame) {
        if (blackJackGame.isCurrentParticipantBust()) {
            outputView.printParticipantBust(participantName);
            blackJackGame.changeNextTurn();
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
