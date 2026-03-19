package blackjack.controller;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.betting.Bettings;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.CardResult;
import blackjack.dto.ProfitResult;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private static final int SIZE_OF_INITIAL_CARD = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final Deck deck;

    public BlackjackController(InputView inputView, OutputView outputView, Deck deck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.deck = deck;
    }

    public void run() {
        Players players = readPlayers();
        Dealer dealer = new Dealer();

        Bettings bettings = readBettingAmounts(players);
        setInitialCards(players, dealer, deck);
        printInitialSettings(players, dealer);

        getMoreCardsOfParticipants(players, dealer, deck);
        printCardsOfParticipants(players, dealer);
        printResult(bettings, players, dealer);
    }

    private Players readPlayers() {
        List<String> playersName = inputView.readPlayersName();
        return Players.from(playersName);
    }

    private Bettings readBettingAmounts(Players players) {
        Map<Player, BettingAmount> bettings = new HashMap<>();
        for (Player player : players.getPlayers()) {
            BettingAmount bettingAmount = new BettingAmount(inputView.readBettingAmount(player.getName()));
            bettings.put(player, bettingAmount);
        }
        return Bettings.of(bettings);
    }

    private void setInitialCards(Players players, Dealer dealer, Deck deck) {
        for (int i = 0; i < SIZE_OF_INITIAL_CARD; i++) {
            players.draw(deck);
            dealer.draw(deck.pop());
        }
    }

    private void printInitialSettings(Players players, Dealer dealer) {
        outputView.printInitialSettingsDoneMessage(dealer.getName(), players.getPlayersName());
        outputView.printCards(dealer.getName(), List.of(dealer.getFirstCardName()));
        for (Player player : players.getPlayers()) {
            outputView.printCards(player.getName(), player.getCardsName());
        }
        outputView.println();
    }

    private void getMoreCardsOfParticipants(Players players, Dealer dealer, Deck deck) {
        getMoreCardsOfPlayers(players, deck);
        getMoreCardsOfDealer(dealer, players, deck);
    }

    private void getMoreCardsOfPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            getMoreCardsOfPlayer(player, deck);
        }
    }

    private void getMoreCardsOfPlayer(Player player, Deck deck) {
        while (player.canDraw() && readPlayerWantMoreCard(player)) {
            player.draw(deck.pop());
            outputView.printCards(player.getName(), player.getCardsName());
        }
        outputView.printCards(player.getName(), player.getCardsName());
    }

    private boolean readPlayerWantMoreCard(Player player) {
        return inputView.readMoreCard(player.getName());
    }

    private void getMoreCardsOfDealer(Dealer dealer, Players players, Deck deck) {
        if (players.isAllPlayersBurst()) {
            return;
        }
        while (dealer.canDraw()) {
            dealer.draw(deck.pop());
            outputView.printGetMoreCardsMessageForDealer(dealer.getName());
        }
    }

    private void printCardsOfParticipants(Players players, Dealer dealer) {
        outputView.println();
        outputView.printCardsWithScore(CardResult.from(dealer));
        for (Player player : players.getPlayers()) {
            outputView.printCardsWithScore(CardResult.from(player));
        }
    }

    private void printResult(Bettings bettings, Players players, Dealer dealer) {
        outputView.printResult(ProfitResult.from(players, dealer, bettings));
    }

}
