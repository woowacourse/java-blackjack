package domain;

import domain.user.AbstractUser;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.List;

public class BlackjackGame {
    private final int INITIAL_CARDS_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(List<String> nameValues) {
        this.dealer = new Dealer();
        this.players = new Players(nameValues);
    }

    public void initGame(Deck deck) {
        addInitialCards(dealer, deck);
        players.getPlayers().forEach(player -> addInitialCards(player, deck));
    }

    private void addInitialCards(AbstractUser user, Deck deck) {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            user.addCard(deck.draw());
        }
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }

    public void addCardToDealerIfPossible(Deck deck) {
        this.dealer.addCard(deck.draw());
    }

    public void calculateAllResults(PlayerResultRepository playerResultRepository) {
        this.players.calculateAllResults(playerResultRepository, this.dealer);
    }
}
