package service;

import domain.Deck;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;

// todo : 메서드 통합 및 메서드명 수정
public class BlackJackTurnService {
    public boolean hitByPlayer(Player player, String input, Deck deck) {
        if (canPlayerHit(player, input)) {
            Card card = deck.drawCard();
            player.draw(card);
            return true;
        }
        return false;
    }

    public boolean hitByDealer(Dealer dealer, Deck deck) {
        if (canDealerHit(dealer)) {
            Card card = deck.drawCard();
            dealer.draw(card);
            return true;
        }
        return false;
    }

    public boolean isPlayerUnder21(Player player) {
        return player.getScore() < 21;
    }

    private boolean canPlayerHit(Player player, String input) {
        return isPlayerUnder21(player) && input.equals("y");
    }

    private boolean canDealerHit(Dealer dealer) {
        return dealer.getScore() < 17;
    }

}
