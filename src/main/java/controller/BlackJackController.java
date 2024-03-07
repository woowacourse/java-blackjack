package controller;

import java.util.ArrayList;
import java.util.List;
import model.BlackJack;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final int INITIAL_CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        BlackJack blackJack = createBlackJack();
        blackJack.offerCardToPlayers(INITIAL_CARD_COUNT);
        outputView.printStartBlackJack(blackJack.getParticipants(), blackJack.getDealer());

        offerMoreCards(blackJack);

        outputView.printBlackJackScore(blackJack.getParticipants(), blackJack.getDealer());
        outputView.printPlayersOutcome(blackJack.getDealerOutCome(), blackJack.matchParticipantsOutcome());
    }

    private BlackJack createBlackJack() {
        List<String> names = inputView.askParticipantNames();
        List<Participant> players = new ArrayList<>(names.stream()
                .map(Participant::new).toList());
        return new BlackJack(new Participants(players), new Dealer());
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
        while (!participant.isOverMaximumSum() && inputView.askOneMoreCard(participant.getName())) {
            blackJack.offerCardToParticipant(participant, 1);
            outputView.printPlayerCardMessage(participant);
        }
    }

    private void checkDealerMoreCards(BlackJack blackJack) {
        while (blackJack.isDealerUnderThreshold()) {
            outputView.printDealerAddCard();
            blackJack.offerCardToDealer(1);
        }
    }
}
