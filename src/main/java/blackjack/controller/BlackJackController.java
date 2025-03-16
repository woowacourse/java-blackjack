package blackjack.controller;

import blackjack.model.BlackJackRule;
import blackjack.model.game.BettedMoney;
import blackjack.model.game.Deck;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = generateParticipants();
        Dealer dealer = new Dealer();
        Deck deck = BlackJackRule.generateDeck();

        givePlayersTwoCardsWhenStart(deck, dealer, participants);
        progressParticipantTurn(participants, deck);
        progressDealerTurn(dealer, deck);
        outputFinalResult(dealer, participants);
    }

    private Participants generateParticipants() {
        String namesText = inputView.inputParticipantName();
        return new Participants(Parser.parseNames(namesText).stream()
                .map(name -> new Participant(name, new BettedMoney(inputView.inputParticipantMoney(name))))
                .toList());
    }

    private void givePlayersTwoCardsWhenStart(final Deck deck, final Dealer dealer, final Participants participants) {
        BlackJackRule.givePlayersTwoCardsWhenStart(deck, dealer, participants);
        outputView.outputFirstCardDistributionResult(participants, dealer);
    }

    private void progressParticipantTurn(final Participants participants, final Deck deck) {
        for (Participant participant : participants.getParticipants()) {
            giveCardToParticipantUntilWant(participant, deck);
        }
    }

    private void giveCardToParticipantUntilWant(final Participant participant, final Deck deck) {
        boolean isParticipantWantCard;
        do {
            isParticipantWantCard = Parser.parseCommand(inputView.inputCallOrStay(participant.getName()));
            BlackJackRule.giveCardToCurrentTurnParticipant(participant, isParticipantWantCard, deck);
            outputView.outputPlayerCardStatus(participant);
        } while(isParticipantWantCard && isParticipantNotBust(participant));
    }

    private boolean isParticipantNotBust(final Participant participant) {
        if (BlackJackRule.isPlayerBust(participant)) {
            outputView.outputParticipantBust(participant.getName());
            return false;
        }
        return true;
    }

    private void progressDealerTurn(final Dealer dealer, final Deck deck) {
        while (BlackJackRule.isDealerDrawable(dealer)) {
            BlackJackRule.giveCard(dealer, deck);
            outputView.outputDealerGetCard();
            outputView.outputPlayerCardStatus(dealer);
        }
        outputView.outputDealerCardFinish();
    }

    private void outputFinalResult(final Dealer dealer, final Participants participants) {
        outputView.outputFinalCardStatus(dealer, participants);
        Map<Participant, Long> winningMoneys = BlackJackRule.calculateWinningMoneys(dealer, participants);
        long dealerMoney = BlackJackRule.calculateDealerWinningMoney(winningMoneys);
        outputView.outputFinalWinningMoney(participants, dealerMoney, winningMoneys);
    }
}
