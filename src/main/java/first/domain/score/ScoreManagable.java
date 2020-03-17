package first.domain.score;

import first.domain.card.possessable.CardPossessable;

public interface ScoreManagable {
    Score BLACK_JACK_SCORE = new Score(21);
    Score DEALER_DRAW_THRESHOLD = new Score(16);
    Score ACE_ADDITIONAL_SCORE = new Score(10);

    Calculatable calculate(CardPossessable cardPossessable);
}