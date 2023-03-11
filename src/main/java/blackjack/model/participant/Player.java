package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.Card;
import blackjack.model.card.CardScore;
import blackjack.model.card.HandCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Participant {

    public static final int FIRST_CARD = 0;
    public static final int SECOND_CARD = 1;

    public Player(Name name) {
        super(name);
    }

    public Player(Name name, HandCard handCard) {
        super(name, handCard, 0);
    }

    public Player(Name name) {
        super(name, new HandCard(), 0);
    }

    @Override
    public Map<String, List<Card>> firstDistributedCard() {
        if (handcard.isEmpty()) {
            throw new IllegalStateException("카드를 분배 받지 않은 상태입니다.");
        }
        Map<String, List<Card>> firstCardUnits = new HashMap<>();

        List<Card> handCards = handcard.getCards();
        firstCardUnits.put(getName(), List.of(handCards.get(FIRST_CARD), handCards.get(SECOND_CARD)));
        return firstCardUnits;
    }

    @Override
    public boolean isFinished() {
        return (isBlackjack() || isBust());
    }

    @Override
    protected ResultState resultState() {
        if (isBlackjack()) {
            return ResultState.BLACKJACK;
        }
        if (isBust()) {
            return ResultState.PLAYER_BUST;
        }
        return ResultState.STAND;
    }

    public WinningResult winningResult(CardScore dealerScore) {
        CardScore playerScore = cardScore();
        if (playerScore.compareTo(dealerScore) > 0) {
            return new WinningResult().win();
        }
        if (playerScore.compareTo(dealerScore) < 0) {
            return new WinningResult().lose();
        }
        return new WinningResult().draw();
    }
}
