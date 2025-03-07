package controller;

import domain.Blackjack;
import domain.Dealer;
import domain.Deck;
import domain.DeckGenerator;
import domain.Participant;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Blackjack blackjack = createBlackjack();
        // 초기 카드 분배
        blackjack.distributeInitialCards();

        // 초기 카드 오픈
        OutputView.printInitialCards(blackjack.openDealerCards(), blackjack.openParticipantsCards());

        // 추가 카드 분배
        addMoreCards(blackjack);

        // 카드 합 결과
        OutputView.printPlayerCardsAndSum(blackjack.getDealerNameAndCards(),
                blackjack.getParticipantsNameAndCards(),
                blackjack.getNameAndSumOfAllPlayers());

        // 최종 승패 결과 출력
        OutputView.printMatchResult(blackjack.computeDealerMatchResult(),
                blackjack.computeParticipantsMatchResult());
    }

    private void addMoreCards(Blackjack blackjack) {
        // 참여자들의 추가 카드 분배
        List<String> participantNames = blackjack.getParticipantNames();
        for (String participantName : participantNames) {
            addMoreCardsEachParticipant(blackjack, participantName);
        }

        // 딜러의 추가 카드 분배
        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            OutputView.printAddCardToDealer();
        }
    }

    private void addMoreCardsEachParticipant(Blackjack blackjack, String participantName) {
        YesOrNo wantOneMoreCard;
        do {
            wantOneMoreCard = YesOrNo.from(InputView.inputWantOneMoreCard(participantName));
            addMoreCardByAnswer(blackjack, participantName, wantOneMoreCard);
            OutputView.printPlayerCards(blackjack.getNameAndCardsByName(participantName));
        } while (wantOneMoreCard.equals(YesOrNo.YES));
    }

    private void addMoreCardByAnswer(Blackjack blackjack, String participantName, YesOrNo wantOneMoreCard) {
        if (wantOneMoreCard.equals(YesOrNo.YES)) {
            blackjack.addCardToCurrentParticipant(participantName);
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