package controller;

import static controller.YesOrNo.NO;
import static controller.YesOrNo.YES;
import static controller.YesOrNo.from;

import controller.dto.DealerMatchResultCountDto;
import controller.dto.NameAndCardsDto;
import controller.dto.NameAndSumsDto;
import controller.dto.ParticipantsMatchResultDto;
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
                NameAndCardsDto.toNameAndOpenedCards(blackjackManager.getDealer()),
                NameAndCardsDto.toNameAndOpenedCards(blackjackManager.getParticipants())
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
        YesOrNo isContinued = wantOneMoreCard(participant);
        if (isContinued == NO) {
            OutputView.printPlayerCards(NameAndCardsDto.toNameAndCards(participant));
            return;
        }
        while (isContinued == YES) {
            blackjackManager.addOneCard(participant);
            OutputView.printPlayerCards(NameAndCardsDto.toNameAndCards(participant));
            isContinued = wantOneMoreCard(participant);
        }
    }

    private YesOrNo wantOneMoreCard(Player participant) {
        return from(InputView.inputWantOneMoreCard(participant.getName()));
    }

    private void printGameResult(BlackjackManager blackjackManager) {
        final Dealer dealer = blackjackManager.getDealer();
        final List<Player> participants = blackjackManager.getParticipants();
        OutputView.printPlayersCardsAndSum(NameAndCardsDto.toNameAndCards(dealer),
                NameAndCardsDto.toNameAndCards(participants),
                NameAndSumsDto.from(blackjackManager.computePlayerSum()));

        final var participantsMatchResult = blackjackManager.computeParticipantsMatchResult(dealer, participants);
        final var dealerMathResultCount = blackjackManager.computeDealerMatchResultCount(participantsMatchResult);
        OutputView.printMatchResults(blackjackManager.getDealerName(),
                DealerMatchResultCountDto.from(dealerMathResultCount),
                ParticipantsMatchResultDto.from(participantsMatchResult));
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