package blackjackgame.controller;

import java.util.List;

import blackjackgame.domain.CardMachine;
import blackjackgame.domain.Dealer;
import blackjackgame.domain.Deck;
import blackjackgame.domain.Guest;
import blackjackgame.domain.Guests;
import blackjackgame.domain.Result;
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
        final CardMachine cardMachine = new CardMachine(new Deck());
        setGame(guests, dealer, cardMachine);

        askGuestsHitCard(guests.getGuests(), cardMachine);
        askDealerHitCard(dealer, cardMachine);

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

    private void setGame(final Guests guests, final Dealer dealer, final CardMachine cardMachine) {
        cardMachine.initPlayersCards(guests, dealer);
        outputView.printFirstDealerCards(dealer.getName(), dealer.getCards());
        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), guest.getCards());
        }
    }

    private void askGuestsHitCard(final List<Guest> guests, final CardMachine cardMachine) {
        for (Guest guest : guests) {
            askGuestHitCardRepeat(cardMachine, guest);
        }
    }

    private void askGuestHitCardRepeat(final CardMachine cardMachine, final Guest guest) {
        AddCardResponse addCardResponse = AddCardResponse.YES;
        while (guest.canHit() && addCardResponse == AddCardResponse.YES) {
            addCardResponse = inputView.readWantMoreCard(guest.getName());
            askGuestHitCard(cardMachine, guest, addCardResponse);
        }
    }

    private void askGuestHitCard(final CardMachine cardMachine, final Guest guest,
        final AddCardResponse addCardResponse) {
        if (addCardResponse == AddCardResponse.YES) {
            cardMachine.distributeCard(guest);
        }
        outputView.printCards(guest.getName(), guest.getCards());
    }

    private void askDealerHitCard(final Dealer dealer, final CardMachine cardMachine) {
        while (dealer.canHit()) {
            cardMachine.distributeCard(dealer);
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
