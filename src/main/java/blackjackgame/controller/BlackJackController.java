package blackjackgame.controller;

import java.util.*;

import blackjackgame.domain.*;
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
        final Dealer dealer = new Dealer(new Hand(deck.pickOne(), deck.pickOne()));
        Map<Guest, BettingMoney> guestBettingMoney = askGuestsBettingMoney(guests);
        printFirstHand(guests, dealer);

        askGuestsHitCard(guests.getGuests(), deck);
        askDealerHitCard(dealer, deck);

        printPlayersCardScore(guests, dealer);
        printBettingResult(guests, dealer, guestBettingMoney);
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

    private Map<Guest, BettingMoney> askGuestsBettingMoney(final Guests guests) {
        Map<Guest, BettingMoney> guestBettingMoneyMap = new LinkedHashMap<>();
        for (Guest guest : guests.getGuests()) {
            askGuestBettingMoney(guestBettingMoneyMap, guest);
        }
        return guestBettingMoneyMap;
    }

    private void askGuestBettingMoney(Map<Guest, BettingMoney> guestBettingMoneyMap, Guest guest) {
        try {
            BettingMoney bettingMoney = BettingMoney.of(inputView.readBettingMoney(guest.getName()));
            guestBettingMoneyMap.put(guest, bettingMoney);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMsg(e.getMessage());
            askGuestBettingMoney(guestBettingMoneyMap, guest);
        }
    }

    private void printFirstHand(final Guests guests, final Dealer dealer) {
        outputView.printFirstDealerCards(dealer.getName(), BlackJackGameDataAssembler.assembleCardDto(dealer.getHand()));
        outputView.printLineSeparator();
        for (final Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), BlackJackGameDataAssembler.assembleCardDto(guest.getHand()));
            outputView.printLineSeparator();
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
        outputView.printCards(guest.getName(), BlackJackGameDataAssembler.assembleCardDto(guest.getHand()));
    }

    private void askDealerHitCard(final Dealer dealer, final Deck deck) {
        outputView.printLineSeparator();
        while (dealer.canHit()) {
            dealer.addCard(deck.pickOne());
            outputView.dealerAddCard();
        }
    }

    private void printPlayersCardScore(final Guests guests, final Dealer dealer) {
        outputView.printLineSeparator();
        outputView.printCards(dealer.getName(), BlackJackGameDataAssembler.assembleCardDto(dealer.getHand()));
        outputView.printScore(dealer.getScore());

        for (final Guest guest : guests.getGuests()) {
            outputView.printLineSeparator();
            outputView.printCards(guest.getName(), BlackJackGameDataAssembler.assembleCardDto(guest.getHand()));
            outputView.printScore(guest.getScore());
        }
    }

    private void printBettingResult(final Guests guests, final Dealer dealer, final Map<Guest, BettingMoney> guestBettingMoney) {
        Result result = new Result(dealer, guests.getGuests());
        Map<Guest, GameOutcome> guestGameOutcome = result.getGuestsResult();
        Map<String, Integer> bettingResult = new LinkedHashMap<>();
        int dealerRevenue = 0;
        bettingResult.put(dealer.getName(), dealerRevenue);
        for(Guest guest : guestGameOutcome.keySet()) {
            int guestRevenue = guestBettingMoney.get(guest).getRevenue(guestGameOutcome.get(guest));
            bettingResult.put(guest.getName(), guestRevenue);
            dealerRevenue -= guestRevenue;
        }
        bettingResult.put(dealer.getName(), dealerRevenue);
        outputView.printBettingResult(bettingResult);
    }
}
