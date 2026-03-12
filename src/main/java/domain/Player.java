package domain;


import java.util.ArrayList;
import java.util.List;

import constant.GameConstant;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;

public class Player {
    private static final int ACE_ADDITIONAL_SCORE = 11;
    protected final List<Card> cards = new ArrayList<>();
    private final String name;
    private int bettingScore;

    public Player(String name) {
        this.name = name;
        this.bettingScore = 0;
    }

    protected int calculateScore() {
        int total = 0;
        for (Card card : cards) {
            total += card.getCardRank().getNumber();
        }
        return total;
    }

    public int getFinalScore() {
        return calculateScore() + calculateAceScore();
    }

    private int calculateAceScore() {
        if (!isAceExist() || calculateScore() > ACE_ADDITIONAL_SCORE) {
            return 0;
        }
        return ACE_ADDITIONAL_SCORE - 1;
    }

    public boolean isBust() {
        return getFinalScore() > GameConstant.GAME_OVER_THRESHOLD_SCORE;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void addInitializedCard(Cards totalCards) {
        cards.add(totalCards.pop());
        cards.add(totalCards.pop());
    }

    public void betMoney(int money) {
        bettingScore = money;
    }

    public void loseMoney() {
        int minusScore = bettingScore * 2;
        bettingScore -= minusScore;
    }

    public void calculateBettingScore(Dealer dealer) {
        if (!dealer.isBlackjack() && isBlackjack()) {
            bettingScore = (int) ((int) bettingScore * 1.5);
            return;
        }
        if (isPlayerLose(dealer.isBust(), dealer.getFinalScore())) {
            loseMoney();
        }
    }

    private boolean isPlayerLose(boolean dealerBurst, int dealerTotal) {
        return isBust() || (!dealerBurst && getFinalScore() < dealerTotal);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getBettingScore() {
        return bettingScore;
    }

    public CardContentDto toCardContentDto() {
        return new CardContentDto(this.name, this.cards);
    }

    public FinalCardDto toFinalCardDto() {
        return new FinalCardDto(this.name, this.cards, getFinalScore());
    }

    public boolean isAceExist() {
        return cards.stream()
                .anyMatch(c -> c.getCardRank().equals(CardRank.ACE));
    }

    public boolean isBlackjack() {
        return getFinalScore() == 21 && cards.size() == 2;
    }
}
