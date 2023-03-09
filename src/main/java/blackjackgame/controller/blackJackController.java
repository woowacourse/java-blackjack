package blackjackgame.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjackgame.domain.Judge;
import blackjackgame.domain.Result;
import blackjackgame.domain.ResultDto;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Guests;
import blackjackgame.domain.player.Name;
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
        final Deck deck = new Deck();
        final Guests guests = generateGuests(deck);
        final Dealer dealer = new Dealer(deck.firstPickCards());

        printFirstCards(guests, dealer);

        askGuestsHitCard(guests.getGuests(), deck);
        askDealerHitCardRepeat(dealer, deck);

        printPlayersCardScore(guests, dealer);
        judgeAndPrintResult(guests, dealer);
    }

    private Guests generateGuests(Deck deck) {
        Guests guests = null;
        do {
            try {
                guests = getGuests(deck);
            } catch (IllegalArgumentException e) {
                inputView.printErrorMsg(e.getMessage());
            }
        } while (guests == null);
        return guests;
    }

    private Guests getGuests(Deck deck) {
        List<String> guestNames = inputView.readGuestsName();

        List<Name> names = guestNames.stream()
            .map(Name::new)
            .collect(Collectors.toList());

        List<Guest> guests = names.stream()
            .map(name -> new Guest(name, deck.firstPickCards()))
            .collect(Collectors.toList());
        return new Guests(guests);
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
            guest.addCard(deck.pickOne());
            outputView.printCards(guest.getName(), guest.getCards());
        }
    }

    private void askDealerHitCardRepeat(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            dealer.addCard(deck.pickOne());
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

        outputView.printResult(ResultDto.from(result));
    }
}
