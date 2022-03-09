package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.GameResult;

import java.util.List;

public class BlackJackGame {

    private static final int INIT_DISTRIBUTION_COUNT = 2;
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;

    private final CardFactory cardFactory;

    public BlackJackGame() {
        this.cardFactory = new CardFactory(Card.getCards());
    }

    public void initDistribution(Dealer dealer, List<Player> players) {
        for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
            distributeCard(dealer);
            players.forEach(this::distributeCard);
        }
    }

    public void distributeCard(Gamer gamer) {
        gamer.addCard(cardFactory.draw());
    }

    public void distributeAdditionalToDealer(Dealer dealer) {
        while (!dealer.isOverThan(ADDITIONAL_DISTRIBUTE_STANDARD)) {
            distributeCard(dealer);
        }
    }

    public GameResult createResult(Dealer dealer, List<Player> players) {
        return new GameResult(players, dealer);
    }
}
