package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Name;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static final int DEFAULT_CARD_AMOUNT = 2;

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Dealer dealer = initDealer(cardDeck);
        Players players = initPlayers(cardDeck);
        OutputView.printInitGameState(players, dealer);

        askPlayersOneMoreCard(players, cardDeck);
        addDealerOneMoreCard(dealer, cardDeck);

        OutputView.printCardAndPoint(players, dealer);

        Statistic statistic = Statistic.of(dealer, players);
        printGameResult(statistic, players);
    }

    private Dealer initDealer(CardDeck cardDeck) {
        Dealer dealer = Dealer.of();
        dealer.addCard(cardDeck.draw());
        dealer.addCard(cardDeck.draw());
        return dealer;
    }

    private Players initPlayers(CardDeck cardDeck) {
        Players players = generatePlayers();
        players.distributeCard(cardDeck);
        players.distributeCard(cardDeck);
        return players;
    }

    private Players generatePlayers() {
        List<Player> playerList = new ArrayList<>();
        String[] names = InputView.inputPlayerName();
        for (String name : names) {
            playerList.add(Player.of(Name.of(name)));
        }
        return Players.of(playerList);
    }

    private void askPlayersOneMoreCard(Players players, CardDeck cardDeck) {
        for (Player player : players.getCardNeedPlayers()) {
            askOneMoreCardByPlayer(player, cardDeck);
        }
    }

    private void addDealerOneMoreCard(Dealer dealer, CardDeck cardDeck) {
        if (dealer.isOneMoreCard()) {
            dealer.addCard(cardDeck.draw());
            OutputView.printDealerCardAdded();
        }
    }

    private void askOneMoreCardByPlayer(Player player, CardDeck cardDeck) {
        if (player.isOneMoreCard()) {
            askNeedCardPlayer(player, cardDeck);
            checkFirstQuestion(player);
        }
    }

    private void askNeedCardPlayer(Player player, CardDeck cardDeck) {
        if (InputView.inputOneMoreCard(player.getName())) {
            player.addCard(cardDeck.draw());
            OutputView.printHumanCardState(player);
            askOneMoreCardByPlayer(player, cardDeck);
        }
    }

    private void checkFirstQuestion(Player player) {
        if (player.getCards().size() <= DEFAULT_CARD_AMOUNT) {
            OutputView.printHumanCardState(player);
        }
    }

    private void printGameResult(Statistic statistic, Players players) {
        OutputView.printTotalResult(statistic);
        OutputView.printTotalResultByPlayer(statistic, players);
    }
}
