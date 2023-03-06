package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.state.DrawState;
import blackjack.model.state.State;

import java.util.List;

public class Player extends Participant {

    public static final int FIRST_CARD = 0;
    public static final int SECOND_CARD = 1;

    public Player(Name name, State currentState) {
        super(name, currentState);
    }

    @Override
    public void draw(CardDeck cardDeck) {
        this.currentState = currentState.draw(cardDeck);
    }

    @Override
    public void changeToStand() {
        if (currentState instanceof DrawState) {
            this.currentState = ((DrawState) currentState).turnStandState();
        }
    }

    @Override
    public List<Card> firstDistributedCard() {
        if (!currentState.isCardDistributed()) {
            throw new IllegalStateException("카드를 분배 받지 않은 상태입니다.");
        }
        List<Card> handCards = currentState.getHand();
        return List.of(handCards.get(FIRST_CARD), handCards.get(SECOND_CARD));
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
