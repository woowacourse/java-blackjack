package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.GameBoard;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckShuffleFactory;
import blackjack.domain.dto.DealerDto;
import blackjack.domain.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(new DeckShuffleFactory());
        Dealer dealer = Dealer.create();
        Players players = Players.from(InputView.readPlayerNames());
        final GameBoard gameBoard = new GameBoard(deck, dealer, players);
        final DealerDto dealerDto = gameBoard.drawInitialDealerCards();
        final PlayersDto playersDto = gameBoard.drawInitialPlayersCards();

        OutputView.printInitialState(dealerDto, playersDto);

        hitPlayers(players, deck);
        hitDealer(dealer, deck);
    }

    private static void hitPlayers(final Players players, final Deck deck) {
        for (Player player : players.getPlayers()) {
            hitPlayer(player, deck);
        }
    }

    private static void hitPlayer(final Player player, final Deck deck) {
        while (player.canDraw() && InputView.readDoesWantHit(player.getName())) {
            player.draw(deck.pop());
            OutputView.printCurrentState(player.toDto());
        }
    }

    private static void hitDealer(final Dealer dealer, final Deck deck) {
        System.out.println();
        while (dealer.canDraw()) {
            dealer.draw(deck.pop());
            OutputView.printDealerDrawMessage();
        }
    }
}
