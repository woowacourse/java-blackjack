package controller;

import common.ExecuteContext;
import domain.model.BlackJackResultMaker;
import domain.model.CardDistributor;
import domain.model.Cards;
import domain.model.Dealer;
import domain.model.Participant;
import domain.model.Player;
import domain.model.Result;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final CardDistributor cardDistributor;
    private final BlackJackResultMaker blackJackResultMaker;

    public BlackJackController(final CardDistributor cardDistributor, final BlackJackResultMaker blackJackResultMaker) {
        this.cardDistributor = cardDistributor;
        this.blackJackResultMaker = blackJackResultMaker;
    }

    public void play() {
        final List<Participant> participants = getParticipants();
        final Dealer dealer = new Dealer(makeEmptyCards());
        giveInitialCards(dealer, participants);
        getPlayerAdditionalCard(participants);
        getDealerAdditionalCard(dealer);
        printTotalCardState(dealer, participants);
        printResult(dealer, participants);
    }

    private List<Participant> getParticipants() {
        return ExecuteContext.workWithExecuteStrategy(() -> {
            List<String> names = InputView.inputNames();
            return names.stream()
                .map(name -> new Player(makeEmptyCards(), name))
                .collect(Collectors.toList());
        });
    }

    private Cards makeEmptyCards() {
        return new Cards(new HashSet<>());
    }

    private void giveInitialCards(final Dealer dealer, final List<Participant> participants) {
        cardDistributor.giveInitCards(dealer, participants);
        OutputView.printInitialCards(dealer, participants);
    }

    private void getPlayerAdditionalCard(final List<Participant> participants) {
        participants.forEach(this::getPlayerAdditionalCard);
    }

    private void getPlayerAdditionalCard(final Participant participant) {
        boolean intent = false;
        while (!participant.isBust()
            && (intent = ExecuteContext.workWithExecuteStrategy(() -> InputView.inputCardIntent(participant)))) {
            cardDistributor.giveCard(participant);
            OutputView.printCard(participant);
        }
        if (!intent) {
            OutputView.printCard(participant);
        }
    }

    private void getDealerAdditionalCard(final Dealer dealer) {
        while (dealer.canReceiveCard()) {
            OutputView.printDealerReceiveNotice();
            cardDistributor.giveCard(dealer);
        }
    }

    private void printTotalCardState(final Dealer dealer, final List<Participant> participants) {
        OutputView.printTotalCardState(dealer, participants);
    }

    private void printResult(final Dealer dealer, final List<Participant> participants) {
        Result dealerResult = blackJackResultMaker.makeDealerResult(dealer, participants);
        Map<Participant, Result> playerResult = blackJackResultMaker.makeParticipantsResult(dealer, participants);
        OutputView.printDealerResult(dealerResult, dealer);
        OutputView.printResult(playerResult);
    }
}
