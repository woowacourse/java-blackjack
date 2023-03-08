package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.ResultMap;
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
        players.pickStartCards(cardDeck);
    }

    public void hit(Player player) {
        Card pickedCard = cardDeck.pick();
        player.pickCard(pickedCard);
    }

    public ResultMap makeResult() {
        return ResultMap.from(players);
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Player> getChallengers() {
        return players.getChallengers();
    }
}
