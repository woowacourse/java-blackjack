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
        game.initialCards(deck);
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());
        OutputView.printLine();
        game.getPlayers().forEach(player -> getAdditionalCard((Player) player, deck));
        OutputView.printDealerDraw(game.askDrawToDealer(deck));
    }

    private void getAdditionalCard(Player player, Deck deck) {
        while(player.isHit()) {
            player.askDraw(InputView.askIfMoreCard(player), deck.pickSingleCard());
            OutputView.printPlayerCard(player);
        }
    }
}

