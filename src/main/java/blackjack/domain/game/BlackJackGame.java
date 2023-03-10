package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResult;
import java.util.List;

public class BlackJackGame {
    private final CardDeck cardDeck;
    private final Players players;
    private final List<Money> betAmounts;

    private BlackJackGame(CardDeck cardDeck, Players players, List<Money> betAmounts) {
        this.cardDeck = cardDeck;
        this.players = players;
        this.betAmounts = betAmounts;
    }

    public static BlackJackGame from(Players players, List<Money> betAmounts) {
        CardDeck cardDeck = CardDeck.create();
        return new BlackJackGame(cardDeck, players, betAmounts);
    }

    public void handOutStartCards() {
        players.pickStartCards(cardDeck);
    }

    public void hit(Player player) {
        Card pickedCard = cardDeck.pick();
        player.pickCard(pickedCard);
    }

    public GameResult makeResult() {
        return GameResult.from(players, betAmounts);
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Challenger> getChallengers() {
        return players.getChallengers();
    }
}
