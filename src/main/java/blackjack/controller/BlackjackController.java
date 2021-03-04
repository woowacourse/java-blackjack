package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Deck deck = new Deck();
        Game game = new Game(InputView.receivePlayerNames());

        initCards(deck, game);
        drawCards(deck, game);
        printResults(game);
    }

    private void printResults(Game game) {
        OutputView.printUserResult(game.getResultDTOs());
        OutputView.printWinningResult(game.getWinningResultDTOs());
    }

    private void drawCards(Deck deck, Game game) {
        game.getPlayers().forEach(player -> getAdditionalCard((Player) player, deck));
        OutputView.printDealerDraw(game.askDrawToDealer(deck));
    }

    private void initCards(Deck deck, Game game) {
        game.initialCards(deck);
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());
    }

    private void getAdditionalCard(Player player, Deck deck) {
        while(player.isHit()) {
            player.askDraw(InputView.askIfMoreCard(player), deck);
            OutputView.printPlayerCard(player);
        }
    }
}

