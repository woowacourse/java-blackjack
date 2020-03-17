package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public abstract class GameResultStrategy {
    public boolean fulfill(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        boolean enough = enough(gamblerCardBundle, dealerCardBundle);
        if (enough) {
            return true;
        }
        int compare = Integer.compare(gamblerCardBundle.calculateScore(), dealerCardBundle.calculateScore());
        return isMatch(compare);
    }

    protected abstract boolean enough(CardBundle gamblerCardBundle, CardBundle dealerCardBundle);

    protected abstract boolean isMatch(int compare);
}
