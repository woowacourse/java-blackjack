package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Result;

import java.util.List;

public class BlackJackGame {
    private final CardDeck cardDeck;
    private final Players players;

    private BlackJackGame(CardDeck cardDeck, Players players) {
        this.cardDeck = cardDeck;
        this.players = players;
    }

    public static BlackJackGame from(Players players) {
        CardDeck cardDeck = CardDeck.create();
        cardDeck.shuffle();
        return new BlackJackGame(cardDeck, players);
    }

    public void handOutStartCards() {
        players.pickStartCards(cardDeck);
    }

    public boolean canPick(Player player) {
        return player.canPick();
    }

    public boolean isDealerCanPick() {
        return getDealer().canPick();
    }

    public void pick(Player player) {
        Card pickedCard = cardDeck.pick();
        player.pick(pickedCard);
    }

    public void takeDealerTurn() {
        Player dealer = getDealer();
        if (dealer.canPick()) {
            pick(dealer);
        }
    }

    public Result makeResult() {
        return Result.from(players);
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Challenger> getChallengers() {
        return players.getChallengers();
    }
}
