package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Player {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";

    private final Gamer gamer;
    private final String name;

    public Player(Gamer gamer, String name) {
        validateNameIsBlank(name);
        this.gamer = gamer;
        this.name = name;
    }

    public void draw(List<Card> cards) {
        gamer.draw(cards);
    }

    public void draw(Card card) {
        gamer.draw(card);
    }

    public boolean isBust() {
        return gamer.isBust();
    }

    public boolean isBlackjack() {
        return gamer.isBlackjack();
    }

    public boolean isMaxScore() {
        return gamer.isMaxScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return gamer.getCards();
    }

    public int getScore() {
        return gamer.getScore();
    }

    private void validateNameIsBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }
}
