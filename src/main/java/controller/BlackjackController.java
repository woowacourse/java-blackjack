package controller;

import domain.Blackjack;
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
        Blackjack blackjack = createBlackjack();
        blackjack.distributeInitialCards();
        OutputView.printInitialCards(blackjack.openDealerCards(), blackjack.openParticipantsCards());

        addMoreCards(blackjack);

        OutputView.printPlayersCardsAndSum(blackjack.getDealerNameAndCards(),
                blackjack.getParticipantsNameAndCards(), blackjack.getNameAndSumOfAllPlayers());
        OutputView.printMatchResult(blackjack.computeDealerMatchResult(), blackjack.computeParticipantsMatchResult());
    }

    private void addMoreCards(Blackjack blackjack) {
        List<String> participantNames = blackjack.getParticipantNames();
        for (String participantName : participantNames) {
            addMoreCardsByName(blackjack, participantName);
        }

        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            OutputView.printAddCardToDealer();
        }
    }

    private void addMoreCardsByName(Blackjack blackjack, String participantName) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(InputView.inputWantOneMoreCard(participantName));
            addOneCardByNameIfYes(blackjack, participantName, yesOrNo);
            OutputView.printPlayerCards(blackjack.getNameAndCardsByName(participantName));
        } while (yesOrNo == YesOrNo.YES);
    }

    private void addOneCardByNameIfYes(Blackjack blackjack, String name, YesOrNo yesOrNo) {
        if (yesOrNo == YesOrNo.YES) {
            blackjack.addCardByName(name);
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