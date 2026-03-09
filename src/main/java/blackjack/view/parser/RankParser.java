package blackjack.view.parser;

import static blackjack.model.card.Rank.ACE;
import static blackjack.model.card.Rank.JACK;
import static blackjack.model.card.Rank.KING;
import static blackjack.model.card.Rank.QUEEN;

import blackjack.model.card.Rank;
import java.util.Map;

public class RankParser {

    private static final Map<Rank, String> CHARACTER_RANK_LABELS = Map.of(
            ACE, "A",
            KING, "K",
            QUEEN, "Q",
            JACK, "J"
    );

    public static String parseToLabel(Rank rank) {
        if (CHARACTER_RANK_LABELS.containsKey(rank)) {
            return CHARACTER_RANK_LABELS.get(rank);
        }

        return String.valueOf(rank.getScore());
    }
}
