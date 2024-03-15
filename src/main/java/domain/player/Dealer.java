package domain.player;

import domain.card.Card;
import domain.card.Deck;
import dto.DealerResponse;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int HIT_UPPER_BOUND = 17;
    private final Deck decks = Deck.makeDecks();

    public Card draw() {
        return decks.draw();
    }


    public PlayerResult compareHandsWith(final Player player) {
        if (isBust()) {
            return PlayerResult.LOSE;
        }

        if (player.isBust()) {
            return PlayerResult.WIN;
        }

        return compareScore(player);
    }

    private PlayerResult compareScore(final Player player) {
        if (this.getScore() == player.getScore()) {
            return PlayerResult.TIE;
        }
        if (this.getScore() < player.getScore()) {
            return PlayerResult.LOSE;
        }
        return PlayerResult.WIN;
    }

    public Map<PlayerResult, Integer> wrapUp(final Players players) {
        final Map<PlayerResult, Integer> result = new LinkedHashMap<>();
        players.stream()
                .forEach(player -> result.merge(compareHandsWith(player), 1, Integer::sum));
        return Collections.unmodifiableMap(result);
    }

    @Override
    public boolean canHit() {
        return state.canHit(HIT_UPPER_BOUND);
    }

    public DealerResponse toDealerResponse() {
        return new DealerResponse(getHands().stream().
                map(Card::toCardResponse)
                .toList(), state.getHands().calculateScore());
    }
}

