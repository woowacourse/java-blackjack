package blackjack.controller;

import blackjack.domain.CardDistributor;
import blackjack.domain.Response;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.Deck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private static final CardDistributor CARD_DISTRIBUTOR = makeCardDistributor();

    private BlackJackController() {
    }

    public static void play() {
        Dealer dealer = new Dealer();
        Players players = joinPlayers();
        Participants participants = Participants.valueOf(dealer, players);

        initializeGame(participants);
        prepare(dealer, players);

        printGameResult(dealer, players);
    }

    private static void prepare(Dealer dealer, Players players) {
        preparePlayers(players);
        prepareDealer(dealer);
    }

    private static CardDistributor makeCardDistributor() {
        Deck deck = new Deck(Card.getAllCards());
        deck.shuffle();
        return new CardDistributor(deck);
    }

    private static Players joinPlayers() {
        OutputView.printInputNames();
        return Players.valueOf(InputView.inputString());
    }

    private static void initializeGame(Participants participants) {
        CARD_DISTRIBUTOR.distributeStartingCardsTo(participants);
        printGameStatus(participants);
    }

    private static void printGameStatus(Participants participants) {
        OutputView.printGameInitializeMessage(participants, Participant.STARTING_CARD_COUNT);
        OutputView.printParticipantsStatus(participants, false);
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

    private static void drawCardByResponse(Player playerToPrepare, Response response) {
        if (response == Response.POSITIVE) {
            CARD_DISTRIBUTOR.distributeCardTo(playerToPrepare);
            OutputView.printParticipantStatus(playerToPrepare, false);
        }
    }

    private static void prepareDealer(Dealer dealer) {
        while (dealer.isContinue()) {
            CARD_DISTRIBUTOR.distributeCardTo(dealer);
            OutputView.printDealerDrawCard(dealer);
        }
        OutputView.printNewLine();
    }

    public static void main(String[] args) {
        BlackJackController.play();
    }

}
