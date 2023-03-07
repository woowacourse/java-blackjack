package blackjackgame.controller;

import java.util.List;

import blackjackgame.domain.Judge;
import blackjackgame.domain.Result;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Guests;
import blackjackgame.view.AddCardResponse;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Guests guests = generateGuests();
        final Dealer dealer = new Dealer();
        final Deck deck = new Deck();

        deck.initializePlayersCards(guests, dealer);
        printFirstCards(guests, dealer);

        askGuestsHitCard(guests.getGuests(), deck);
        askDealerHitCardRepeat(dealer, deck);

        printPlayersCardScore(guests, dealer);
        judgeAndPrintResult(guests, dealer);
    }

    private Guests generateGuests() {
        Guests guests = null;
        do {
            List<String> guestNames = inputView.readGuestsName();
            try {
                guests = new Guests(guestNames);
            } catch (IllegalArgumentException e) {
                inputView.printErrorMsg(e.getMessage());
            }
        } while (guests == null);
        return guests;
    }

    private void printFirstCards(final Guests guests, final Dealer dealer) {
        outputView.printFirstDealerCards(dealer.getName(), dealer.getCards());
        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), guest.getCards());
        }
    }

    private void askGuestsHitCard(final List<Guest> guests, final Deck deck) {
        for (Guest guest : guests) {
            AddCardResponse addCardResponse = AddCardResponse.YES;
            while (guest.canHit() && addCardResponse == AddCardResponse.YES) {
                addCardResponse = inputView.readWantMoreCard(guest.getName());
                hitAndPrintCards(deck, guest, addCardResponse);
            }
        }
    }

    private void hitAndPrintCards(Deck deck, Guest guest, AddCardResponse addCardResponse) {
        if (addCardResponse == AddCardResponse.YES) {
            deck.distributeCard(guest);
            outputView.printCards(guest.getName(), guest.getCards());
        }
    }

    private void askDealerHitCardRepeat(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            deck.distributeCard(dealer);
            outputView.dealerAddCard();
        }
    }

    private void printPlayersCardScore(final Guests guests, final Dealer dealer) {
        outputView.printCards(dealer.getName(), dealer.getCards());
        outputView.printScore(dealer.getScore());

        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), guest.getCards());
            outputView.printScore(guest.getScore());
        }
    }

    private void judgeAndPrintResult(final Guests guests, final Dealer dealer) {
        Judge judge = new Judge(dealer, guests);
        Result result = new Result(judge.guestsResult());
        result.generateDealer();
        outputView.printResult(result.getDealer(), result.getGuests());
    }
}
