package controller;

import domain.cardtable.CardTable;
import domain.deck.CardDeck;
import domain.player.Name;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.dealer.DealerResult;
import domain.player.participant.Participant;
import domain.player.participant.ParticipantResult;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class BlackJackController {

    public void run() {

        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final CardTable cardTable = CardTable.readyToPlayBlackjack(cardDeck);

        final List<Participant> participants = createParticipants();
        final Dealer dealer = createDealer();
        final List<Player> players = createPlayers(participants, dealer);

        deal(cardTable, players);

        printStateAfterDeal(participants, dealer);
        hittingPlayer(cardTable, participants, dealer);
        printStateAfterHit(participants, dealer);

        final Map<Participant, ParticipantResult> participantsResult = cardTable.determineWinner(participants, dealer);
        final Map<DealerResult, Long> scoreBoard = countDealerResult(participantsResult);

        printPlayerScoreBoard(participants, participantsResult, scoreBoard);
    }

    private static List<Player> createPlayers(final List<Participant> participants, final Dealer dealer) {
        List<Player> players = new ArrayList<>(participants);
        players.add(dealer);

        return players;
    }

    private static void deal(final CardTable cardTable, final List<Player> players) {
        dealCard(cardTable, players);
        dealCard(cardTable, players);
    }

    private static void dealCard(final CardTable cardTable, final List<Player> players) {
        players.forEach(cardTable::dealCardTo);
    }

    private static Dealer createDealer() {
        return new Dealer();
    }

    private static List<Participant> createParticipants() {
        return InputView.readParticipantsName()
                        .stream()
                        .map(Name::new)
                        .map(Participant::new)
                        .collect(Collectors.toList());
    }

    private static void printPlayerScoreBoard(final List<Participant> participants,
                                              final Map<Participant, ParticipantResult> playersResult,
                                              final Map<DealerResult, Long> scoreBoard) {
        OutputView.showDealerScoreBoard(scoreBoard);
        OutputView.showParticipantsScoreBoard(playersResult, participants);
    }

    private static void printStateAfterHit(final List<Participant> participants, final Dealer dealer) {
        OutputView.showPlayerStateResult(dealer);
        OutputView.showParticipantsStateResult(participants);
    }

    private static void printStateAfterDeal(final List<Participant> participants, final Dealer dealer) {
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

    private void hittingPlayer(final CardTable cardTable, final List<Participant> participants, final Dealer dealer) {
        hitForParticipants(cardTable, participants);
        hitForDealer(cardTable, dealer);
    }

    private void hitForDealer(final CardTable cardTable, final Dealer dealer) {
        do {
            OutputView.dealerOneMoreCard();
        } while (cardTable.dealCardTo(dealer));
    }

    private void hitForParticipants(final CardTable cardTable, final List<Participant> participants) {
        participants.forEach(participant -> hitForParticipant(cardTable, participant));
    }

    private void hitForParticipant(final CardTable cardTable, final Participant participant) {
        while (inputHitOrStay(participant)) {
            cardTable.dealCardTo(participant);
            OutputView.showStateOf(participant);
        }
    }

    private boolean inputHitOrStay(final Participant participant) {
        return InputView.readMoreCard(participant).equals("y");
    }
}
