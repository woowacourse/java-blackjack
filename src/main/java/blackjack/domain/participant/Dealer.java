package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private final Name name;
    private final Cards cards;

    public Dealer() {
        this.name = new Name("딜러");
        this.cards = new Cards();
    }

    public boolean isReady() {
        return cards.isReady();
    }

    public boolean isFinished() {
        return cards.totalScore() > 16;
    }

    public void hit(Card card) {
        if (!isFinished()) {
            cards.append(card);
        }
    }

    public int getTotalScore() {
        return cards.totalScore();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getValue());
    }
}
