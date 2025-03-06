package controller;

import domain.Blackjack;
import domain.Dealer;
import domain.Deck;
import domain.DeckGenerator;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.dto.OpenCardsResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;

public class BlackjackController {

    public void run() {
        String names = InputView.inputParticipantName();
        List<Participant> participants = createParticipants(names);
        Dealer dealer = new Dealer();
        Deck deck = DeckGenerator.generateDeck();
        Players players = createPlayers(dealer, participants);
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();

        // 초기 카드 오픈
        OpenCardsResponse openCardsResponse = blackjack.openPlayersCards();


    }

    private List<Participant> createParticipants(String names) {
        List<String> parsed = Arrays.stream(names.split(",", -1))
                .toList();

        return parsed.stream()
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