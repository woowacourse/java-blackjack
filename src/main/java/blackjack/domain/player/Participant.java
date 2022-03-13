package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Judge;
import blackjack.domain.result.Result;

import java.util.List;

public class Participant extends Player {

    public Participant(final List<Card> cards, final String name) {
        super(cards, name);
        validateEmpty(name);
    }

    private void validateEmpty(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 비어있을 수 없습니다.");
        }
    }

    @Override
    public boolean acceptableCard() {
        return cards.calculateScoreByAceOne() <= Cards.getMaxScore();
    }
}
