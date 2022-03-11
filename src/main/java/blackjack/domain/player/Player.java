package blackjack.domain.player;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Player {

    private final String name;
    protected final Cards cards;

    public Player(String name, Cards cards) {
        checkEmptyName(name);
        this.name = name;
        this.cards = cards;
    }

    private void checkEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public Result findResult(Player otherPlayer) {
        return Result.findResult(this.cards.calculateScore(), otherPlayer.cards.calculateScore());
    }

    public List<Card> openAllOfCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }

    public abstract List<Card> openFirstCards();

    public abstract boolean isPossibleToReceiveCard();
}
