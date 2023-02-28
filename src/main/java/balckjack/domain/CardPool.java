package balckjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardPool {

    public static final Map<Pattern, List<Card>> cards = new HashMap<>();

    private CardPool() {
    }

    static {
        List<Card> a = new ArrayList<>();
        a.add(new AceCard());
        for (int i = 2; i < 11; i++) {
            a.add(new StandardCard(i));
        }
        a.add(new CourtCard("J"));
        a.add(new CourtCard("K"));
        a.add(new CourtCard("Q"));

        Arrays.stream(Pattern.values()).forEach((pattern)->cards.put(pattern, a));
    }

    public static int getSize() {
        return cards.values().stream().mapToInt(List::size).sum();
    }

}
