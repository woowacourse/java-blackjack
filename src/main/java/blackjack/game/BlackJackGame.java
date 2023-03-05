package blackjack.game;

import blackjack.domain.card.Cards;
import blackjack.domain.player.*;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int INIT_FIRST_CARD = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(List<Name> names) {
        Cards.init();
        players = initializePlayers(names);
        dealer = new Dealer(new Name("딜러"));
        giveFirstCards();
    }

    private Players initializePlayers(List<Name> names) {
        return new Players(names.stream().map(Player::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    private void giveFirstCards() {
        for (Player player : players.getPlayers()) {
            giveFirstCard(player);
        }
        giveFirstCard(dealer);
    }

    private void giveFirstCard(User user) {
        for (int i = 0; i < INIT_FIRST_CARD; i++) {
            user.updateCardScore(Cards.giveFirstCard());
        }
    }

    public void giveOneMoreCard(User user) {
        user.updateCardScore(Cards.giveFirstCard());
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
