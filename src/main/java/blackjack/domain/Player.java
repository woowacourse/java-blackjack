package blackjack.domain;

import blackjack.utils.CardDeck;

import java.util.List;
import java.util.regex.Pattern;

public class Player extends Playable {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-z|A-Z]+");

    public Player(String name, CardDeck cardDeck) {
        this(name, cardDeck.initCards());
    }

    public Player(String name, List<Card> cards) {
        super(validateWord(name.trim()), cards);
    }

    private static String validateWord(String nameValue) {
        if (!NAME_PATTERN.matcher(nameValue).matches()) {
            throw new IllegalArgumentException();
        }
        return nameValue;
    }

    @Override
    public boolean isAvailableToTake() {
        return sumCards() <= 21;
    }
}
