package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.utils.CardDeck;

import java.util.List;
import java.util.regex.Pattern;

public class Player extends Participant {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-z|A-Z]+");

    public Player(String name, GameTable gameTable) {
        this(name, gameTable.initCards());
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
        return super.sumCards() <= Cards.BLACK_JACK;
    }
}
