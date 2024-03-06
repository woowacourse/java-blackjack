package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.dto.DealerDto;
import blackjack.domain.dto.PlayersDto;

public class GameBoard {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;


    public GameBoard(final Deck deck, final Dealer dealer, final Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public DealerDto drawInitialDealerCards() {
        drawTwoCards(dealer);
        return dealer.firstCardToDto();
    }

    public PlayersDto drawInitialPlayersCards() {
        for (Player player : players.getPlayers()) {
            drawTwoCards(player);
        }
        return players.toDto();
    }

    private void drawTwoCards(Gamer gamer) {
        gamer.draw(deck.pop());
        gamer.draw(deck.pop());
    }
}
