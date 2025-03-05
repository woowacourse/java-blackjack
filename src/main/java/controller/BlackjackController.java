package controller;

import domain.Blackjack;
import domain.Dealer;
import domain.Deck;
import domain.DeckGenerator;
import domain.Participant;
import domain.Participants;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;

public class BlackjackController {

    public void run() {
        String names = InputView.inputParticipantName();
        Participants participants = createParticipants(names);
        Dealer dealer = new Dealer();
        Deck deck = DeckGenerator.generateDeck();
        Blackjack blackjack = new Blackjack(dealer, participants, deck);
        blackjack.initialCardsDistribution();

    }

    private Participants createParticipants(String names) {
        List<String> parsed = Arrays.stream(names.split(",", -1))
                .toList();

        return new Participants(parsed.stream()
                .map(Participant::new)
                .collect(Collectors.toList()));
    }
}