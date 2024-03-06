package blackjack.domain;

import java.util.Map;

public class BlackjackGame {

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final Players players) {
        this.players = players;
        this.deck = new Deck();
        this.dealer = new Dealer();
    }

    public StartCardsDTO start() {
        deck.shuffle();

        dealer.addCard(deck.pick(2));

        int playersCardCount = players.count() * 2;
        players.divideCard(deck.pick(playersCardCount));

        return getStartCards();
    }

    private StartCardsDTO getStartCards() {
        Card dealerCard = dealer.getOpenedCard();
        Map<PlayerName, Hands> playersCard = players.getPlayerHands();

        return StartCardsDTO.of(dealerCard, playersCard);
    }
}
