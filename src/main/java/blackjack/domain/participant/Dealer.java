package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int DEALER_CARD_PIVOT = 17;
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final Map<Result, Integer> resultScores = new EnumMap(Result.class);

    public Dealer() {
        this(Deck.createFixedCards());
    }

    public Dealer(Deck deck) {
        this.deck = deck;
        super.name = new Name(DEALER_NAME);
        initResultScores();
    }

    private void initResultScores() {
        for (Result result : Result.values()) {
            resultScores.put(result, 0);
        }
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void drawCardHandFirstTurn() {
        selfDraw();
        selfDraw();
    }

    public void selfDraw() {
        cardHand.add(drawCard());
    }

    public boolean shouldReceive() {
        return cardHand.getScore() < DEALER_CARD_PIVOT;
    }

    public Card getOpenCard() {
        return cardHand.getCards().get(0);
    }

    public void drawCardToPlayers(List<Player> players) {
        for (Player player : players) {
            giveCard(player);
            giveCard(player);
        }
    }

    public void giveCard(Player player) {
        player.receiveCard(drawCard());
    }

    public Map<Result, Integer> getResultScores() {
        return resultScores;
    }

    public void decideMatchResult(Player player) {
        Result result = cardHand.compareMatchResult(player.getCardHand());
        resultScores.put(result, resultScores.get(result) + 1);
    }
}
