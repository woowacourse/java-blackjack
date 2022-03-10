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

    public static final int INIT_CARD_NUMBER = 2;

    public void run() {
        Table table = Table.of(getPlayers(), Dealer.of());

        initGame(table);
        startGame(table);
        endGame(table);
    }

    private void initGame(final Table table) {
        table.initCard();
        OutputView.printInitCards(table);
    }

    private void startGame(final Table table) {
        for (Player player : table.getPlayers().get()) {
            questionOneMoreCard(player, table.getCardDeck());
        }
        addCardToDealer(table);
    }

    private void endGame(final Table table) {
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
        if (!player.isOneMoreCard()) {
            return;
        }
        if (!InputView.inputOneMoreCard(player.getName()) && player.getCardSize() == INIT_CARD_NUMBER) {
            OutputView.printHumanCardState(player);
            return;
        }
        player.addCard(cardDeck.giveCard());
        OutputView.printHumanCardState(player);
        questionOneMoreCard(player,cardDeck);
    }
}