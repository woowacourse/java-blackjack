package blackjack.player;

import blackjack.card.Card;
import blackjack.game.MatchResult;
import blackjack.player.state.GameState;
import blackjack.player.state.playing.DealerState;
import blackjack.player.state.playing.PlayerState;
import java.util.List;
import java.util.function.Supplier;

public class Player {

    private final Name name;
    private GameState state;

    public Player(String name) {
        this(name, new PlayerState());
    }

    private Player(String name, GameState state) {
        this.name = new Name(name);
        this.state = state;
    }

    public static Player createAsDealer() {
        return new Player("딜러", new DealerState());
    }

    public void drawCard(Card card) {
        state = state.drawCard(card);
    }

    public void drawCards(Supplier<Card> cardSupplier, int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard(cardSupplier.get());
        }
    }

    public MatchResult matchResultVersus(Player opponent) {
        return state.createMatchResult(opponent.state);
    }

    public void stand() {
        state = state.stand();
    }

    public boolean isDrawable() {
        return !state.isTerminated();
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        Score score = state.getScore();
        return score.toInt();
    }

    public List<Card> getCards() {
        return state.cards();
    }

    public Card getFirstCard() {
        List<Card> cards = getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드를 가지고있지 않습니다.");
        }
        return getCards().get(0);
    }
}
