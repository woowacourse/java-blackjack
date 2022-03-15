package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class DealerState extends State {
    private static final int MAX_SCORE = 21;
    private static final int DEALER_HIT_LIMIT_SCORE = 16;

    private static final String FINISH = "Finish";

    public DealerState() {
        super();
    }

    private DealerState(Cards cards, String state) {
        super(cards, state);
    }

    @Override
    public State addCard(Card card) {
        Cards copyOfCards = this.cards.add(card);
        return new DealerState(copyOfCards, this.sign);
    }

    @Override
    public boolean canHit() {
        choiceSign();
        return sign.equals(HIT);
    }

    protected void choiceSign() {
        int score = cards.sumScore();
        if (score > DEALER_HIT_LIMIT_SCORE) {
            sign = FINISH;
        }
        if (score > MAX_SCORE) {
            sign = BUST;
        }
        if (score == MAX_SCORE && cards.hasOnlyStartCards()) {
            sign = BLACKJACK;
        }
    }
}
