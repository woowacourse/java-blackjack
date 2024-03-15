package model.participant;

import static model.casino.MatchResult.DRAW;
import static model.casino.MatchResult.LOSE;
import static model.casino.MatchResult.WIN;

import model.card.Card;
import model.casino.MatchResult;
import service.dto.FaceUpResult;

public class Player extends Participant {
    private final Name name;
    private boolean isTurnOver;

    public Player(Name name, Card card1, Card card2) {
        super(card1, card2);
        this.name = name;
        this.isTurnOver = false;
    }

    @Override
    public boolean canHit() {
        return !cardDeck.isBust() && !isTurnOver;
    }

    @Override
    public MatchResult calculateMatchResult(int dealerHand) {
        int playerHand = cardDeck.calculateHand();
        if (playerHand >= BUST_THRESHOLD || (dealerHand < BUST_THRESHOLD) && (playerHand < dealerHand)) {
            return LOSE;
        }
        if (dealerHand == playerHand) {
            return DRAW;
        }
        return WIN;
    }

    public void turnOver() {
        isTurnOver = true;
    }

    public FaceUpResult generateFaceUpResult() {
        return new FaceUpResult(name, cardDeck.getCards(), cardDeck.calculateHand());
    }

    public Name getName() {
        return name;
    }
}
