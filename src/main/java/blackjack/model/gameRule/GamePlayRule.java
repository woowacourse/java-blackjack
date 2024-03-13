package blackjack.model.gameRule;

import blackjack.model.card.Card;
import blackjack.model.deck.Deck;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;

public class GamePlayRule {

    protected GamePlayRule() {
    }

    public void cardScoringRule(Deck deck) {
        int aceCount = deck.countElevenAce();

        while (aceCount > 0 && deck.calculateCardScore() >= GameRule.BUST_STANDARD_SCORE) {
            deck.switchAceValueInRow();
            aceCount--;
        }
    }

    public Card dealerInitialCardOpenRule(Dealer dealer) {
        return dealer.allCards().get(0);
    }

    public boolean playerHitRule(Player player) {
        int totalScore = player.totalScore();
        return totalScore <= GameRule.PLAYER_HIT_MAX_SCORE;
    }

    public boolean dealerHitRule(Dealer dealer) {
        int totalScore = dealer.totalScore();
        return totalScore <= GameRule.DEALER_HIT_MAX_SCORE;
    }
}
