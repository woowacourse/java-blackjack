package blackjackgame.controller;

import java.util.List;
import java.util.stream.IntStream;

import blackjackgame.domain.Dealer;
import blackjackgame.domain.Deck;
import blackjackgame.domain.Guest;
import blackjackgame.domain.Guests;
import blackjackgame.domain.Result;
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
        initGame(guests, dealer, deck);

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

    private void initGame(final Guests guests, final Dealer dealer, final Deck deck) {
        IntStream.range(0, 2).forEach(
                count -> {
                    dealer.addCard(deck.pickOne());
                    guests.getGuests().forEach(guest -> guest.addCard(deck.pickOne()));
                });
        outputView.printFirstDealerCards(dealer.getName(), DataTransformController.transformCards(dealer.getCards()));
        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), DataTransformController.transformCards(guest.getCards()));
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
            guest.addCard(deck.pickOne());
        }
        outputView.printCards(guest.getName(), DataTransformController.transformCards(guest.getCards()));
    }

    private void askDealerHitCard(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            dealer.addCard(deck.pickOne());
            outputView.dealerAddCard();
        }
    }

    private void printPlayersCardScore(final Guests guests, final Dealer dealer) {
        outputView.printCards(dealer.getName(), DataTransformController.transformCards(dealer.getCards()));
        outputView.printScore(dealer.getScore());

        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), DataTransformController.transformCards(guest.getCards()));
            outputView.printScore(guest.getScore());
        }
    }

    private void printGameResult(final Guests guests, final Dealer dealer) {
        Result result = new Result(dealer, guests.getGuests());
        outputView.printResult(DataTransformController.transformDealerResult(result.getDealerResult()),
                DataTransformController.transformGuestsResult(result.getGuestsResult()));
    }
}
