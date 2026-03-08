package domain;


import constant.GameConstant;
import domain.dto.PlayerCardDto;
import domain.dto.FinalCardDto;


public class Player {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int ACE_NO_BONUS = 0;
    protected final Cards cards = new Cards();
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    protected int calculateScore() {
        int total = 0;
        for (Card card : cards) {
            total += card.getCardRank().getValue();
        }

        return total;
    }

    private int calculateAceScore() {
        if (!cards.hasAce() || calculateScore() > 11) {
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
        cards.add(card);
    }

    public void receiveInitialCards(Cards deck) {
        cards.add(deck.pop());
        cards.add(deck.pop());
    }

    public String getName() {
        return name;
    }

    public int getCardCount() {
        return cards.getSize();
    }

    public PlayerCardDto toPlayerCardDto() {
        return new PlayerCardDto(this.name, this.cards.toCardDtos());
    }

    public FinalCardDto toFinalCardDto() {
        return new FinalCardDto(this.name, this.cards.toCardDtos(), getFinalScore());
    }
}
