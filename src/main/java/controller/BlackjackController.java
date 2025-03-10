package controller;

import controller.dto.DealerMatchResultCount;
import controller.dto.NameAndCards;
import controller.dto.NameAndSums;
import controller.dto.ParticipantsMatchResult;
import domain.BlackjackManager;
import domain.Dealer;
import domain.Deck;
import domain.DeckGenerator;
import domain.Participant;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        BlackjackManager blackjackManager = createBlackjackManager();

        distributeInitialCards(blackjackManager);
        addMoreCards(blackjackManager);
        printGameResult(blackjackManager);
    }

    private void distributeInitialCards(BlackjackManager blackjackManager) {
        blackjackManager.distributeInitialCards();
        blackjackManager.openInitialCards();
        OutputView.printInitialCards(
                NameAndCards.toNameAndOpenedCards(blackjackManager.getDealer()),
                NameAndCards.toNameAndOpenedCards(blackjackManager.getParticipants())
        );
    }

    private void addMoreCards(BlackjackManager blackjackManager) {
        List<Player> participants = blackjackManager.getParticipants();
        for (Player participant : participants) {
            addMoreCards(blackjackManager, participant);
        }

        if (blackjackManager.addCardToDealerIfLowScore()) {
            OutputView.printAddCardToDealer();
        }
    }

    private void addMoreCards(BlackjackManager blackjackManager, Player participant) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(InputView.inputWantOneMoreCard(participant.getName()));
            addOneCardIfYes(blackjackManager, participant, yesOrNo);
            OutputView.printPlayerCards(NameAndCards.toNameAndCards(participant));
        } while (yesOrNo == YesOrNo.YES);
    }

    private void addOneCardIfYes(BlackjackManager blackjackManager, Player participant, YesOrNo yesOrNo) {
        if (yesOrNo == YesOrNo.YES) {
            blackjackManager.addOneCard(participant);
        }
    }

    private void printGameResult(BlackjackManager blackjackManager) {
        final Dealer dealer = blackjackManager.getDealer();
        final List<Player> participants = blackjackManager.getParticipants();
        OutputView.printPlayersCardsAndSum(NameAndCards.toNameAndCards(dealer),
                NameAndCards.toNameAndCards(participants),
                NameAndSums.from(blackjackManager.computePlayerSum()));

        final var participantsMatchResult = blackjackManager.computeParticipantsMatchResult(dealer, participants);
        final var dealerMathResultCount = blackjackManager.computeDealerMatchResultCount(participantsMatchResult);
        OutputView.printMatchResults(blackjackManager.getDealerName(),
                DealerMatchResultCount.from(dealerMathResultCount),
                ParticipantsMatchResult.from(participantsMatchResult));
    }

    private BlackjackManager createBlackjackManager() {
        List<String> names = InputView.inputParticipantName();
        List<Participant> participants = createParticipants(names);
        Dealer dealer = new Dealer();
        Deck deck = DeckGenerator.generateDeck();
        Players players = createPlayers(dealer, participants);
        return new BlackjackManager(players, deck);
    }

    private List<Participant> createParticipants(List<String> names) {
        return names.stream()
                .map(String::strip)
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    private Players createPlayers(Dealer dealer, List<Participant> participants) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(participants);
        return new Players(players);
    }
}