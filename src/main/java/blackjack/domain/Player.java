package blackjack.domain;

import blackjack.utils.CardDeck;
import blackjack.utils.IllegalNameException;

import java.util.List;
import java.util.regex.Pattern;

public class Player extends Participant {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-z|A-Z]+");

    public Player(String name, CardDeck cardDeck) {
        this(name, cardDeck.initCards());
    }

    public Player(String name, List<Card> cards) {
        super(validateWord(name.trim()), cards);
    }

    private static String validateWord(String nameValue) {
        if (!NAME_PATTERN.matcher(nameValue).matches()) {
            throw new IllegalNameException("이름은 알파벳 대소문자로 이루어져야 합니다.");
        }
        return nameValue;
    }

    @Override
    public boolean isAvailableToTake() {
        return sumCards() <= BLACKJACK;
    }

    public Outcome calculateOutcome(Dealer dealer) {
        return result(dealer.sumCardsForResult());
    }
}
