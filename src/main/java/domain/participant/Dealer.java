package domain.participant;

import domain.GameResult;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.cardsGenerator.CardsGenerator;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dealer extends Participant {

    public static final int DRAW_BOUNDARY = 16;
    private static final int INIT_COUNT = 2;

    private final Map<GameResult, Integer> result;
    private final Deck deck;

    private Dealer(Hand hand, Deck deck) {
        super(hand);
        this.result = new EnumMap<>(GameResult.class);
        this.deck = deck;
    }

    public static Dealer of(Hand hand, CardsGenerator cardsGenerator) {
        return new Dealer(hand, new Deck(cardsGenerator));
    }

    public static Dealer init(CardsGenerator cardsGenerator) {
        return new Dealer(Hand.empty(), new Deck(cardsGenerator));
    }

    public void handoutCards(Players players) {
        giveCards(this, INIT_COUNT);
        Set<Player> participants = players.getPlayers();
        for (Participant participant : participants) {
            giveCards(participant, INIT_COUNT);
        }
    }

    public void giveCards(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            deck.giveCardTo(participant, count);
        }
    }

    public void drawUntilLimit() {
        while (hasToDraw()) {
            deck.giveCardTo(this, 1);
        }
    }

    private boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }


    public Map<Player, GameResult> getGameResult(Players players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult playerResult = rule.getResult(player, this);
            gameResult.put(player, playerResult);
            GameResult dealerResult = playerResult.getReverse();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return gameResult;
    }

    public int getNewCardCount() {
        return super.getCards().getCards().size();
    }

    public Map<GameResult, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
