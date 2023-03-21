package domain.participant;

import domain.card.Card;
import domain.card.Cards;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawPlayer(String playerName, Card card) {
        Player player = players.find(playerName);
        player.hit(card);
    }

    public void drawDealer(Card card) {
        dealer.hit(card);
    }

    public boolean canAddCard(String playerName) {
        Player player = players.find(playerName);
        return player.canAddCard();
    }

    public boolean canAddCardToDealer() {
        return dealer.canAddCard();
    }

    public Cards findCards(String playerName) {
        Player player = players.find(playerName);
        return player.getCards();
    }

    public Card findFirstDealerCard() {
        return dealer.getCards().getFirst();
    }

    public Cards findDealerCards() {
        return dealer.getCards();
    }
}
