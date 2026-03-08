package domain;

import constant.GameConstant;
import domain.dto.FinalCardDto;
import domain.dto.PlayerCardDto;

public class Player {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int ACE_NO_BONUS = 0;
    protected final Hand hand = new Hand();
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    protected int calculateScore() {
        int total = 0;
        for (Card card : hand) {
            total += card.getCardRank().getValue();
        }

        return total;
    }

    private int calculateAceScore() {
        if (!hand.hasAce() || calculateScore() > 11) {
            return ACE_NO_BONUS;
        }

        return ACE_BONUS_SCORE;
    }

    public int getFinalScore() {
        return calculateScore() + calculateAceScore();
    }

    public boolean isBust() {
        return getFinalScore() > GameConstant.BUST_THRESHOLD;
    }

    public void add(Card card) {
        hand.add(card);
    }

    public void receiveInitialCards(Deck deck) {
        hand.add(deck.pop());
        hand.add(deck.pop());
    }

    public String getName() {
        return name;
    }

    public int getCardCount() {
        return hand.getSize();
    }

    public PlayerCardDto toPlayerCardDto() {
        return new PlayerCardDto(this.name, this.hand.toCardDtos());
    }

    public FinalCardDto toFinalCardDto() {
        return new FinalCardDto(this.name, this.hand.toCardDtos(), getFinalScore());
    }
}
