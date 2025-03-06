package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class BlackJack {
    private final Players players;
    private final Dealer dealer;

    public BlackJack(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void hitCardsToParticipant() {
        players.hitCards(dealer);
        dealer.hitCards();
    }

    public void draw(BooleanSupplier answer, Consumer<Player> playerDeck) {
        players.draw(answer, playerDeck, dealer);
    }
}
