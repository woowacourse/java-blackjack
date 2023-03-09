package controller;

import domain.cardtable.CardTable;
import domain.deck.CardDeck;
import domain.player.Name;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.participant.Money;
import domain.player.participant.Participant;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {

        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final CardTable cardTable = CardTable.readyToPlayBlackjack(cardDeck);

        final List<Participant> participants = createParticipants();
        final Dealer dealer = createDealer();
        final List<Player> players = createPlayers(participants, dealer);

        deal(cardTable, players);
        printStateAfterFirstDeal(participants, dealer);
        cardTable.matchAfterFirstDeal(participants, dealer);

        hittingPlayer(cardTable, participants, dealer);
        printStateAfterHit(participants, dealer);

        printPlayersMoneyBoard(cardTable, participants, dealer);
    }

    private static void deal(final CardTable cardTable, final List<Player> players) {
        dealCard(cardTable, players);
        dealCard(cardTable, players);
    }

    private static void dealCard(final CardTable cardTable, final List<Player> players) {
        players.forEach(cardTable::dealCardTo);
    }

    private static void printStateAfterFirstDeal(final List<Participant> participants, final Dealer dealer) {
        OutputView.showDealtCardTo(participants);
        OutputView.showStateOf(dealer);
        OutputView.showStateOf(participants);
    }

    private void hittingPlayer(final CardTable cardTable, final List<Participant> participants, final Dealer dealer) {
        hitForParticipants(cardTable, participants);
        hitForDealer(cardTable, dealer);
    }

    private void hitForDealer(final CardTable cardTable, final Dealer dealer) {
        do {
            OutputView.dealerOneMoreCard();
        } while (dealer.canHit() && cardTable.dealCardTo(dealer));
    }

    private void hitForParticipants(final CardTable cardTable, final List<Participant> participants) {
        participants.forEach(participant -> hitForParticipant(cardTable, participant));
    }

    private void hitForParticipant(final CardTable cardTable, final Participant participant) {
        while (participant.canHit() && selectHitOrStand(participant)) {
            cardTable.dealCardTo(participant);
            OutputView.showStateOf(participant);
        }
    }

    private static void printStateAfterHit(final List<Participant> participants, final Dealer dealer) {
        OutputView.showPlayerStateResult(dealer);
        OutputView.showParticipantsStateResult(participants);
    }

    private static void printPlayersMoneyBoard(final CardTable cardTable, final List<Participant> participants,
                                               final Dealer dealer) {
        printParticipantsMoneyBoard(cardTable, participants, dealer);
        printDealerMoneyBoard(cardTable, participants, dealer);
    }

    private static void printParticipantsMoneyBoard(final CardTable cardTable, final List<Participant> participants,
                                  final Dealer dealer) {
        final Map<Participant, Money> participantsResult = cardTable.determineParticipantsBettingMoney(participants, dealer);
        OutputView.showBettingMoneyBoard(participantsResult);
    }

    private static void printDealerMoneyBoard(final CardTable cardTable, final List<Participant> participants,
                                              final Dealer dealer) {
        final Money money = cardTable.determineDealerMoney(participants, dealer);
        OutputView.showDealerMoneyBoard(money);
    }

    private static List<Player> createPlayers(final List<Participant> participants, final Dealer dealer) {
        List<Player> players = new ArrayList<>(participants);
        players.add(dealer);

        return players;
    }

    private static Dealer createDealer() {
        return new Dealer();
    }

    private static List<Participant> createParticipants() {
        return InputView.readParticipantsName()
                        .stream()
                        .map(Name::new)
                        .map(name -> {
                            final int bettingMoney = InputView.readBettingMoney(name);
                            return new Participant(name, Money.wons(bettingMoney));
                        })
                        .collect(Collectors.toList());
    }

    private boolean selectHitOrStand(final Participant participant) {
        return InputView.readMoreCard(participant);
    }
}
