package blackjackgame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjackgame.domain.Judge;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Name;
import blackjackgame.domain.player.Player;
import blackjackgame.domain.player.Players;
import blackjackgame.view.DrawRequest;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public final class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Deck deck = new Deck();
        final Players players = initializeGuests(deck);
        players.add(Dealer.from(deck.firstPickCards()));
        outputView.printStartingCards(players.startingCards());

        players.guests().forEach(guest -> hitGuestCard(guest, deck));
        hitDealerCard(players.dealer(), deck);

        printScore(players);
        outputView.printProfit(Judge.profit(players.dealer(), players.guests()));
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

    private void hitGuestCard(final Guest guest, final Deck deck) {
        DrawRequest drawRequest = DrawRequest.YES;
        while (guest.canHit() && drawRequest == DrawRequest.YES) {
            drawRequest = inputView.readWantMoreCard(guest.getName());
            if (drawRequest == DrawRequest.YES) {
                guest.draw(deck.pickOne());
                outputView.printCards(guest.getName(), guest.cards());
            } else {
                guest.setStay();
            }
        }
    }

    private void hitDealerCard(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            dealer.draw(deck.pickOne());
            outputView.dealerAddCard();
        }
    }

    private void printScore(final Players players) {
        for (final Player player : players.getAllPlayers()) {
            outputView.printCards(player.getName(), player.cards());
            outputView.printScore(player.scoreValue());
        }
    }
}
