package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DeckFactory;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.RandomCardsShuffler;
import blackjack.domain.ScoreCalculator;
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
    }

    private Players createPlayers() {
        String[] playerNames = InputView.inputPlayerNames();
        return new Players(toPlayers(playerNames));
    }

    private static List<Player> toPlayers(String[] playerNames) {
        return Arrays.stream(playerNames)
                .map(name -> new Player(name.trim(), new ArrayList<>(), new ScoreCalculator()))
                .toList();
    }

    private void handOutCards(Dealer dealer, Players players) {
        dealer.prepareBlackjack(new RandomCardsShuffler());
        OutputView.printPlayerCards(dealer.getCards(), players.getPlayers());
    }
}
