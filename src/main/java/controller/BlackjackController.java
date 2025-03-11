package controller;

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
        blackjackManager.addMoreCardsToPlayers(InputView::inputWantOneMoreCard, OutputView::printPlayerCards);

        if (blackjackManager.addCardToDealerIfLowSum()) {
            OutputView.printAddCardToDealer();
        }
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
