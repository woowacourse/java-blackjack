package blackjack.controller;

import blackjack.domain.BlackjackMachine;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.BetMoney;
import blackjack.domain.player.Choice;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;
import blackjack.domain.player.Guests;
import blackjack.domain.player.Money;
import blackjack.domain.result.DealerProfit;
import blackjack.domain.result.GuestProfit;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        final BlackjackMachine blackJackMachine = new BlackjackMachine(new CardDeck());
        final Dealer dealer = new Dealer();
        final Guests guests = createGuests();

        betGuestsMoney(guests);
        giveInitialCardsToPlayer(blackJackMachine, dealer, guests);
        askAndGiveCardsToGuests(blackJackMachine, guests);
        giveCardsToDealer(blackJackMachine, dealer);

        calculateTotalScore(dealer, guests);
        calculateTotalProfit(dealer, guests);
    }

    private Guests createGuests() {
        try {
            final List<String> names = InputView.getGuestNames();
            return new Guests(names);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createGuests();
        }
    }

    private void betGuestsMoney(final Guests participants) {
        for (Guest participant : participants) {
            participant.betMoney(getMoney(participant.getName()));
        }
    }

    private Money getMoney(final String name) {
        try {
            final BetMoney betMoney = new BetMoney(InputView.getMoney(name));
            return betMoney.getMoney();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getMoney(name);
        }
    }

    private void giveInitialCardsToPlayer(final BlackjackMachine blackJackMachine,
                                          final Dealer dealer, final Guests guests) {
        blackJackMachine.giveInitialCards(dealer, guests);
        OutputView.printInitialCards(dealer, guests);
    }

    private void askAndGiveCardsToGuests(final BlackjackMachine blackJackMachine, final Guests guests) {
        for (Guest guest : guests) {
            askAndGiveCardToGuest(blackJackMachine, guest);
        }
        OutputView.printNewLine();
    }

    private void askAndGiveCardToGuest(final BlackjackMachine blackJackMachine, final Guest guest) {
        Choice choice;
        do {
            choice = getChoice(guest);
            blackJackMachine.giveCardToGuest(guest, choice);
            OutputView.printPlayerCards(guest);
        } while (choice.isHit() && guest.canTakeCard());
    }

    private Choice getChoice(final Guest guest) {
        try {
            return InputView.getChoice(guest);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getChoice(guest);
        }
    }

    private void giveCardsToDealer(final BlackjackMachine blackJackMachine, final Dealer dealer) {
        while (dealer.canTakeCard()) {
            blackJackMachine.giveCardToDealer(dealer);
            OutputView.printDealerGetCardMessage(dealer);
        }
        OutputView.printNewLine();
    }

    private void calculateTotalScore(final Dealer dealer, final Guests guests) {
        OutputView.printTotalScore(dealer, dealer.getTotalScore());
        for (Guest guest : guests) {
            OutputView.printTotalScore(guest, guest.getTotalScore());
        }
    }

    private void calculateTotalProfit(final Dealer dealer, final Guests guests) {
        final GuestProfit guestProfit = new GuestProfit(dealer, guests);
        final DealerProfit dealerProfit = new DealerProfit(guestProfit);
        OutputView.printTotalProfit(guestProfit.getProfits(), dealer.getName(), dealerProfit.getProfit());
    }
}
