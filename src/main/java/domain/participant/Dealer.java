package domain.participant;

import domain.GameResult;
import domain.card.Card;
import domain.card.Cards;

import java.util.*;

public class Dealer extends Participant {
    public static final int DRAW_BOUNDARY = 16;

    private final Map<GameResult, Integer> result;

    private Dealer(Cards cards) {
        super(cards);
        this.result = new EnumMap<>(GameResult.class);
    }

    @Override
    public List<Card> getShowCards() {
        List<Card> cards = getCards().getValues();
        return cards.subList(0, 1);
    }

    public static Dealer init() {
        return new Dealer(Cards.empty());
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }

    public boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }

    public Map<Player, GameResult> getGameResult(Players players) {
        Map<Player, GameResult> gameResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult playerResult = getRule().getResult(player, this);
            gameResult.put(player, playerResult);
            GameResult dealerResult = playerResult.getReverse();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return gameResult;
    }

    public Map<GameResult, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
