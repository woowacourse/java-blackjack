package blackjack.domain.participant;

import blackjack.domain.PlayingCards;
import blackjack.dto.DrawResult;

public abstract class Participant {

    private static final int FIRST_DRAW_COUNT = 2;

    protected String nickname;
    protected PlayingCards hand;
    protected Role role;

    public Participant(String nickname, PlayingCards hand, Role role) {
        this.nickname = nickname;
        this.hand = hand;
        this.role = role;
    }

    public boolean isDealer() {
        return role == Role.DEALER;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCardStatus() {
        return hand.getStatusByDisplayName();
    }

    public int getTotalScore() {
        return hand.calculateTotalScore();
    }

    public int getTotalScoreForResult() {
        return hand.calculateTotalScoreForResult();
    }

    public DrawResult distributeCards(PlayingCards deck) {
        DrawResult drawResult = deck.draw(FIRST_DRAW_COUNT);
        receiveCard(drawResult.drewCard());
        return drawResult;
    }

    public PlayingCards receiveCard(PlayingCards drewCards) {
        hand = hand.add(drewCards);
        return hand;
    }
}
