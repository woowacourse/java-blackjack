package blackjack.controller;

import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.DeckInitializer;
import blackjack.model.Participant;
import blackjack.model.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

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
        List<Participant> participants = Parser.parseParticipants(inputView.inputParticipant());
        Dealer dealer = new Dealer();
        giveInitialCards(dealer);
        participants.forEach(this::giveInitialCards);
        outputView.outputFirstCardDistributionResult(participants, dealer);
        participants.forEach(this::inputMoreCard);
        drewDealerCard(dealer);
        outputView.outputDealerCardFinish();
        outputView.outputFinalCardStatus(dealer, participants);
        outputView.outputFinalResult(dealer, participants);
    }

    private void drewDealerCard(Dealer dealer) {
        if (dealer.calculatePoint() <= 16) {
            outputView.outputDealerGetCard();
            dealer.putCard(deck.drawCard());
            drewDealerCard(dealer);
            outputView.printPlayerCardStatus("딜러", dealer);
        }
    }

    private void inputMoreCard(Participant participant) {
        String command = inputView.inputCallOrStay(participant.getName());
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

    public void giveInitialCards(Player player) {
        player.putCard(deck.drawCard());
        player.putCard(deck.drawCard());
    }
}
