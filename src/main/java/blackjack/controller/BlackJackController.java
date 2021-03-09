package blackjack.controller;

import blackjack.domain.Response;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.Deck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.dto.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private static final Deck DECK = new Deck(Card.getAllCards());

    private BlackJackController() {
    }

    public static void play() {
        DECK.shuffle();
        Dealer dealer = new Dealer(DECK);
        Players players = joinPlayers();

        printGameStatus(dealer, players);

        preparePlayers(players);
        prepareDealer(dealer);

        printGameResult(dealer, players);
    }

    private static Players joinPlayers() {
        OutputView.printInputNames();
        return Players.valueOf(InputView.inputString(), DECK);
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

    private static void preparePlayers(Players players) {
        while (players.isNotPrepared()) {
            preparePlayer(players.nextPlayerToPrepare());
        }
    }

    private static void preparePlayer(Player playerToPrepare) {
        while (playerToPrepare.isContinue()) {
            OutputView.willDrawCard(playerToPrepare);
            Response response = Response.getResponse(InputView.inputString());
            playerToPrepare.updateStatusByResponse(response);
            drawCardByResponse(playerToPrepare, response);
        }
    }

    private static void drawCardByResponse(Player playerToPrepare,
        Response response) {
        if (response == Response.POSITIVE) {
            playerToPrepare.draw(DECK);
            OutputView.printParticipantStatus(playerToPrepare, false);
        }
    }

    private static void prepareDealer(Dealer dealer) {
        while (dealer.isContinue()) {
            dealer.draw(DECK);
            OutputView.printDealerDrawCard(dealer);
        }
        OutputView.printNewLine();
    }

    public static void main(String[] args) {
        BlackJackController.play();
    }

}
