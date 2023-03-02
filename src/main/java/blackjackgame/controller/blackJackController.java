package blackjackgame.controller;

import java.util.List;

import blackjackgame.domain.CardMachine;
import blackjackgame.domain.Cards;
import blackjackgame.domain.Dealer;
import blackjackgame.domain.Guest;
import blackjackgame.domain.Guests;
import blackjackgame.view.AddCardResponse;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class blackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public blackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> guestNames = inputView.readGuestsName();
        Guests guests = new Guests(guestNames);
        Dealer dealer = new Dealer();
        CardMachine cardMachine = new CardMachine(new Cards());
        cardMachine.initPlayersCards(guests, dealer);
        outputView.printCards(dealer.getName(), dealer.getCards());
        for (Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), guest.getCards());
        }

        for (Guest guest : guests.getGuests()) {
            AddCardResponse addCardResponse = AddCardResponse.YES;
            while (guest.isPick() && addCardResponse == AddCardResponse.YES) {
                addCardResponse = inputView.readWantMoreCard(guest.getName());
                if (addCardResponse == AddCardResponse.YES) {
                    cardMachine.giveCard(guest);
                }
                outputView.printCards(guest.getName(), guest.getCards());
            }
        }

        while (dealer.isPick()) {
            cardMachine.giveCard(dealer);
            outputView.dealerAddCard();
        }

        outputView.printCards(dealer.getName(), dealer.getCards());
        outputView.printScore(dealer.getScore());

        for (Guest guest : guests.getGuests()) {
            outputView.printCards(guest.getName(), guest.getCards());
            outputView.printScore(guest.getScore());
        }
    }
}
