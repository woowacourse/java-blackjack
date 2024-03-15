package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.utils.Constants;

import java.util.LinkedHashMap;
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
        final Card card = pickCard();
        dealer.receiveCard(card);
    }

    private void dealCardToPlayers() {
        for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
            final Player player = players.findPlayerByIndex(playerIndex);
            dealCardTo(player);
        }
    }

    public void dealCardTo(final Player player) {
        final Card card = pickCard();
        player.receiveCard(card);
    }

    private Card pickCard() {
        return dealer.pickCard();
    }

    public Map<Player, ResultStatus> judgeGameResult() {
        final Map<Player, ResultStatus> gameResult = new LinkedHashMap<>();

        for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
            final Player player = players.findPlayerByIndex(playerIndex);
            final ResultStatus resultStatus = ResultStatus.of(player, dealer);

            gameResult.put(player, resultStatus);
        }

        return gameResult;
    }
}
