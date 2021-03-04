package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.Response;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Collections;
import java.util.List;

public class BlackJackController {

    public static void play() {
        OutputView.printInputNames();
        List<Card> cards = Card.getAllCards();
        Collections.shuffle(cards);
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);
        Players players = Players.valueOf(InputView.inputString(), deck);

        OutputView.printGameInitializeMessage(dealer, players, Participant.STARTING_CARD_COUNT);
        while (!players.isPrepared()) {
            Player playerToPrepare = players.nextPlayerToPrepare();
            while (playerToPrepare.isContinue()) {
                OutputView.willDrawCard(playerToPrepare);
                Response response = Response.getResponse(InputView.inputString());
                playerToPrepare.willContinue(response, deck);
                if (response == Response.POSITIVE) {
                    OutputView.printParticipantStatus(playerToPrepare, false);
                }
            }
        }

        while (dealer.isContinue()) {
            dealer.drawCard(deck);
            OutputView.printDealerDrawCard(dealer);
        }

        OutputView.printParticipantsStatus(dealer, players);
        OutputView.printResult(players.match(dealer));
    }

    public static void main(String[] args) {
        BlackJackController.play();
    }

}
