package blackjack.domain.participant;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int SPECIFIC_SCORE_OF_DEALER = 16;

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

    public void drawSelfInitCards() {
        for (int count = 0; count < 2; count++) {
            Card drawnCard = deck.drawCard();
            receiveCard(drawnCard);
        }
    }

    public void giveOneMoreCard(Player player) {
        player.receiveCard(deck.drawCard());
    }

    public Map<Player, Result> decideResult() {
        return players.decideResults(cards.calculateTotalScore());
    }

    public boolean canDraw() {
        return cards.calculateTotalScore() <= SPECIFIC_SCORE_OF_DEALER;
    }

    public void drawOneMoreCard() {
        receiveCard(deck.drawCard());
    }
}
