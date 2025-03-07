package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJack {
    private final Players players;
    private final Dealer dealer;

    public BlackJack(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void hitCardsToParticipant() {
        players.hitCards(dealer);
        dealer.addCards();
    }

    public void drawPlayers(Function<Player, Boolean> answer, Consumer<Player> playerDeck) {
        players.draw(answer, playerDeck, dealer);
    }

    public void drawDealer() {
        dealer.draw();
    }

    public Map<Player, MatchResult> calculatePlayerResult() {
        return players.calculateWinner(dealer.sum());
    }
}
