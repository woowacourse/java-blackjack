package blackjack.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Dealer extends Person {
    private static final int DRAW_CARD_BOUNDARY = 16;

    public Dealer() {
        super("딜러");
    }

    public Cards createUniqueCards() {
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .flatMap(suit -> Stream.of(new Card(suit, rank)))
                ).collect(toList());
        Collections.shuffle(cards);
        return new Cards(cards);
    }

    public boolean canDrawCard() {
        return getScore() <= DRAW_CARD_BOUNDARY;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public List<Card> getInitCards() {
        return getCards().stream()
                .limit(1)
                .collect(toList());
    }
}
