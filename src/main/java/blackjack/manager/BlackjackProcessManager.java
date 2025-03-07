package blackjack.manager;

import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.Deck;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.PlayersResult;
import java.util.List;
import java.util.Map;

public class BlackjackProcessManager {
    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final Deck deck;
    private final PlayersResult playersResult;
    private final DealerResult dealerResult;

    public BlackjackProcessManager(Deck deck) {
        this.deck = deck;
        this.playersResult = PlayersResult.create();
        this.dealerResult = DealerResult.create();
    }

    public void giveStartingCards(CardHolder cardHolder) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);
        cards.forEach(cardHolder::takeCard);
    }

    public void giveCard(CardHolder cardHolder) {
        List<Card> card = deck.takeCards(ADDITIONAL_CARD_SIZE);
        card.forEach(cardHolder::takeCard);
    }

    public void calculateCardResult(Players players, Dealer dealer, GameRuleEvaluator gameRuleEvaluator) {
        for (Player player : players.getPlayers()) {
            saveResult(dealer, gameRuleEvaluator, player);
        }
    }

    private void saveResult(Dealer dealer, GameRuleEvaluator gameRuleEvaluator, Player player) {
        boolean isBustedDealer = gameRuleEvaluator.isBusted(dealer);
        boolean isBustedPlayer = gameRuleEvaluator.isBusted(player);

        if (isBustedDealer) {
            processForBustedDealer(player, isBustedPlayer);
            return;
        }

        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.LOSE);
            return;
        }

        GameResultType resultOfPlayer = decideResultOfPlayer(player, dealer);
        saveResultWithPlayerResult(player, resultOfPlayer);
    }

    private void processForBustedDealer(Player player, boolean isBustedPlayer) {
        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.TIE);
            return;
        }

        saveResultWithPlayerResult(player, GameResultType.WIN);
    }

    public GameResultType decideResultOfPlayer(Player player, Dealer dealer) {
        int playerValue = player.getCardHolder().getOptimisticValue();
        int dealerValue = dealer.getCardHolder().getOptimisticValue();

        return GameResultType.find(playerValue, dealerValue);
    }

    public void saveResultWithPlayerResult(Player player, GameResultType gameResultOfPlayer) {
        GameResultType gameResultOfDealer = gameResultOfPlayer.getOppositeType();

        playersResult.save(player, gameResultOfPlayer);
        dealerResult.addCountOf(gameResultOfDealer);
    }

    public Map<Player, GameResultType> getPlayersResult() {
        return playersResult.getAllResult();
    }

    public Map<GameResultType, Integer> getDealerResult() {
        return dealerResult.getDealerResult();
    }

}
