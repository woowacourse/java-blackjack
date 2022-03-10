package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Name;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    public void run() {
        Players players = getPlayers();
        Dealer dealer = Dealer.of();

        giveTwoCards(players, dealer);
        OutputView.printInitCards(players, dealer);

        for (Player player : players.getPlayers()) {
            questionOneMoreCard(player);
        }
        addCardToDealer(dealer);

        OutputView.printCardAndPoint(players, dealer);
        Statistic.of(dealer).calculate(players);
        printGameResult(players, dealer);
    }

    private void addCardToDealer(final Dealer dealer) {
        if (dealer.isOneMoreCard()) {
            dealer.addCard(CardDeck.giveCard());
            OutputView.printDealerCardAdded();
        }
    }

    private Players getPlayers() {
        List<Player> playerList = new ArrayList<>();
        String[] names = InputView.inputPlayerName();
        for (String name : names) {
            playerList.add(Player.of(Name.of(name)));
        }
        return Players.of(playerList);
    }


    private void giveTwoCards(final Players players, final Dealer dealer) {
        dealer.addCard(CardDeck.giveCard());
        dealer.addCard(CardDeck.giveCard());
        players.giveCard();
        players.giveCard();
    }

    private void printGameResult(final Players players, final Dealer dealer) {
        Statistic statistic = Statistic.of(dealer);
        statistic.calculate(players);
        OutputView.printDealerResult(statistic.getDealerWinState());
        OutputView.printPlayerResult(players);
    }

    private void questionOneMoreCard(final Player player) {
        boolean flag = true;
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                if (flag) {
                    OutputView.printHumanCardState(player);
                }
                break;
            }
            player.addCard(CardDeck.giveCard());
            OutputView.printHumanCardState(player);
            flag = false;
        }
    }
}