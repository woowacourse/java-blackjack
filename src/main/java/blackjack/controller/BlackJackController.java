package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.Response;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.dto.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private BlackJackController() {
    }

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
        Deck deck = new Deck(Card.getAllCards());
        deck.shuffle();
        return deck;
    }

    private static Players joinPlayers(Deck deck) {
        OutputView.printInputNames();
        return Players.valueOf(InputView.inputString(), deck);
    }

    private static void printGameStatus(Dealer dealer, Players players) {
        OutputView.printGameInitializeMessage(Participants.valueOf(dealer, players),
            Participant.STARTING_CARD_COUNT);
        OutputView.printParticipantsStatus(Participants.valueOf(dealer, players), false);
    }

    private static void printGameResult(Dealer dealer, Players players) {
        OutputView.printParticipantsStatus(Participants.valueOf(dealer, players), true);
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
            playerToPrepare.willContinue(response);
            drawCardByResponse(playerToPrepare, response, deck);
        }
    }

    private static void drawCardByResponse(Player playerToPrepare,
        Response response, Deck deck) {
        if (response == Response.POSITIVE) {
            playerToPrepare.drawCard(deck);
            OutputView.printParticipantStatus(playerToPrepare, false);
        }
    }

    private static void prepareDealer(Deck deck, Dealer dealer) {
        while (dealer.isContinue()) {
            dealer.drawCard(deck);
            OutputView.printDealerDrawCard(dealer);
        }
        OutputView.printNewLine();
    }

    public static void main(String[] args) {
        BlackJackController.play();
    }

}
