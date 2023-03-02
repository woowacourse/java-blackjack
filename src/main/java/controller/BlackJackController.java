package controller;

import domain.area.CardArea;
import domain.deck.CardDeck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.State;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {

        final List<Name> participantNames = createParticipantNames();

        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();

        final List<Participant> participants = dealParticipantsCards(cardDeck, participantNames);
        final Dealer dealer = dealDealerCars(cardDeck);

        OutputView.printAfterDeal(participants);
        OutputView.showPlayerState(dealer);
        OutputView.showPlayersState(participants);

        for (final Participant participant : participants) {
            addParticipant(cardDeck, participant);
        }

        //딜러 반복해서 주고
        while (dealer.canHit()) {
            OutputView.dealerOneMoreCard();
            dealer.hit(cardDeck.draw());
        }

        //전체 상태 다 출력 - 결과
        OutputView.showParticipantsStateResult(participants);
        OutputView.showPlayerStateResult(dealer);

        //최종 승패
        int dealerWin = 0;
        int dealerDraw = 0;
        int dealerLose = 0;
        final int dealerScore = dealer.score();

        for (final Participant participant : participants) {

            if (participant.isBurst()) {
                System.out.println(participant.name().value() + ": " + "패");
                dealerWin++;
            } else {
                if (dealer.isBurst()) {
                    System.out.println(participant.name().value() + ": " + "승");
                    dealerLose++;
                } else {
                    if (participant.score() > dealerScore) {
                        System.out.println(participant.name().value() + ": " + "승");
                        dealerLose++;
                    } else if (participant.score() == dealerScore) {
                        System.out.println(participant.name().value() + ": " + "무승부");
                        dealerDraw++;
                    } else {
                        System.out.println(participant.name().value() + ": " + "패");
                        dealerWin++;
                    }
                }
            }
        }

        System.out.println("딜러: " + dealerWin + "승" + dealerDraw + "무" + dealerLose + "패");
    }

    private static void addParticipant(final CardDeck cardDeck, final Participant participant) {
        // canMoreCard -> 21 이하이면서, 참여자가 STAY 를 원하지 않는 경우
        while (participant.canHit()) {
            if (InputView.readMoreCard(participant).equals("y")) {
                participant.changeState(State.HIT);
            } else {
                participant.changeState(State.STAY);
            }

            if (participant.wantHit()) {
                participant.hit(cardDeck.draw());
            }
            OutputView.showPlayerState(participant);
        }
    }

    private Dealer dealDealerCars(final CardDeck cardDeck) {
        // TODO 수정
        return new Dealer(new Name("딜러"), new CardArea(cardDeck.draw(), cardDeck.draw()));
    }

    private List<Participant> dealParticipantsCards(final CardDeck cardDeck, final List<Name> participantNames) {
        return participantNames.stream()
                .map(it -> new Participant(it, new CardArea(cardDeck.draw(), cardDeck.draw())))
                .collect(Collectors.toList());
    }

    private List<Name> createParticipantNames() {
        return InputView.readParticipantsName()
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }
}
