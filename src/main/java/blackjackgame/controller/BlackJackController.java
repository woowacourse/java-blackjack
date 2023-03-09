package blackjackgame.controller;

import java.util.List;

import blackjackgame.domain.Dealer;
import blackjackgame.domain.Deck;
import blackjackgame.domain.Guest;
import blackjackgame.domain.Guests;
import blackjackgame.domain.Result;
import blackjackgame.view.AddCardRequest;
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
        final Deck deck = new Deck();
        final Guests guests = generateGuests(deck);
        final Dealer dealer = new Dealer(deck.pickOne(), deck.pickOne());
        printFirstHand(guests, dealer);

        askGuestsHitCard(guests.getGuests(), deck);
        askDealerHitCard(dealer, deck);

        printPlayersCardScore(guests, dealer);
        printGameResult(guests, dealer);
    }

    private Guests generateGuests(final Deck deck) {
        try {
            List<String> guestNames = inputView.readGuestsName();
            return new Guests(guestNames, deck);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMsg(e.getMessage());
            return generateGuests(deck);
        }
    }

    private void printFirstHand(final Guests guests, final Dealer dealer) {
        outputView.printFirstDealerCards(dealer.getName(), BlackJackGameDataAssembler.assembleCardDto(dealer.getCards()));
        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), BlackJackGameDataAssembler.assembleCardDto(guest.getCards()));
        }
    }

    private void askGuestsHitCard(final List<Guest> guests, final Deck deck) {
        for (Guest guest : guests) {
            askGuestHitCardRepeat(deck, guest);
        }
    }

    private void askGuestHitCardRepeat(final Deck deck, final Guest guest) {
        AddCardRequest addCardRequest = AddCardRequest.YES;
        while (guest.canHit() && addCardRequest == AddCardRequest.YES) {
            addCardRequest = inputView.readWantMoreCard(guest.getName());
            askGuestHitCard(deck, guest, addCardRequest);
        }
    }

    private void askGuestHitCard(final Deck deck, final Guest guest,
                                 final AddCardRequest addCardRequest) {
        if (addCardRequest == AddCardRequest.YES) {
            guest.addCard(deck.pickOne());
        }
        outputView.printCards(guest.getName(), BlackJackGameDataAssembler.assembleCardDto(guest.getCards()));
    }

    private void askDealerHitCard(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            dealer.addCard(deck.pickOne());
            outputView.dealerAddCard();
        }
    }

    private void printPlayersCardScore(final Guests guests, final Dealer dealer) {
        outputView.printCards(dealer.getName(), BlackJackGameDataAssembler.assembleCardDto(dealer.getCards()));
        outputView.printScore(dealer.getScore());

        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), BlackJackGameDataAssembler.assembleCardDto(guest.getCards()));
            outputView.printScore(guest.getScore());
        }
    }

    private void printGameResult(final Guests guests, final Dealer dealer) {
        Result result = new Result(dealer, guests.getGuests());
        outputView.printResult(BlackJackGameDataAssembler.assembleDealerResultDto(result.getDealerResult()),
                BlackJackGameDataAssembler.assembleGuestsResultDto(result.getGuestsResult()));
    }
}
