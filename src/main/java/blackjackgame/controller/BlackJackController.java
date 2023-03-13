package blackjackgame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjackgame.domain.card.Deck;
import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Name;
import blackjackgame.domain.player.Player;
import blackjackgame.domain.player.Players;
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
        final Dealer dealer = Dealer.from(deck.firstPickCards());
        final Players players = initializeGuests(deck);
        players.add(dealer);

        outputView.printStartingCards(players.startingCards());

        hitGuestsCard(players.guests(), deck);
        hitDealerCard(players.dealer(), deck);
        printScore(players);
    }

    private Players initializeGuests(Deck deck) {
        Players guests = null;
        do {
            try {
                guests = generateGuests(deck);
            } catch (IllegalArgumentException e) {
                inputView.printErrorMsg(e.getMessage());
            }
        } while (guests == null);
        return guests;
    }

    private Players generateGuests(Deck deck) {
        List<String> guestNames = inputView.readGuestsName();
        List<Integer> moneys = guestNames.stream()
            .map(inputView::readBettingMoney)
            .collect(Collectors.toList());

        List<Name> names = guestNames.stream()
            .map(Name::new)
            .collect(Collectors.toList());

        List<Player> guests = new ArrayList<>();

        for (int i = 0; i < names.size(); i++) {
            Guest guest = Guest.of(deck.firstPickCards(), names.get(i), moneys.get(i));
            guests.add(guest);
        }
        return new Players(guests);
    }

    private void hitGuestsCard(final List<Guest> guests, final Deck deck) {
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
            guest.draw(deck.pickOne());
            outputView.printCards(guest.getName(), guest.cards());
        } else {
            guest.stay();
        }
    }

    private void hitDealerCard(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            dealer.draw(deck.pickOne());
            outputView.dealerAddCard();
        }
    }

    private void printScore(Players players) {
        for (final Player player : players.getAllPlayers()) {
            outputView.printCards(player.getName(), player.cards());
            outputView.printScore(player.scoreValue());
        }
    }

    private void judgeAndPrintResult(final Guests guests, final Dealer dealer) {
        Judge judge = new Judge(dealer, guests);
        Result result = new Result(judge.guestsResult());
        result.generateDealer();

        outputView.printResult(ResultDto.from(result));
    }
}
