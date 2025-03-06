package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    public static final int DEALER_DRAW_BOUND = 16;

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand drawCardWhenStart(CardDeck cardDeck) {
        hand.drawCardWhenStart(cardDeck);
        return hand;
    }

    public Hand drawCard(CardDeck cardDeck) {
        hand.drawCard(cardDeck);
        return hand;
    }

    public List<Card> getInitCard() {
        return List.of(hand.getCards().getFirst());
    }

    public Hand getHand() {
        return hand;
    }

    public int calculateTotalCardNumber() {
        return hand.calculateTotalCardNumber();
    }

    public boolean isOverBurstBound() {
        int totalCardNumber = calculateTotalCardNumber();
        return hand.isOverBurstBound(totalCardNumber);
    }

    public boolean isOverDrawBound() {
        return calculateTotalCardNumber() > DEALER_DRAW_BOUND;
    }

    public int getCardsCount() {
        return hand.getCardsCount();
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
}
