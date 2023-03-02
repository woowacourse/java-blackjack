package controller;

import domain.area.CardArea;
import domain.deck.CardDeck;
import domain.player.Dealer;
import domain.player.DealerResult;
import domain.player.Name;
import domain.player.Participant;
import domain.player.ParticipantResult;
import domain.player.State;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class BlackJackController {

    public void run() {

        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final List<Participant> participants = dealParticipantsCards(cardDeck);
        final Dealer dealer = dealDealerCards(cardDeck);

        printStateAfterDealtCard(participants, dealer);
        hittingPlayer(cardDeck, participants, dealer);
        printStateAfterHittedCard(participants, dealer);

        final Map<Participant, ParticipantResult> playersResult = determineWinner(participants, dealer);
        final Map<DealerResult, Long> scoreBoard = countDealerResult(playersResult);

        printPlayerScoreBoard(participants, playersResult, scoreBoard);
    }

    private static void printPlayerScoreBoard(final List<Participant> participants,
                                              final Map<Participant, ParticipantResult> playersResult,
                                              final Map<DealerResult, Long> scoreBoard) {
        OutputView.showDealerScoreBoard(scoreBoard);
        OutputView.showParticipantsScoreBoard(playersResult, participants);
    }

    private static void printStateAfterHittedCard(final List<Participant> participants, final Dealer dealer) {
        OutputView.showPlayerStateResult(dealer);
        OutputView.showParticipantsStateResult(participants);
    }

    private void hittingPlayer(final CardDeck cardDeck, final List<Participant> participants, final Dealer dealer) {
        hitForParticipants(cardDeck, participants);
        hitForDealer(cardDeck, dealer);
    }

    private static void printStateAfterDealtCard(final List<Participant> participants, final Dealer dealer) {
        OutputView.showDealtCardTo(participants);
        OutputView.showStateOf(dealer);
        OutputView.showStateOf(participants);
    }

    private static Map<DealerResult, Long> countDealerResult(
            final Map<Participant, ParticipantResult> playersResult) {
        return playersResult.keySet()
                            .stream()
                            .collect(Collectors.groupingBy(participant -> playersResult.get(participant)
                                                                                       .convertToDealerResult(),
                                                           counting()));
    }

    private static Map<Participant, ParticipantResult> determineWinner(final List<Participant> participants,
                                                                       final Dealer dealer) {
        return participants.stream()
                           .collect(Collectors.toMap(
                                   Function.identity(),
                                   participant -> ParticipantResult.matchBetween(participant, dealer))
                           );
    }

    private void hitForDealer(final CardDeck cardDeck, final Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.dealerOneMoreCard();
            dealer.hit(cardDeck.draw());
        }
    }

    private void hitForParticipants(final CardDeck cardDeck, final List<Participant> participants) {
        participants.forEach(participant -> hitForParticipant(cardDeck, participant));
    }

    private void hitForParticipant(final CardDeck cardDeck, final Participant participant) {
        while (participant.canHit()) {
            participant.changeState(inputHitOrStay(participant));
            determineHitForParticipant(cardDeck, participant);
        }
    }

    private void determineHitForParticipant(final CardDeck cardDeck, final Participant participant) {
        if (participant.wantHit()) {
            participant.hit(cardDeck.draw());
        }
        OutputView.showStateOf(participant);
    }

    private State inputHitOrStay(final Participant participant) {
        if (InputView.readMoreCard(participant).equals("y")) {
            return State.HIT;
        }
        return State.STAY;
    }

    private Dealer dealDealerCards(final CardDeck cardDeck) {
        return new Dealer(new CardArea(cardDeck.draw(), cardDeck.draw()));
    }

    private List<Participant> dealParticipantsCards(final CardDeck cardDeck) {
        return InputView.readParticipantsName()
                        .stream()
                        .map(Name::new)
                        .map(name -> new Participant(name, new CardArea(cardDeck.draw(), cardDeck.draw())))
                        .collect(Collectors.toList());
    }
}
