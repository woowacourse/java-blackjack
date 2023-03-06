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
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final int START_DEAL_COUNT = 2;

    private final CardDistributor cardDistributor;
    private final BlackJackResultMaker blackJackResultMaker;

    public BlackJackController(final CardDistributor cardDistributor, final BlackJackResultMaker blackJackResultMaker) {
        this.cardDistributor = cardDistributor;
        this.blackJackResultMaker = blackJackResultMaker;
    }

    public void play() {
        final List<Participant> participants = getParticipants();
        final Dealer dealer = new Dealer(makeEmptyCards());
        giveCards(dealer, participants);
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

    private void giveCards(final Dealer dealer, final List<Participant> participants) {
        dealCard(participants);
        dealCard(List.of(dealer));
        OutputView.printInitialCards(dealer, participants);
    }

    private void dealCard(final List<Participant> participants) {
        IntStream.range(0, START_DEAL_COUNT)
            .mapToObj(i -> participants)
            .forEach(cardDistributor::giveCard);
    }

    private void getPlayerAdditionalCard(final List<Participant> participants) {
        participants.forEach(this::getPlayerAdditionalCard);
    }

    private void getPlayerAdditionalCard(final Participant participant) {
        boolean hit = false;
        while (!participant.isBust() && (hit = getPlayerHitOrStand(participant))) {
            cardDistributor.giveCard(participant);
            OutputView.printCards(participant);
        }
        if (!hit) {
            OutputView.printCards(participant);
        }
    }

    private boolean getPlayerHitOrStand(final Participant participant) {
        return ExecuteContext.workWithExecuteStrategy(() -> InputView.inputPlayerHitOrStand(participant));
    }

    private void getDealerAdditionalCard(final Dealer dealer) {
        while (dealer.canReceiveCard()) {
            OutputView.printDealerReceptionNotice();
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
