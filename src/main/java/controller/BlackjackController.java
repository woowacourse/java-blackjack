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

    private Blackjack createBlackjack() {
        List<String> names = InputView.inputParticipantName();
        Deck deck = DeckGenerator.generateDeck();
        Players players = createPlayers(new Dealer(), createParticipants(names));
        return new Blackjack(players, deck);
    }

    private Players createPlayers(Dealer dealer, List<Participant> participants) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(participants);
        return new Players(players);
    }

    private List<Participant> createParticipants(List<String> names) {
        return names.stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    private void addMoreCards(Blackjack blackjack) {
        List<Participant> participants = blackjack.getParticipants();
        for (Participant participant : participants) {
            addMoreCards(blackjack, participant);
        }

        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            OutputView.printAddCardToDealer();
        }
    }

    private void addMoreCards(Blackjack blackjack, Participant participant) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(InputView.inputWantOneMoreCard(participant.getName()));
            addOneCardIfYes(blackjack, participant, yesOrNo);
            OutputView.printPlayerCards(blackjack.getNameAndCards(participant));
        } while (yesOrNo.equals(YesOrNo.YES));
    }

    private void addOneCardIfYes(Blackjack blackjack, Participant participant, YesOrNo yesOrNo) {
        if (yesOrNo.equals(YesOrNo.YES)) {
            blackjack.addCard(participant);
        }
    }
}
