package domain.user;

import domain.HandCard;
import domain.card.Card;

import java.util.Objects;

public class Player extends User {
    private final String name;
    private final HandCard handCard;

    public Player(String name) {
        validate(name);
        this.name = name;
        handCard = new HandCard();
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public String getName() {
        return name;
    }

    public void draw(Card card) {
        handCard.add(card);
    }

    public String getStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name)
                .append(": ")
                .append(handCard.getNames());
        return stringBuilder.toString();
    }

    public int getScore() {
        return handCard.getScore();
    }
}
