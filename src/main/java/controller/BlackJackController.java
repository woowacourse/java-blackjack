package controller;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import model.card.Deck;
import model.money.Bet;
import model.user.Dealer;
import model.user.Name;
import model.user.Participants;
import model.user.Player;
import ui.input.InputView;
import ui.input.ReceiveCommand;
import ui.output.BlackJackGameResponse;
import ui.output.HandResponse;
import ui.output.OutputView;
import ui.output.BetResponse;
import ui.output.UserResponse;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void init(final Deck deck) {
        final Dealer dealer = new Dealer();

        final Participants participants = getParticipants(dealer);

        distributeCards(deck, dealer, participants);
        printResult(dealer, participants);
    }

    private Participants getParticipants(final Dealer dealer) {
        final List<Name> playerNames = retryBy(this::getPlayerNames);
        final List<Bet> bets = retryBy(this::getBets, playerNames);

        return Participants.of(playerNames, bets, dealer);
    }

    private <T> T retryBy(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Name> getPlayerNames() {
        final String[] playersName = inputView.getPlayersName().split(",", -1);
        return Arrays.stream(playersName)
                .map(playerName -> new Name(playerName.trim()))
                .collect(toList());
    }

    private List<Bet> getBets(final List<Name> playerNames) {
        List<Bet> bets = new ArrayList<>();

        for (Name playerName : playerNames) {
            final Bet bet = new Bet(inputView.getBet(playerName.getName()));
            bets.add(bet);
        }

        return bets;
    }

    private void distributeCards(Deck deck, Dealer dealer, Participants participants) {
        distributeInitialCards(deck, participants);
        distributeMoreCards(deck, dealer, participants);
    }

    private void distributeInitialCards(Deck deck, Participants participants) {
        divideFirstCard(deck, participants);
        outputView.printFirstCardStatus(BlackJackGameResponse.create(participants));
    }

    private void divideFirstCard(final Deck deck, final Participants participants) {
        outputView.printDivideTwoCard(BlackJackGameResponse.create(participants));
        participants.receiveInitialCards(deck);
    }

    private void distributeMoreCards(Deck deck, Dealer dealer, Participants participants) {
        divideCard(deck, participants, dealer);
        outputView.printScoreBoard(BlackJackGameResponse.create(participants));
    }

    private void divideCard(final Deck deck, final Participants participants, final Dealer dealer) {
        divideCardForPlayer(deck, participants);
        divideCardForDealer(deck, dealer);
    }

    private void divideCardForPlayer(final Deck deck, final Participants participants) {
        for (Player player : participants.getPlayers()) {
            receiveCardForPlayer(deck, player);
        }
    }

    private void receiveCardForPlayer(final Deck deck, final Player player) {
        boolean canReceive = player.canReceiveCard();
        while (canReceive) {
            final ReceiveCommand inputCommand = retryBy(this::getInputMoreCardCommand, player);
            receiveCardForPlayer(deck, player, inputCommand);
            canReceive = canPlayerReceiveCard(player, inputCommand);
        }
    }

    private <T, R> R retryBy(final Function<T, R> function, T input) {
        while (true) {
            try {
                return function.apply(input);
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean canPlayerReceiveCard(final Player player, final ReceiveCommand receiveCommand) {
        return player.canReceiveCard() && ReceiveCommand.isHit(receiveCommand);
    }

    private ReceiveCommand getInputMoreCardCommand(final Player player) {
        return ReceiveCommand.of(inputView.getPlayerInputGetMoreCard(player.getName()));
    }

    private void receiveCardForPlayer(final Deck deck, final Player player, final ReceiveCommand receiveCommand) {
        if (ReceiveCommand.isHit(receiveCommand)) {
            player.receiveCard(deck.pick());
            final HandResponse handResponse = HandResponse.of(player.calculateTotalValue(),
                    player.getHand().getCards());
            outputView.printPlayerCardStatus(new UserResponse(player.getName(), handResponse));
        }
    }

    private void divideCardForDealer(final Deck deck, final Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            outputView.printReceiveCardForDealer();
        }
    }

    private void printResult(Dealer dealer, Participants participants) {
        printBetResult(dealer, participants);
    }

    private void printBetResult(Dealer dealer, Participants participants) {
        outputView.printBetResult();
        outputView.printDealerBetResult(dealer.getName(), participants.calculateTotalProfits());
        outputView.printPlayerBetResult(BetResponse.create(participants));
    }
}
