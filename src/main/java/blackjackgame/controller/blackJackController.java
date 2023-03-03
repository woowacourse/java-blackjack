package blackjackgame.controller;

import java.util.List;

import blackjackgame.domain.Result;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Guests;
import blackjackgame.view.AddCardResponse;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class blackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public blackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Guests guests = generateGuests();
        final Dealer dealer = new Dealer();
        final Deck deck = new Deck();
        setGame(guests, dealer, deck);

        askGuestsHitCard(guests.getGuests(), deck);
        askDealerHitCard(dealer, deck);

        printPlayersCardScore(guests, dealer);
        printGameResult(guests, dealer);
    }

    private Guests generateGuests() {
        try {
            List<String> guestNames = inputView.readGuestsName();
            return new Guests(guestNames);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMsg(e.getMessage());
            return generateGuests();
        }
    }

    private void setGame(final Guests guests, final Dealer dealer, final Deck deck) {
        deck.initPlayersCards(guests, dealer);
        outputView.printFirstDealerCards(dealer.getName(), dealer.getCards());
        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), guest.getCards());
        }
    }

    private void askGuestsHitCard(final List<Guest> guests, final Deck deck) {
        for (Guest guest : guests) {
            askGuestHitCardRepeat(deck, guest);
        }
    }

    private void askGuestHitCardRepeat(final Deck deck, final Guest guest) {
        AddCardResponse addCardResponse = AddCardResponse.YES;
        while (guest.canHit() && addCardResponse == AddCardResponse.YES) {
            addCardResponse = inputView.readWantMoreCard(guest.getName());
            askGuestHitCard(deck, guest, addCardResponse);
        }
    }

    private void askGuestHitCard(final Deck deck, final Guest guest,
        final AddCardResponse addCardResponse) {
        if (addCardResponse == AddCardResponse.YES) {
            deck.distributeCard(guest);
        }
        outputView.printCards(guest.getName(), guest.getCards());
    }

    private void askDealerHitCard(final Dealer dealer, final Deck deck) {
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

    private void printGameResult(final Guests guests, final Dealer dealer) {
        Result result = new Result(dealer, guests.getGuests());
        outputView.printResult(result.getDealerResult(), result.getGuestsResult());
    }
}
