package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.PlayingCards;

public abstract class Participant {

    private static final int FIRST_DRAW_COUNT = 2;
    protected final String nickname;
    protected final Hand hand;
    
    protected Participant(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }

    public String getResultSnapshot() {
        String info = getInfoSnapshot();
        int scoreSum = hand.getScoreSum();
        return String.format("%s - 결과: %d", info, scoreSum);
    }

    public String getInfoSnapshot() {
        String cardSnapshot = hand.getCardSnapshot();
        return String.format("%s카드: %s", nickname, cardSnapshot);
    }

    public String getNickname() {
        return nickname;
    }

    public String distributeCards(PlayingCards deck) {
        PlayingCards drewCards = deck.drawCards(FIRST_DRAW_COUNT);
        return receiveCard(drewCards);
    }

    public abstract boolean isDrawable();

    public String receiveCard(PlayingCards receivedCards) {
        hand.addCard(receivedCards);
        return getInfoSnapshot();
    }
    public int getTotalScore() {
        return hand.getTotalScore();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }
}
