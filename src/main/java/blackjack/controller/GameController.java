package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.Table;
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
        Table table = Table.of(getPlayers(), Dealer.of());

        table.initCard();
        OutputView.printInitCards(table);

        for (Player player : table.getPlayers().get()) {
            questionOneMoreCard(player, table.getCardDeck());
        }
        addCardToDealer(table);

        OutputView.printCardAndPoint(table);
        Statistic.of(table.getDealer()).calculate(table.getPlayers());
        printGameResult(table);
    }

    private Players getPlayers() {
        List<Player> playerList = new ArrayList<>();
        for (String name : InputView.inputPlayerName()) {
            playerList.add(Player.of(Name.of(name)));
        }
        return Players.of(playerList);
    }

    private void addCardToDealer(final Table table) {
        if (table.getDealer().isOneMoreCard()) {
            table.getDealer().addCard(table.getCardDeck().giveCard());
            OutputView.printDealerCardAdded();
        }
    }

    private void printGameResult(final Table table) {
        Statistic statistic = Statistic.of(table.getDealer());
        statistic.calculate(table.getPlayers());
        OutputView.printDealerResult(statistic.getDealerWinState());
        OutputView.printPlayerResult(table.getPlayers());
    }

    private void questionOneMoreCard(final Player player, final CardDeck cardDeck) {
        boolean flag = true;
        while (player.isOneMoreCard()) {
            if (!InputView.inputOneMoreCard(player.getName())) {
                if (flag) {
                    OutputView.printHumanCardState(player);
                }
                break;
            }
            player.addCard(cardDeck.giveCard());
            OutputView.printHumanCardState(player);
            flag = false;
        }
    }
}