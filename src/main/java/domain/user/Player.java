package domain.user;

import domain.card.Card;
import domain.card.Hand;
import java.util.Objects;

public class Player implements Playable {
    
    public static final int PLAYER_DRAWABLE_BOUNDARY = 21;
    private final PlayerName name;
    
    private Hand hand = new Hand();
    
    public Player(final String name) {
        this.name = new PlayerName(name);
    }
    
    
    @Override
    public void addCard(final Card card) {
        this.hand = this.hand.add(card);
    }
    
    @Override
    public Hand getReadyCards() {
        if (this.hand.size() != 2) {
            throw new IllegalStateException(FIRST_HAND_STATUS_ERROR_MESSAGE);
        }
        return this.hand;
    }
    
    @Override
    public Hand getCards() {
        return this.hand;
    }
    
    @Override
    public boolean isAbleToDraw() {
        return this.hand.calculateScore() < PLAYER_DRAWABLE_BOUNDARY;
    }
    
    @Override
    public MemberStatus getStatus() {
        return MemberStatus.of(this.hand.calculateScore());
    }
    
    @Override
    public int getScore() {
        return this.hand.calculateScore();
    }
    
    @Override
    public boolean isBust() {
        return this.getStatus().isBust();
    }
    
    public String getName() {
        return this.name.getValue();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return this.name.equals(player.name);
    }
    
    public boolean isBlackJack() {
        return this.hand.calculateScore() == PLAYER_DRAWABLE_BOUNDARY;
    }
}
