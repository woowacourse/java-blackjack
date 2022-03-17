package blackjack.controller;

import static blackjack.constant.Command.HIT;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.RevenueResultResponse;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private static final String COMMA_DELIMINATOR = ",";

    private BlackJackController() {
    }

    public static List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        String participantsNames = InputView.inputParticipantsNames();
        for (String participantName : participantsNames.split(COMMA_DELIMINATOR)) {
            players.add(new Player(participantName));
        }
        return players;
    }

    public static List<BettingMoney> receiveBettingMoney(List<Player> players) {
        return InputView.inputBettingMoney(players);
    }

    public static void initiateParticipantsHand(Dealer dealer, List<Player> players, Deck deck) {
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        for (Player player : players) {
            player.receiveCard(deck.draw());
            player.receiveCard(deck.draw());
        }
    }

    public static void printParticipantsHand(Dealer dealer, List<Player> players) {
        OutputView.printInitialParticipantsHand(dealer, players);
    }

    public static void takeMoreCard(List<Player> players, Dealer dealer, Deck deck) {
        takeMoreCardPlayerTurnForAllPlayers(players, deck);
        takeOneMoreCardDuringDealerTurn(dealer, deck);
        OutputView.printParticipantCardsAndScore(dealer, players);
    }

    public static void takeMoreCardPlayerTurnForAllPlayers(List<Player> players, Deck deck) {
        for (Player player : players) {
            takeMoreCardPlayerTurnForPlayer(player, deck);
        }
    }

    public static void takeMoreCardPlayerTurnForPlayer(Player player, Deck deck) {
        while (player.canReceive() && InputView.inputOneMoreCard(player) == HIT) {
            player.receiveCard(deck.draw());
            OutputView.showPlayerHand(player);
        }
    }

    public static void takeOneMoreCardDuringDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerOneMoreCard();
            dealer.receiveCard(deck.draw());
        }
    }

    public static void printRevenueResultResponse(RevenueResultResponse revenueResultResponse) {
        OutputView.printRevenueResultResponse(revenueResultResponse);
    }
}
