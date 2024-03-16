package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.ResultStatus;
import blackjack.utils.Constants;

import java.util.Map;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void start() {
        dealer.shuffleCards();

        dealInitialCards();
    }

    private void dealInitialCards() {
        for (int i = 0; i < Constants.INITIAL_CARD_COUNT; i++) {
            dealCardToDealer();
            dealCardToPlayers();
        }
    }

    public void dealCardToDealer() {
        dealer.receiveCard(pickCard());
    }

    private void dealCardToPlayers() {
        players.receiveCardFrom(dealer);
    }

    public void dealCardTo(final Player player) {
        player.receiveCard(pickCard());
    }

    private Card pickCard() {
        return dealer.pickCard();
    }

    public void dealToPlayerIfHit(final Player player, final BlackjackAction blackjackAction) {
        if (blackjackAction.isHit()) {
            dealCardTo(player);
            return;
        }
        player.stay();
    }

    public Map<Player, ResultStatus> compareDealerAndPlayers() {
        return players.compareTo(dealer);
    }
}
