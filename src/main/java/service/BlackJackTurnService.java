package service;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.card.Card;
import dto.BlackJackHandDto;

public class BlackJackTurnService {

    public void playerHit(Player player, Deck deck) {
        Card card = deck.drawCard();
        player.draw(card);
    }

    public void dealerHit(Dealer dealer, Deck deck) {
        Card card = deck.drawCard();
        dealer.draw(card);
    }

    // todo: 이름 리팩토링
    public boolean isDealerPossible(Dealer dealer){
        return dealer.getHand().getSum() < 17;
    }

    // todo: player.isBurst로 변경
    public boolean isPlayerPossible(Player player, String input){
        return player.getHand().getSum() < 21 && input.equals("y");
    }

    public BlackJackHandDto createHandDto(Player player) {
        return BlackJackHandDto.from(player);
    }
}
