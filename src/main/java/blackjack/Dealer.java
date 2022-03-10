package blackjack;

import blackjack.cards.Cards;
import blackjack.cards.ChangeableCards;

public class Dealer {

    private final ChangeableCards cards;
    private final Card openCard;

    public Dealer(Card card1, Card card2, Card... cards) {
        this.openCard = card1;
        this.cards = Cards.softHandCards(card1, card2, cards);
    }

    public void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }

        cards.take(card);
    }

    public Card openCard() {
        return openCard;
    }

    public Result match(Cards cards) {
        Score playerScore = cards.score();
        if (playerScore.isBust()) {
            return Result.WIN;
        }
        if (score().isBust()) {
            return Result.LOSS;
        }
        return compare(playerScore);
    }

    public Score score() {
        return cards.toMixHand().score();
    }

    private Result compare(Score playerScore) {
        if (score().lessThan(playerScore)) {
            return Result.LOSS;
        }
        if (score().moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isHittable() {
        return cards.score().lessThan(new Score(17));
    }
}
