package domain.game;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private static final int DEALER_DRAW_BOUND = 16;

    private final Hand hand;

    public Dealer(CardDeck cardDeck) {
        this.hand = new Hand(cardDeck);
    }

    public void drawCardWhenStart() {
        hand.drawCardWhenStart();
    }

    public void drawCard() {
        hand.drawCard();
    }

    public List<GameResult> judgeGameResult(List<Player> players) {
        List<GameResult> gameResult = new ArrayList<>();
        for (Player player : players) {
            gameResult.add(judgeWin(player));
        }
        return gameResult;
    }

    private GameResult judgeWin(Player player) {
        return GameResult.of(this, player);
    }

    public Hand getHand() {
        return hand;
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalWithAce();
    }

    public boolean isOverBustBound() {
        return hand.isOverBustBound();
    }

    public boolean isOverDrawBound() {
        return calculateTotalCardNumber() > DEALER_DRAW_BOUND;
    }

    public int getCardsCount() {
        return hand.getCardsCount();
    }

    public Card getSingleCard() {
        return hand.getCards().getFirst();
    }
}
