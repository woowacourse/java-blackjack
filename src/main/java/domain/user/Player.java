package domain.user;

import domain.card.Card;
import domain.card.Hand;
import java.util.Objects;

public class Player implements Playable {
    
    public static final String FIRST_HAND_STATUS_ERROR_MESSAGE = "처음에는 2장의 카드만 가질 수 있습니다.";
    public static final String EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있습니다.";
    public static final int PLAYER_DRAWABLE_BOUNDARY = 21;
    public static final String INVALID_DEALER_NAME_USAGE_ERROR_MESSAGE = "딜러는 플레이어가 될 수 없습니다.";
    public static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 1자 이상 5자 이하여야 합니다.";
    public static final String DEALER_NAME = "딜러";
    private final String name;
    
    private Hand hand = new Hand();
    
    public Player(final String name) {
        this.validateBlank(name);
        this.validateDealerName(name);
        this.validateNameLength(name);
        this.name = name;
    }
    
    private void validateBlank(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_NAME_ERROR_MESSAGE);
        }
    }
    
    private void validateDealerName(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(INVALID_DEALER_NAME_USAGE_ERROR_MESSAGE);
        }
    }
    
    private void validateNameLength(final String name) {
        if (name.length() > 5 || name.length() < 1) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
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
    public ParticipantStatus getStatus() {
        return ParticipantStatus.of(this.hand.calculateScore());
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
        return this.name;
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
