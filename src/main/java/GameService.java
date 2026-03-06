import domain.Card;
import domain.CardDeck;
import domain.Dealer;
import domain.User;

import java.util.List;

public class GameService {

    private final CardDeck cardDeck;

    public GameService() {
        this.cardDeck = new CardDeck();
    }

    public void initDeal(List<User> users, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            for (User user : users) {
                user.receiveCard(deal());
            }
            dealer.receiveCard(deal());
        }
    }

    public int calculateScore(List<Card> cards) {
        int score = 0;
        for (Card card: cards) {
            if (card.isAce()) {
                score += calculateOptimalAceScore(score);
            }
            else {
                score += card.getValue();
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
