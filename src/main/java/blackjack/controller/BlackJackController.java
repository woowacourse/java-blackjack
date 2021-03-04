package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.Response;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Collections;
import java.util.List;

public class BlackJackController {

    public static void play() {
        Deck deck = prepareDeck();
        Dealer dealer = new Dealer(deck);
        Players players = joinPlayers(deck);

        printGameStatus(dealer, players);

        preparePlayers(deck, players);
        prepareDealer(deck, dealer);

        printGameResult(dealer, players);
    }

    private static Deck prepareDeck() {
        List<Card> cards = Card.getAllCards();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static Players joinPlayers(Deck deck) {
        OutputView.printInputNames();
        return Players.valueOf(InputView.inputString(), deck);
    }

    private static void printGameStatus(Dealer dealer, Players players) {
        OutputView.printGameInitializeMessage(dealer, players, Participant.STARTING_CARD_COUNT);
        OutputView.printParticipantsStatus(dealer, players, false);
    }

    private static void printGameResult(Dealer dealer, Players players) {
        OutputView.printParticipantsStatus(dealer, players, true);
        OutputView.printResult(players.match(dealer));
    }

    private static void preparePlayers(Deck deck, Players players) {
        while (!players.isPrepared()) {
            preparePlayer(deck, players.nextPlayerToPrepare());
        }
    }

    private static void preparePlayer(Deck deck, Player playerToPrepare) {
        while (playerToPrepare.isContinue()) {
            OutputView.willDrawCard(playerToPrepare);
            Response response = Response.getResponse(InputView.inputString());
            playerToPrepare.willContinue(response, deck);
            printParticipantStatusByResponse(playerToPrepare, response);
        }
    }

    private static void printParticipantStatusByResponse(Player playerToPrepare,
        Response response) {
        if (response == Response.POSITIVE) {
            OutputView.printParticipantStatus(playerToPrepare, false);
        }
    }

    private static void prepareDealer(Deck deck, Dealer dealer) {
        while (dealer.isContinue()) {
            dealer.drawCard(deck);
            OutputView.printDealerDrawCard(dealer);
        }
    }

    public static void main(String[] args) {
        BlackJackController.play();
    }

}
