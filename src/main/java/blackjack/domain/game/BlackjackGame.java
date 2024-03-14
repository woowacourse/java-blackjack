package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.strategy.RandomCardShuffleStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void start() {
        dealer.shuffleCards();

        distributeInitialCards();
    }

    private void distributeInitialCards() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeCardToDealer();
            distributeCardToPlayers();
        }
    }

    private void distributeCardToDealer() {
        final Card card = pickCard();
        dealer.receiveCard(card);
    }

    private void distributeCardToPlayers() {
        for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
            final Card card = pickCard();
            players.distributeCardToPlayer(playerIndex, card);
        }
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
