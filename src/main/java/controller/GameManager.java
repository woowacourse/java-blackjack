package controller;

import java.util.List;
import java.util.stream.Collectors;
import model.participants.Dealer;
import model.participants.HitOption;
import model.participants.Player;
import model.participants.Players;
import model.rounds.Actors;
import model.rounds.Outcome;
import view.Input;
import view.Output;

public class GameManager {
    private final Input input;
    private final Output output;

    public GameManager(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void runBlackjack() {
        Actors actors = inviteActors();
        Dealer dealer = actors.getDealer();
        Players players = actors.getPlayers();

        revealHands(dealer, players);
        startRound(dealer, players);
        revealFinalHands(dealer, players);

        displayFinalOutcome(dealer, players);
    }

    private Actors inviteActors() {
        Actors actors = new Actors();
        actors.inviteDealer();

        List<String> playerNames = input.readPlayerNames();
        List<Integer> playerWagers = playerNames.stream()
                .map(input::readWager)
                .collect(Collectors.toList());

        actors.invitePlayers(actors.getDealer(), playerNames, playerWagers);
        return actors;
    }

    private void revealHands(Dealer dealer, Players players) {
        output.printHandDistribution(players.members());
        output.printDealerHand(dealer.getHandCards());

        for (Player player : players.members()) {
            output.printParticipantHand(player.getHandCards(), player.getName());
        }
    }

    private void startRound(Dealer dealer, Players players) {
        for (Player player : players.members()) {
            processPlayerHit(player, dealer);
        }
        processDealerHit(dealer);
    }

    private void processPlayerHit(Player player, Dealer dealer) {
        while (player.canHit() && !HitOption.isNo(input.readHitOption(player.getName()))) {
            player.addCardToHand(dealer.pullCard());
            output.printParticipantHand(player.getHandCards(), player.getName());
            output.printPlayerBustNotice(player.isBust(), player.getName());
        }
    }

    private void processDealerHit(Dealer dealer) {
        while (dealer.canHit()) {
            dealer.addCardToHand(dealer.pullCard());
            output.printDealerHitNotice();
        }
    }

    private void revealFinalHands(Dealer dealer, Players players) {
        output.printFinalHand(dealer.getHandCards(), dealer.getHandScore());
        for (Player player : players.members()) {
            output.printFinalHand(player.getHandCards(), player.getHandScore(), player.getName());
        }
    }

    private void displayFinalOutcome(final Dealer dealer, final Players players) {
        Outcome outcome = new Outcome();
        outcome.checkFinalOutcome(dealer, players);
        output.printProfits(dealer, players);
    }
}
