package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class PlayerState extends State {
    private static final String FINISH = "Finish";
    private static final int MAX_SCORE = 21;

    public PlayerState() {
        super();
    }

    private PlayerState(Cards cards, String state) {
        super(cards, state);
    }

    @Override
    public State addCard(Card card) {
        Cards copyOfCards = this.cards.add(card);
        return new PlayerState(copyOfCards, this.sign);
    }

    @Override
    public boolean canHit() {
        choiceSign();
        return sign.equals(HIT);
    }

    protected void choiceSign() {
        int score = cards.sumScore();
        if (score == MAX_SCORE) {
            this.sign = FINISH;
        }
        if (score > MAX_SCORE) {
            this.sign = BUST;
        }
        if (score == MAX_SCORE && cards.hasOnlyStartCards()) {
            this.sign = BLACKJACK;
        }
    }
}
