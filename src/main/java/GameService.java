import domain.Card;
import domain.CardDeck;

import java.util.ArrayList;
import java.util.List;

public class GameService {

    private final CardDeck cardDeck;

    public GameService() {
        this.cardDeck = new CardDeck();
    }

    public int calculateScore(List<String> cards) {
        int score = 0;
        for (String card: cards) {
            if (card.equals("J") || card.equals("Q") || card.equals("K")) {
                score += 10;
            }
            else if (card.equals("A")) {
                score += calculateOptimalAceScore(score);
            }
            else {
                score += Integer.parseInt(card);
            }
        }
        return score;
    }

    public Card deal() {
        return cardDeck.deal();
    }
    

    public boolean isBlackjack(int score) {
        if(score == 21) {
            return true;
        }
        return false;
    }

    public boolean isBurst(int score) {
        if(score > 21) {
            return true;
        }
        return false;
    }

    public boolean isHit(int score) {
        if (score <= 16) {
            return true;
        }
        return false;
    }

    public int calculateOptimalAceScore(int sum) {
        if (sum > 10) {
         return 1;
        }
        return 11;
    }
}
