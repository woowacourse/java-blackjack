package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.Status;
import blackjack.domain.card.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Player {
    protected static final int BLACKJACK_SCORE = 21;
    protected static final int START_INDEX = 0;

    protected List<Card> cards = new ArrayList<>();
    protected String name;
    protected Status status;

    public void addCard(CardDeck deck) {
        this.cards.add(deck.pop());
    }

    public void changeStatusIfBlackJack() {
        if (calculateScore() == BLACKJACK_SCORE) {
            this.status = Status.BLACKJACK;
        }
    }

    public void changeStatusIfBust() {
        if (calculateScore() > BLACKJACK_SCORE) {
            this.status = Status.BUST;
        }
    }

    public int calculateScore() {
        int scoreExceptAce = calculateScoreExceptAce();
        return calculateScoreIncludeAce(scoreExceptAce);

//        int score = calculateRawScore();
//        if (hasAce() && score <= ACE_CRITICAL_POINT) {
//            score += ACE_UPPER_POINT - ACE_LOWER_POINT;
//        }
//        return score;
    }

    private int calculateScoreIncludeAce(int scoreExceptAce) {
        List<Card> aceCards = this.cards.stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());

        int score = scoreExceptAce;
        for (Card aceCard : aceCards) {
            score += aceCard.getPointOfAceUsing(score);
        }
        return score;
    }

    private int calculateScoreExceptAce() {
        return this.cards.stream()
                .filter(Predicate.not(Card::isAce))
                .mapToInt(Card::getPoint)
                .sum();
    }

//    private int calculateRawScore() {
//        return cards.stream()
//                .mapToInt(Card::getPoint)
//                .sum();
//    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getCardsSize() {
        return this.cards.size();
    }

    public abstract List<Card> getInitialCards();
}
