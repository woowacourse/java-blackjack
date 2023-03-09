package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.card.HandCard;
import blackjack.model.state.DrawState;
import blackjack.model.state.ParticipantState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Participant {

    public static final int FIRST_CARD = 0;
    public static final int SECOND_CARD = 1;

    public Player(Name name, ParticipantState currentState) {
        super(name, currentState);
    }

    public Player(Name name, ParticipantState state, HandCard handCard) {
        super(name, state, handCard);
    }

    @Override
    public void draw(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck, this.handcard);
    }

    @Override
    public void changeToStand() {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnStandState();
        }
    }

    @Override
    public Map<String, List<Card>> firstDistributedCard() {
        if (handcard.size() == 0) {
            throw new IllegalStateException("카드를 분배 받지 않은 상태입니다.");
        }
        Map<String, List<Card>> firstCardUnits = new HashMap<>();

        List<Card> handCards = handcard.getCards();
        firstCardUnits.put(getName(), List.of(handCards.get(FIRST_CARD), handCards.get(SECOND_CARD)));
        return firstCardUnits;
    }

    @Override
    public ResultState resultState() {
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
