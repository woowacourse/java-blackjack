package controller;

import model.card.Deck;
import model.user.Dealer;
import model.user.Participants;
import model.user.Player;
import ui.input.InputView;
import ui.output.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void start() {
        final Deck deck = new Deck();
        final Participants participants = getParticipants();
        final Dealer dealer = participants.getDealer();

        divideFirstCard(deck, participants);
        OutputView.printFirstCardStatus(participants);
        divideCard(deck, participants);
        divideCardForDealer(deck, dealer);

        OutputView.printScoreBoard(participants);
        OutputView.printResult(participants, dealer.getTotalValue());
    }

    private void divideCardForDealer(Deck deck, Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            OutputView.printReceiveCardForDealer();
        }
    }

    private void divideCard(Deck deck, Participants participants) {
        for (Player player : participants.getPlayers()) {
            receiveCardForPlayer(deck, player);
        }
    }

    private void receiveCardForPlayer(Deck deck, Player player) {
        while (canReceiveCard(player)) {
            player.receiveCard(deck.pick());
            OutputView.printPlayerCardStatus(player);
        }
    }

    private boolean canReceiveCard(Player player) {
        return player.canReceiveCard() && isInputEqualsReceiveCardCommand(player);
    }

    private boolean isInputEqualsReceiveCardCommand(Player player) {
        return InputView.getPlayerInputGetMoreCard(player.getName());
    }


    private void divideFirstCard(Deck deck, Participants participants) {
        OutputView.printDivideTwoCard(participants.getPlayers());
        participants.receiveInitialCards(deck);
    }

    private Participants getParticipants() {
        List<Player> players = InputView.getPlayersName().stream()
                .map(playerName -> Player.from(playerName, InputView.getBatingMoney(playerName)))
                .collect(Collectors.toList());
        return Participants.from(players);
    }

}
