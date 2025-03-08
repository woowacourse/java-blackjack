package domain.gamer;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;

import java.util.List;

public class Dealer{
    private static final String DEALER_NAME = "NEO";
    private final int DEALER_HIT_ROLE = 16;

    private final Player player;

    public Dealer(CardGroup cardGroup, CardGenerator cardGenerator) {
        player = new Player(DEALER_NAME,cardGroup,cardGenerator);
    }

    public int calculateScore() {
        return player.calculateScore();
    }

    public void receiveCard(int count){
        player.receiveCard(count);
    }

    public int giveCardsToDealer() {
        int count = 0;
        while (isLessThen(DEALER_HIT_ROLE)) {
            player.receiveCard();
            count++;
        }
        return count;
    }

    public boolean isLessThen(int score) {
        return player.calculateScore() <= score;
    }

    public boolean isBust(){
        return player.isBust();
    }

    public List<Card> getCards(){
        return player.getCards();
    }
}
