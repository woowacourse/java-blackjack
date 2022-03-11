package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class User extends Player {

    private static final int MAX_SCORE_TO_PICK = 21;
    private static final int FIRST_OPEN_COUNT = 2;

    public User(String name, Cards cards) {
        super(name, cards);
    }

    public ResultType findResult(int dealerScore) {
        return ResultType.findUserResult(calculateScore(), dealerScore);
    }

    @Override
    public List<Card> openFirstCards() {
        return cards.getCards().subList(0, FIRST_OPEN_COUNT);
    }

    @Override
    public boolean isPossibleToReceiveCard() {
        return cards.calculateScore() <= MAX_SCORE_TO_PICK;
    }
}
