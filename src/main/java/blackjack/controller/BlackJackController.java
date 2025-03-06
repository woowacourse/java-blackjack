package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.DeckInitializer;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Deck deck;

    public BlackJackController(InputView inputView, OutputView outputView, DeckInitializer deckInitializer) {
        this.inputView = inputView;
        this.outputView = outputView;
        deck = deckInitializer.generateDeck();
    }

    public void run() {
        Participants participants = Parser.parseParticipants(inputView.inputParticipant());
        Dealer dealer = new Dealer();
        giveInitialCards(dealer);
        participants.stream().forEach(this::giveInitialCards);
        outputView.outputFirstCardDistributionResult(participants, dealer);
        participants.stream().forEach(this::inputMoreCard);
        drawDealerCard(dealer);
        outputView.outputDealerCardFinish();
        outputView.outputFinalCardStatus(dealer, participants);
        outputView.outputFinalResult(dealer, participants);
    }

    private void drawDealerCard(Dealer dealer) {
        if (dealer.calculatePoint() <= 16) {
            outputView.outputDealerGetCard();
            dealer.putCard(deck.drawCard());
            drawDealerCard(dealer);
            outputView.printPlayerCardStatus("딜러", dealer);
        }
    }

    private void inputMoreCard(Participant participant) {
        String command = inputView.inputCallOrStay(participant.getName());
        validateCommand(command);
        if (command.equals("y")) {
            participant.putCard(deck.drawCard());
            outputView.printPlayerCardStatus(participant.getName(), participant);
            if (participant.isBust()) {
                outputView.printParticipantBust(participant.getName());
                return;
            }
            inputMoreCard(participant);
        }
    }

    private void validateCommand(String command) {
        if (!(command.equals("y") || command.equals("n"))) {
            throw new IllegalArgumentException("y 또는 n을 입력해 주세요.");
        }
    }

    public void giveInitialCards(Player player) {
        player.putCard(deck.drawCard());
        player.putCard(deck.drawCard());
    }
}
