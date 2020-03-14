package blackjack.controller.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;

import java.util.List;

public class HandResponseDto {

    private static final int DEFAULT_SCORE = 0;

    private String ownerName;
    private List<Card> hand;
    private int score;

    public HandResponseDto(String ownerName, List<Card> hand, int score) {
        this.ownerName = ownerName;
        this.hand = hand;
        this.score = score;
    }

    public static HandResponseDto ofDealer(Dealer dealer) {
        return new HandResponseDto(dealer.getName(), dealer.getHand().subList(0, 1), DEFAULT_SCORE);
    }

    public static HandResponseDto of(Gamer gamer) {
        return new HandResponseDto(gamer.getName(), gamer.getHand(), gamer.calculateSum());
    }

    public String getOwnerName() {
        return ownerName;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }
}
