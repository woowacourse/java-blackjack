package domain;

import java.util.List;

public class Dealer extends Participant {

    public Dealer(Hand hand) {
        super(hand);
    }

    public TrumpCard retrieveFirstCard() {
        List<TrumpCard> cards = hand.getCards();

        if (cards.size() != BlackJackGame.INITIAL_CARD_COUNT) {
            throw new IllegalStateException("딜러는 " + BlackJackGame.INITIAL_CARD_COUNT + "장의 카드를 가지고 있어야 합니다.");
        }

        return cards.getFirst();
    }

    @Override
    public boolean isHitAllowed(Rule rule) {
        return rule.isDealerHitAllowed(super.hand.getCards());
    }

    public GameResult calculateGameResult(Rule rule, List<TrumpCard> playerCards) {
        return rule.evaluateGameResult(playerCards, super.hand.getCards());
    }
}
