package blackjack.core;

import blackjack.domain.card.CardsGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerGroup;
import java.util.List;

public class BlackjackGame {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final Deck deck;
    private final Dealer dealer;
    private final PlayerGroup playerGroup;

    public BlackjackGame(Deck deck, Dealer dealer, PlayerGroup playerGroup) {
        this.deck = deck;
        this.dealer = dealer;
        this.playerGroup = playerGroup;
    }

    public static BlackjackGame create(CardsGenerator cardsGenerator, String playerNames) {
        return new BlackjackGame(
            Deck.create(cardsGenerator),
            Dealer.create(),
            PlayerGroup.from(playerNames));
    }

    public List<Player> getPlayers() {
        return playerGroup.players();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public void initialDeal() {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            hit(dealer);
            playerGroup.deal(deck);
        }
    }

    public void hit(Participant participant) {
        participant.hit(deck.draw());
    }

    public void hitDealer() {
        hit(dealer);
    }

    public boolean canHit(Player player) {
        return player.canHit();
    }

    public boolean canHitDealer() {
        return dealer.canHit();
    }
}
