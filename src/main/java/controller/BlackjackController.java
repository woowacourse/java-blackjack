package controller;

import domain.Blackjack;
import domain.Dealer;
import domain.Deck;
import domain.DeckGenerator;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.dto.NameAndCards;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Blackjack blackjack = createBlackjack();
        blackjack.distributeInitialCards();
        OutputView.printInitialCards(NameAndCards.toNameAndOpenedCards(blackjack.getDealer()),
                NameAndCards.toNameAndCards(blackjack.getParticipants()));

        addMoreCards(blackjack);

        OutputView.printPlayersCardsAndSum(NameAndCards.toNameAndCards(blackjack.getDealer()),
                NameAndCards.toNameAndCards(blackjack.getParticipants()), blackjack.getNameAndSumOfAllPlayers());
        OutputView.printMatchResult(blackjack.computeDealerMatchResult(), blackjack.computeParticipantsMatchResult());
    }

    private void addMoreCards(Blackjack blackjack) {
        List<Player> participants = blackjack.getParticipants();
        for (Player participant : participants) {
            addMoreCardsByName(blackjack, participant);
        }

        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            OutputView.printAddCardToDealer();
        }
    }

    private void addMoreCardsByName(Blackjack blackjack, Player participant) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(InputView.inputWantOneMoreCard(participant.getName()));
            addOneCardByNameIfYes(blackjack, participant, yesOrNo);
            OutputView.printPlayerCards(NameAndCards.toNameAndCards(participant));
        } while (yesOrNo == YesOrNo.YES);
    }

    private void addOneCardByNameIfYes(Blackjack blackjack, Player participant, YesOrNo yesOrNo) {
        if (yesOrNo == YesOrNo.YES) {
            blackjack.addOneCard(participant);
        }
    }

    private Blackjack createBlackjack() {
        List<String> names = InputView.inputParticipantName();
        List<Participant> participants = createParticipants(names);
        Dealer dealer = new Dealer();
        Deck deck = DeckGenerator.generateDeck();
        Players players = createPlayers(dealer, participants);
        return new Blackjack(players, deck);
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