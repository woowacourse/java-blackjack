package blackjack.domain;

import blackjack.common.ErrorMessage;
import java.util.List;

public class Player implements Participant {

    private final String name;
    private final PlayerHand playerHand;

    public Player(String name, PlayerHand playerHand) {
        validName(name);
        this.name = name;
        this.playerHand = playerHand;
    }

    private void validName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.USE_VALID_NAME.getMessage());
        }
    }

    @Override
    public void takeCard(Card newCard) {
        playerHand.takeCard(newCard);
    }

    @Override
    public boolean canHit() {
        return playerHand.canHit();
    }

    @Override
    public boolean isBusted() {
        return playerHand.isBusted();
    }

    @Override
    public boolean isBlackjack() {
        return playerHand.isBlackjack();
    }

    @Override
    public List<Card> getAllCards() {
        return playerHand.getAllCards();
    }

    @Override
    public int getBestCardValue() {
        return playerHand.getBestCardValue();
    }

    public void adjustBalance(GameResultType gameResultType) {
        playerHand.adjustBalance(gameResultType);
    }

    public int getRevenue() {
        return playerHand.getRevenue();
    }

    public String getName() {
        return name;
    }
}
