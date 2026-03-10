package domain;

import meesage.OutputMessage;

public class Dealer {

    private final Cards cards;

    private Dealer(Cards cards) {
        this.cards = cards;
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public String getDealerInfo() {
        return OutputMessage.DEALER_CARD_INFO.format(OutputMessage.DELIMITER.join(cards.getCardsInfo()));
    }

    public int getScoreOrZeroIfBust(){
        return cards.getScoreOrZeroIfBust();
    }

    public String getDealerInitialInfo() {
        return OutputMessage.DEALER_CARD_INFO.format(cards.getCardsInfo().getFirst());
    }

    public String getDealerScoreInfo() {
        return OutputMessage.RESULT_TEXT.format(getDealerInfo(), cards.calculateScore());
    }

    public boolean shouldHit() {
        return cards.calculateScore() < Policy.DEALER_HIT_THRESHOLD;
    }

    public int getCardSize() {
        return cards.size();
    }
}