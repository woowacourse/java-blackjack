package domain;

import domain.dto.FinalCardDto;
import domain.dto.PlayerCardDto;

public class Player {
    protected final Hand hand = new Hand();
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public int getFinalScore() {
        return hand.calculateFinalScore();
    }

    public boolean isBust() {
        return hand.isBust();
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
