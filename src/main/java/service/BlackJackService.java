package service;

import model.CardNumber;
import model.Cards;
import model.Player;
import model.Scorer;
import model.dto.Card;

public class BlackJackService {
    private final Cards cards;
    public BlackJackService(Cards cards) {
        this.cards = cards;
    }

    public void draw(Player player) {
        Card card = cards.draw();
        Integer currentScore = player.getResult().score();

        Integer score = getScore(card, currentScore);

        player.addCard(card);
        player.addScore(score);
    }

    public boolean isBust(Player player) {
        Integer score = player.getResult().score();

        return score > 21;
    }

    private Integer getScore(Card card, Integer score) {
        if(card.cardNumber().equals(CardNumber.ACE)) {
            return Scorer.calculate(card, score);
        }
        return Scorer.calculate(card);
    }
}
