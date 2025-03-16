package domain.blackJack;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJack {
    private final int INITIAL_HIT_COUNT = 2;

    private final Players players;
    private final Dealer dealer;

    private final CardDeck standard;

    public BlackJack(final Players players, final Dealer dealer, final CardDeck standard) {
        this.players = players;
        this.dealer = dealer;
        this.standard = standard;
    }

    public void hitCardsToParticipant() {
        for (int i = 0; i < INITIAL_HIT_COUNT; i++) {
            players.hitCard(standard);
            dealer.hitCard(standard);
        }
    }

    public void drawPlayers(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck) {
        players.draw(answer, playerDeck, standard);
    }

    public void drawDealer() {
        dealer.draw(standard);
    }

    public Map<Player, Integer> calculatePlayerResult() {
        return players.calculateProfit(dealer);
    }

    public int calculateDealerResult() {
        Map<Player, Integer> profitOfPlayer = players.calculateProfit(dealer);

        int sum = 0;
        for (Integer value : profitOfPlayer.values()) {
            sum += value;
        }
        return -sum;
    }
}
