package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.dto.DrawResult;
import java.util.List;

public abstract class Participant {

    private static final int FIRST_DRAW_COUNT = 2;

    protected String nickname;
    protected Hand hand;
    protected Role role;

    public Participant(String nickname, Hand hand, Role role) {
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

    public DrawResult distributeCards(Deck deck) {
        DrawResult drawResult = deck.draw(FIRST_DRAW_COUNT);
        receiveCard(drawResult.drewCard().getCards());
        return drawResult;
    }

    public Hand receiveCard(List<Card> drewCards) {
        hand = hand.add(drewCards);
        return hand;
    }
}
