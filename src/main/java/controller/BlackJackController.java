package controller;

import java.util.ArrayList;
import java.util.List;
import model.BlackJack;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        BlackJack blackJack = createBlackJack(inputView.askParticipantNames());

        blackJack.offerCardToPlayers(CardSize.TWO);
        outputView.printStartBlackJack(blackJack.getParticipants(), blackJack.getDealer());

        offerMoreCards(blackJack);

        outputView.printBlackJackScore(blackJack.getParticipants(), blackJack.getDealer());
        outputView.printPlayersOutcome(blackJack.getDealerOutCome(), blackJack.matchParticipantsOutcome());
    }

    private BlackJack createBlackJack(List<String> names) {
        Participants participants = createParticipants(names);
        Dealer dealer = new Dealer(Cards.selectRandomCards(CardSize.TWO));
        return new BlackJack(participants, dealer);
    }

    private Participants createParticipants(List<String> names) {
         return new Participants(new ArrayList<>(names.stream()
                .map(name -> new Participant(name, Cards.selectRandomCards(CardSize.TWO))).toList()));
    }

    private void offerMoreCards(BlackJack blackJack) {
        askAndOfferMoreCards(blackJack);
        checkDealerMoreCards(blackJack);
    }

    private void askAndOfferMoreCards(BlackJack blackJack) {
        for (Participant participant : blackJack.getParticipants()) {
            askAndOfferMoreCard(participant, blackJack);
        }
    }

    private void askAndOfferMoreCard(Participant participant, BlackJack blackJack) {
        while (!participant.isOverMaximumSum() && inputView.isOneMoreCard(participant.getName())) {
            blackJack.offerCardToParticipant(participant, CardSize.ONE);
            outputView.printPlayerCardMessage(participant);
        }
    }

    private void checkDealerMoreCards(BlackJack blackJack) {
        while (blackJack.isDealerUnderThreshold()) {
            outputView.printDealerAddCard();
            blackJack.offerCardToDealer(CardSize.ONE);
        }
    }
}
