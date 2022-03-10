package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.Table;
import blackjack.domain.card.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Name;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import blackjack.util.Constants;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    public void run() {
        Table table = Table.of(getPlayers());

        initGame(table);
        startGame(table);
        endGame(table);
    }

    private Players getPlayers() {
        List<Player> rawPlayers = new ArrayList<>();
        for (String name : InputView.inputPlayerNames()) {
            rawPlayers.add(Player.of(name));
        }
        return Players.of(rawPlayers);
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

    private void questionOneMoreCard(final Player player, final CardDeck cardDeck) {
        if (!player.isOneMoreCard()) {
            return;
        }
        boolean isAddCard = InputView.inputOneMoreCard(player.getName());
        if (!isAddCard && player.getCardSize() == Constants.INIT_CARD_NUMBER) {
            OutputView.printHumanCardState(player);
            return;
        }
        if (isAddCard) {
            player.addCard(cardDeck.giveCard());
            OutputView.printHumanCardState(player);
            questionOneMoreCard(player, cardDeck);
        }
    }

    private void addCardToDealer(final Table table) {
        if (table.getDealer().isOneMoreCard()) {
            table.getDealer().addCard(table.getCardDeck().giveCard());
            OutputView.printDealerCardAdded();
        }
    }

    private void endGame(final Table table) {
        OutputView.printCardAndPoint(table);
        Statistic.of(table.getDealer()).calculate(table.getPlayers());
        printGameResult(table);
    }

    private void printGameResult(final Table table) {
        Statistic statistic = Statistic.of(table.getDealer());
        statistic.calculate(table.getPlayers());
        OutputView.printDealerResult(statistic.getDealerResult());
        OutputView.printPlayerResult(table.getPlayers());
    }
}