package blackjack.controller;

import blackjack.domain.Victory;
import blackjack.domain.card.Cards;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.RandomCardsShuffler;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        try {
            Players players = createPlayers();
            Dealer dealer = new Dealer(players, DeckFactory.createDefaultDeck(), new ScoreCalculator());
            handOutCards(dealer, players);
            additionalCard(dealer, players);
            dealerAdditionalCard(dealer);
            printBlackjackResult(dealer, players);
            printVictory(dealer, players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
        }
    }

    private Players createPlayers() {
        List<String> playerNames = InputView.inputPlayerNames();
        return new Players(toPlayers(playerNames));
    }

    private static List<Player> toPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> new Player(name, new Cards(new ArrayList<>(), new ScoreCalculator())))
                .toList();
    }

    private void handOutCards(Dealer dealer, Players players) {
        dealer.prepareBlackjack(new RandomCardsShuffler());
        OutputView.printDealerAndPlayerCards(dealer.getCards(), players.getPlayers());
    }

    private void additionalCard(Dealer dealer, Players players) {
        players.sendAll((player -> {
            while (InputView.inputAdditionalCard(player)) {
                if (!sendCardToPlayer(dealer, player)) {
                    break;
                }
                OutputView.printPlayerCards(player);
            }
        }));
    }

    private static boolean sendCardToPlayer(Dealer dealer, Player player) {
        try {
            dealer.sendCardToPlayer(player);
            return true;
        } catch (IllegalArgumentException e) {
            OutputView.printCannotAdditionalCard();
            return false;
        }
    }


    private void dealerAdditionalCard(Dealer dealer) {
        int prevSize = dealer.getCardSize();
        dealer.pickAdditionalCard();
        int nextSize = dealer.getCardSize();
        OutputView.printDealerAdditionalCard(nextSize - prevSize);
    }


    private void printBlackjackResult(Dealer dealer, Players players) {
        OutputView.printDealerResult(dealer);
        OutputView.printPlayerResult(players.getPlayers());
    }

    private void printVictory(Dealer dealer, Players players) {
        Victory victory = dealer.createVictory();
        OutputView.printVictory(victory, players.getPlayers());
    }
}
