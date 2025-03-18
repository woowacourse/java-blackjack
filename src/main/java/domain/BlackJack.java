package domain;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJack {
    private static final int LOSS_PAYOUT_RATIO = -1;

    private final Players players;
    private final Dealer dealer;

    public BlackJack(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void hitCardsToParticipant(final Deck deck) {
        players.hitCards(deck);
        dealer.addCard(deck.hitCard());
        dealer.addCard(deck.hitCard());
    }

    public void drawPlayers(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, Deck deck) {
        players.draw(answer, playerDeck, deck);
    }

    public void drawDealer(final Deck deck) {
        dealer.draw(deck.hitCard());
    }

    public Map<Player, Integer> calculatePlayerProfit() {
        return players.calculatePlayerProfit(dealer.calculateSum());
    }

    public int calculateDealerProfit() {
        Map<Player, Integer> playerProfit = calculatePlayerProfit();
        int dealerProfit = 0;
        for (Integer profit : playerProfit.values()) {
            dealerProfit += profit * LOSS_PAYOUT_RATIO;
        }
        return dealerProfit;
    }
}
