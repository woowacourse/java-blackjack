package blackjack.controller;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Statistic;
import blackjack.domain.card.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class GameController {
    public void run() {
        BlackjackTable blackjackTable = BlackjackTable.of(getPlayers());
        
        initGame(blackjackTable);
        startGame(blackjackTable);
        endGame(blackjackTable);
    }
    
    private Players getPlayers() {
        return Players.ofInputText(InputView.inputPlayerNames());
    }
    
    private void initGame(final BlackjackTable blackjackTable) {
        blackjackTable.initCard();
        OutputView.printInitCards(blackjackTable);
    }
    
    private void startGame(final BlackjackTable blackjackTable) {
        for (Player player : blackjackTable.getPlayers().get()) {
            questAddCard(player, blackjackTable.getCardDeck());
        }
        addCardToDealer(blackjackTable);
    }
    
    private void questAddCard(final Player player, final CardDeck cardDeck) {
        while (player.isAbleToHit() && InputView.inputOneMoreCard(player.getName())) {
            player.addCard(cardDeck.draw());
            OutputView.printHumanHand(player);
        }
        if (player.isTwoCard()) {
            OutputView.printHumanHand(player);
        }
    }
    
    private void addCardToDealer(final BlackjackTable blackjackTable) {
        Dealer dealer = blackjackTable.getDealer();
        if (dealer.isAbleToHit()) {
            dealer.addCard(blackjackTable.getCardDeck().draw());
            OutputView.printDealerHit();
        }
    }
    
    private void endGame(final BlackjackTable blackjackTable) {
        OutputView.printHandAndPoint(blackjackTable);
        
        Statistic statistic = Statistic.of(blackjackTable);
        
        OutputView.printDealerResult(statistic.getDealerResult());
        OutputView.printPlayerResult(statistic.getPlayersResult());
    }
}
