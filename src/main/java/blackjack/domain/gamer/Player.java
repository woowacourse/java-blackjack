package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;

public class Player{
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 최소 1글자, 최대 6글자로 입력해야 합니다.";
    private static final String NAME_EMPTY_ERROR_MESSAGE = "빈 문자는 이름으로 입력할 수 없습니다.";
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 6;

    private final CardGroup cardGroup = new CardGroup();
    private final String name;
    private final Money betMoney;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.betMoney = new Money(0);
    }

    public static List<Player> of(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    private void validateName(String name) {
        validateEmpty(name);
        validateLength(name);
    }

    private void validateEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateLength(String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    public int compareCardsSumTo(int sum) {
        if (isBust()) {
            return -1;
        }

        if (isBust(sum)) {
            return 1;
        }

        return Integer.compare(getScore(), sum);
    }

    public void addCard(Card card) {
        if (cardGroup.isBust()) {
            return;
        }
        cardGroup.addCard(card);
    }

    public void openAllCards() {
        cardGroup.open();
    }

    public boolean isBust() {
        return cardGroup.isBust();
    }

    public boolean isBust(int sum) {
        return cardGroup.isBust(sum);
    }

    public boolean isAddable() {
        return cardGroup.isAddable();
    }

    public int getScore() {
        return cardGroup.getScore();
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public String getName() {
        return this.name;
    }

    public int getCardsSize() {
        return cardGroup.getSize();
    }

    public void addMoney(int amount) {
        betMoney.add(amount);
    }
}
