package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public class PlayerState extends State{
    private static final String FINISH = "Finish";

    public PlayerState(){
        super();
    }

    private PlayerState(Cards cards, String state) {
        super(cards, state);
    }

    @Override
    public State addCard(Card card) {
        Cards cards = this.cards.add(card);
        return new PlayerState(cards, state);
    }

    @Override
    protected void choiceState() {
        int score = cards.sumScore();
        if (score == 21) {
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
