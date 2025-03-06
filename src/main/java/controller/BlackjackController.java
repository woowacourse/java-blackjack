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
import view.OutputView;

public class BlackjackController {

    public void run() {
        Blackjack blackjack = createBlackjack();
        blackjack.distributeInitialCards();

        // 초기 카드 오픈
        OpenCardsResponse openCardsResponse = blackjack.openInitialCards();
        OutputView.printInitialCardsDistribution(openCardsResponse);

        // 참여자들의 추가 카드 분배
        List<String> participantNames = blackjack.getParticipantNames();
        for (String participantName : participantNames) {
            boolean isDone = false;
            boolean isFirst = true;
            while (!isDone) {
                switch (YesOrNo.from(InputView.inputWantMoreCard(participantName))) {
                    case YES -> {
                        OutputView.printPlayerCards(blackjack.addCardToCurrentParticipant(participantName));
                        isFirst = false;
                    }
                    case NO -> {
                        if (isFirst) {
                            OutputView.printPlayerCards(blackjack.getPlayerByName(participantName));
                        }
                        isDone = true;
                    }
                }
            }
        }

        // 딜러의 추가 카드 분배
        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            OutputView.printAddCardToDealer();
        }
    }

    private Blackjack createBlackjack() {
        String names = InputView.inputParticipantName();
        List<Participant> participants = createParticipants(names);
        Dealer dealer = new Dealer();
        Deck deck = DeckGenerator.generateDeck();
        Players players = createPlayers(dealer, participants);
        return new Blackjack(players, deck);
    }

    private List<Participant> createParticipants(String names) {
        List<String> parsed = Arrays.stream(names.split(",", -1))
                .map(String::strip)
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