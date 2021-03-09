package blackjack.domain;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Game {

    private static final int CARD_INIT_COUNT = 2;
    private final Cards cards;
    private final Player dealer;
    private final Gamers gamers;

    private Game(Cards cards, Player dealer, Gamers gamers) {
        this.cards = cards;
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public static Game of(Cards cards, Player dealer, Gamers gamers) {
        initialize(cards, dealer, gamers);
        return new Game(cards, dealer, gamers);
    }

    public static Game ofTest(Cards cards, Player dealer, Gamers gamers) {
        return new Game(cards, dealer, gamers);
    }

    private static void initialize(Cards cards, Player dealer, Gamers gamers) {
        cards.shuffle();

        for (int i = 0; i < CARD_INIT_COUNT; i++) {
            dealer.addCardToDeck(cards.next());
            gamers.drawToGamers(cards);
        }
    }

    public void drawCardToPlayer(Player player) {
        player.addCardToDeck(cards.next());
    }

    public boolean isPlayerDrawable(Player player) {
        return player.isDrawable();
    }

    public GameResult gameResult() {
        GameResult gameResult = new GameResult();
        gameResult.writeResult(dealer, gamers);
        return gameResult;
    }

    public Player findGamerByName(String name) {
        return gamers.findGamer(name);
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamersAsList() {
        return Collections.unmodifiableList(gamers.getGamers());
    }
}
