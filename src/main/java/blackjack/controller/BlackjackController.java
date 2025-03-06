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
import java.util.Arrays;
import java.util.List;

public class BlackjackController {
    public void run() {
        Players players = createPlayers();
        Dealer dealer = new Dealer(players, DeckFactory.createDefaultDeck(), new ScoreCalculator());
        handOutCards(dealer, players);
        additionalCard(dealer, players);
        dealerAdditionalCard(dealer);
        printBlackjackResult(dealer, players);
        printVictory(dealer, players);
    }

    private Players createPlayers() {
        String[] playerNames = InputView.inputPlayerNames();
        return new Players(toPlayers(playerNames));
    }

    private static List<Player> toPlayers(String[] playerNames) {
        return Arrays.stream(playerNames)
                .map(name -> new Player(name.trim(), new Cards(new ArrayList<>(), new ScoreCalculator())))
                .toList();
    }

    private void handOutCards(Dealer dealer, Players players) {
        dealer.prepareBlackjack(new RandomCardsShuffler());
        OutputView.printDealerAndPlayerCards(dealer.getCards(), players.getPlayers());
    }

    private void additionalCard(Dealer dealer, Players players) {
        players.sendAll((player -> {
            String answer = InputView.inputAdditionalCard(player);
            while (answer.equals("y")) {
                if (!sendCardToPlayer(dealer, player)) {
                    break;
                }
                OutputView.printPlayerCards(player);
                answer = InputView.inputAdditionalCard(player);
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
        int prevSize = dealer.getCards().size();
        dealer.pickAdditionalCard();
        int nextSize = dealer.getCards().size();
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
