package domain;


import constant.GameConstant;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int ACE_ADDITION_NONE_SCORE = 0;
    protected final List<Card> cards = new ArrayList<>();
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public int calculateScore() {
        int total = 0;
        for (Card card : cards) {
            total += card.getCardRank().getNumber();
        }

        return total;
    }

    public int calculateAceScore() {
        if (!isAceExist() || calculateScore() > 11) {
            return ACE_ADDITION_NONE_SCORE;
        }

        return 10;
    }

    public int getFinalResult() {
        return calculateScore() + calculateAceScore();
    }

    public boolean isBust() {
        return getFinalResult() > GameConstant.GAME_OVER_THRESHOLD_SCORE;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void addInitializedCard(Cards totalCards) {
        cards.add(totalCards.pop());
        cards.add(totalCards.pop());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardCount() {
        return cards.size();
    }

    public CardContentDto toCardContentDto() {
        return new CardContentDto(this.name, this.cards);
    }

    public FinalCardDto toFinalCardDto() {
        return new FinalCardDto(this.name, this.cards, getFinalResult());
    }

    public boolean isAceExist() {
        return cards.stream()
                .anyMatch(c -> c.getCardRank().equals(CardRank.ACE));
    }
}
