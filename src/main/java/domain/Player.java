package domain;


import java.util.ArrayList;
import java.util.List;

import constant.GameConstant;
import domain.dto.CardContentDto;
import domain.dto.FinalCardDto;

public class Player {
    private static final int ACE_ADDITIONAL_SCORE = 11;

    private final Cards cards;
    private final String name;
    private int bettingScore;

    public Player(String name) {
        this.name = name;
        this.bettingScore = 0;
        this.cards = new Cards();
    }

//    protected int calculateScore() {
//        int total = 0;
//        for (Card card : cards) {
//            total += card.getCardRank().getNumber();
//        }
//        return total;
//    }
//
//    public int getFinalScore() {
//        return calculateScore() + calculateAceScore();
//    }
//
//    private int calculateAceScore() {
//        if (!isAceExist() || calculateScore() > ACE_ADDITIONAL_SCORE) {
//            return 0;
//        }
//        return ACE_ADDITIONAL_SCORE - 1;
//    }

    public boolean isBust() {
        return cards.getFinalScore() > GameConstant.GAME_OVER_THRESHOLD_SCORE;
    }

    private boolean isPlayerLose(boolean dealerBurst, int dealerTotal) {
        return isBust() || (!dealerBurst && cards.getFinalScore() < dealerTotal);
    }

    public void addInitializedCard(Deck deck) {
        add(deck.pop());
        add(deck.pop());
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public void betMoney(int money) {
        bettingScore = money;
    }

    public void loseMoney() {
        int minusScore = bettingScore * 2;
        bettingScore -= minusScore;
    }

    public void calculateBettingScore(boolean isDealerBlackjack) {
        if (!isDealerBlackjack && cards.isBlackjack()) {
            bettingScore = (int) ((int) bettingScore * 1.5);
            return;
        }
//        if (isPlayerLose(dealer.isBust(), dealer.getFinalScore())) {
//            loseMoney();
//        }
    }


    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getBettingScore() {
        return bettingScore;
    }

//    public CardContentDto toCardContentDto() {
//        return new CardContentDto(this.name, this.cards);
//    }
//
//    public FinalCardDto toFinalCardDto() {
//        return new FinalCardDto(this.name, this.cards, getFinalScore());
//    }
}
