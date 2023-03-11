package blackjack.domain.cardpack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.game.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Hand {

    private final List<Card> cards;

    public Hand(final Card... cards) {
        this.cards = new ArrayList<>(List.of(cards));
    }

    private Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand addCard(final Card card) {
        cards.add(card);
        return new Hand(new ArrayList<>(cards));
    }

    public Score getScore() {
        Score sumScore = getSumScore();
        final List<Card> aceCards = getAceCards();

        while (!aceCards.isEmpty() && sumScore.isBust()) {
            sumScore = sumScore.minusIfBust();
        }

        return sumScore;
    }

    private Score getSumScore() {
        return new Score(cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getScore)
                .sum());
    }

    private List<Card> getAceCards() {
        return cards.stream()
                .filter(card -> CardNumber.ACE.equals(card.getNumber()))
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return cards;
    }
}
