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
        final Participants participants = getParticipantsAndBattingMoney();
        final Dealer dealer = participants.getDealer();

        proceed(deck, participants, dealer);

        OutputView.printScoreBoard(participants);
        OutputView.printGameResult(participants);
    }

    private Participants getParticipantsAndBattingMoney() {
        List<Player> players = InputView.getPlayersName().stream()
                .map(playerName -> Player.from(playerName, InputView.getBattingMoney(playerName)))
                .collect(Collectors.toList());
        return Participants.from(players);
    }

    private void proceed(Deck deck, Participants participants, Dealer dealer) {
        divideFirstCard(deck, participants);
        OutputView.printFirstCardStatus(participants);
        divideCard(deck, participants);
        divideCardForDealer(deck, dealer);
    }

    private void divideCard(Deck deck, Participants participants) {
        for (Player player : participants.getPlayers()) {
            receiveCardForPlayer(deck, player);
        }
    }

    private void divideCardForDealer(Deck deck, Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            OutputView.printReceiveCardForDealer();
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

}
