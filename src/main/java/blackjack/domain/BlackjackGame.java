package blackjack.domain;

import java.util.Map;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public StartCardsDTO start() {
        dealer.shuffleDeck();
        dealer.addStartCard();

        int playersCardCount = players.count() * 2;
        players.divideCard(dealer.drawCards(playersCardCount));

        return getStartCards();
    }

    private StartCardsDTO getStartCards() {
        Card dealerCard = dealer.getOpenedCard();
        Map<PlayerName, Hands> playersCard = players.getPlayerHands();

        return StartCardsDTO.of(dealerCard, playersCard);
    }
}
