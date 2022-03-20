package blackjack.domain.participant;

import blackjack.domain.BlackJack;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.regex.Pattern;

public class Participant {
    private static final String DEALER_NAME = "딜러";
    private static final String BLANK = "";
    private static final String ERROR_MESSAGE_EMPTY_NAME = "[ERROR] 이름은 공백일 수 없습니다.";
    private static final Pattern ALLOWED_CHARACTERS = Pattern.compile(".*[^0-9a-zA-Zㄱ-ㅎ가-힣_]+.*");
    private static final String ERROR_MESSAGE_UNAVAILABLE_CHARACTER = "[ERROR] 이름에 특수문자가 포함될 수 없습니다.";

    private final String name;
    private final Cards cards;
    private int betting;

    private Participant(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public static Participant createDealer() {
        return new Participant(DEALER_NAME);
    }

    public static Participant createPlayer(String input) {
        return new Participant(validateName(input.trim()));
    }

    private static String validateName(String input) {
        checkBlankName(input);
        checkUnavailableName(input);
        return input;
    }

    private static void checkBlankName(String input) {
        if (BLANK.equals(input)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_NAME);
        }
    }

    private static void checkUnavailableName(String input) {
        if (ALLOWED_CHARACTERS.matcher(input).matches()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_UNAVAILABLE_CHARACTER);
        }
    }

    public void bet(int betting) {
        this.betting = betting;
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public Boolean isOverMaxScore() {
        return BlackJack.isOverMaxScore(getScore());
    }

    public int getScore() {
        return cards.sum();
    }

    public boolean isBlackJack() {
        return BlackJack.isMaxScore(getScore()) && cards.hasOnlyTwoCards();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getBetting() {
        return betting;
    }
}
