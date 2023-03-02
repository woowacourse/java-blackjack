package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;

public class BlackJackGame {
    private final CardDeck cardDeck;
    private final Players players;

    private BlackJackGame(CardDeck cardDeck, Players players) {
        this.cardDeck = cardDeck;
        this.players = players;
    }

    public static BlackJackGame from(List<String> names) {
        CardDeck cardDeck = CardDeck.create();
        Players players = Players.from(names);
        return new BlackJackGame(cardDeck, players);
    }

    public void handOutStartCards() {
        cardDeck.shuffle();
        players.pickStartCards(cardDeck);
    }

    public boolean canPick(Player player) {
        return player.canPick();
    }

    public void pick(Player player) {
        Card pickedCard = cardDeck.pick();
        player.pick(pickedCard);
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Player> getChallengers() {
        return players.getChallengers();
    }
}
