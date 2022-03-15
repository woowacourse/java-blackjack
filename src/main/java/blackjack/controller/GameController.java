package blackjack.controller;

import blackjack.domain.Statistic;
import blackjack.domain.Table;
import blackjack.domain.card.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
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
            questAddCard(player, table.getCardDeck());
        }
        addCardToDealer(table);
    }
    
    private void questAddCard(final Player player, final CardDeck cardDeck) {
        if (!player.isAbleToHit()) {
            return;
        }
        boolean isHit = InputView.inputOneMoreCard(player.getName());
        if (!isHit && player.isTwoCard()) {
            OutputView.printHumanHand(player);
            return;
        }
        if (isHit) {
            player.addCard(cardDeck.draw());
            OutputView.printHumanHand(player);
            questAddCard(player, cardDeck);
        }
    }
    
    private void addCardToDealer(final Table table) {
        Dealer dealer = table.getDealer();
        if (dealer.isAbleToHit()) {
            dealer.addCard(table.getCardDeck().draw());
            OutputView.printDealerHit();
        }
    }
    
    private void endGame(final Table table) {
        OutputView.printHandAndPoint(table);
        
        Statistic statistic = Statistic.of(table);
        
        OutputView.printDealerResult(statistic.getDealerResult());
        OutputView.printPlayerResult(statistic.getPlayersResult());
    }
}
