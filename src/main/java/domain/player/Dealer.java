package domain.player;

import domain.card.Card;
import domain.card.Deck;
import dto.PlayerResult;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Participant {
    private static final int HIT_UPPER_BOUND = 17;

    private final Deck decks = Deck.makeDecks();

    public Dealer() {
        super();
    }


    public Card draw() {
        return decks.draw();
    }



    public PlayerResult compareHandsWith(final Player player) {
        if (player.isBust()) {
            return PlayerResult.WIN;
        }
        if (isBust()) {
            return PlayerResult.LOSE;
        }

        return compareScore(player);
    }

    private PlayerResult compareScore(final Player player) {
//        if (player.calculateScore() == calculateScore()) {
//            return PlayerResult.TIE;
//        }
//        if (player.calculateScore() > calculateScore()) {
//            return PlayerResult.LOSE;
//        }
//        return PlayerResult.WIN;
        return null;
    }

    public Map<PlayerResult, Integer> wrapUp(final Players players) {
        final Map<PlayerResult, Integer> result = new LinkedHashMap<>();
        players.stream()
                .forEach(player -> result.merge(compareHandsWith(player), 1, Integer::sum));
        return Collections.unmodifiableMap(result);
    }

//    public DealerResponse toDealerResponse() {
//        return new DealerResponse(getHands().stream().
//                map(Card::toCardResponse)
//                .toList(), calculateScore());
//    }
}
