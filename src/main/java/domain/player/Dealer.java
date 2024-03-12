package domain.player;

import domain.card.Card;
import domain.card.Cards;
import java.util.HashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int HIT_UPPER_BOUND = 17;

    private final Cards decks = Cards.makeDecks();

    public Dealer() {
        super();
    }


    public Card draw() {
        return decks.draw();
    }

    @Override
    public boolean canHit() {
        return calculateScore() < HIT_UPPER_BOUND;
    }

    @Override
    public boolean canNotHit() {
        return !canHit();
    }

    @Override
    public String getName() {
        return "딜러";
    }

    public Map<PlayerResult, Integer> wrapUp(final Players players) {
        final Map<PlayerResult, Integer> result = new HashMap<>();
        players.stream()
                .forEach(player -> result.merge(compareHandsWith(player), 1, Integer::sum));
        return result;
    }

    public PlayerResult compareHandsWith(final Player player) {
        if (player.isBust()) {
            return PlayerResult.WIN;
        }
        if (isBust()) {
            return PlayerResult.LOSE;
        }

        if (player.calculateScore() == calculateScore()) {
            return PlayerResult.TIE;
        } else if (player.calculateScore() > calculateScore()) {
            return PlayerResult.LOSE;
        } else {
            return PlayerResult.WIN;
        }
    }

}
