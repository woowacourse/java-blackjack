package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private final Players players;
    private final Deck deck;

    public Dealer(final Players players) {
        super(Cards.generateEmptyCards());
        this.players = players;
        this.deck = new Deck();
    }

    public void distributeTwoCards() {
        List<Card> cardList = new ArrayList<>();

        while (cardList.size() < players.size() * 2) {
            Card drawnCard = deck.drawCard();
            cardList.add(drawnCard);
        }
        players.receiveSettingCards(cardList);
    }

    public void drawSelfCards() {
        for (int count = 0; count < 2; count++) {
            Card drawnCard = deck.drawCard();
            receiveCard(drawnCard);
        }
    }

    public void giveOneMoreCard(Player player) {
        player.receiveCard(deck.drawCard());
    }
}
