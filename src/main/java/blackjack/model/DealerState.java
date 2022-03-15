package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class DealerState extends State {
    private static final String FINISH = "Finish";

    public DealerState() {
        super();
    }

    private DealerState(Cards cards, String state) {
        super(cards, state);
    }

    @Override
    public State addCard(Card card) {
        Cards cards = this.cards.add(card);
        return new DealerState(cards, state);
    }

    @Override
    protected void choiceState() {
        int score = cards.sumScore();
        if (score > 17) {
            state = FINISH;
        }
        if (score > 21) {
            state = BUST;
        }
        if (score == 21 && cards.hasTwoCard()) {
            state = BLACKJACK;
        }
    }
}
