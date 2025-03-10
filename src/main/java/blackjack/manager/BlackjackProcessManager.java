package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.cardholder.CardHolder;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayersResults;
import java.util.List;

public class BlackjackProcessManager {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final Deck deck;
    private final PlayersResults playersResults;

    public BlackjackProcessManager(Deck deck, PlayersResults playersResults) {
        this.deck = deck;
        this.playersResults = playersResults;
    }

    public void giveStartingCardsFor(Dealer dealer) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);
        cards.forEach(dealer::takeCard);
    }

    public void giveStartingCardsFor(Player player) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);
        cards.forEach(player::takeCard);
    }

    public void giveCard(CardHolder cardHolder) {
        List<Card> cards = deck.takeCards(ADDITIONAL_CARD_SIZE);

        cards.forEach(cardHolder::takeCard);
    }

    public void calculateCardResult(Players players, Dealer dealer, GameRuleEvaluator gameRuleEvaluator) {
        for (Player player : players.getPlayers()) {
            saveResult(dealer, gameRuleEvaluator, player);
        }
    }

    private void saveResult(Dealer dealer, GameRuleEvaluator gameRuleEvaluator, Player player) {
        boolean isBustedDealer = gameRuleEvaluator.isBustedFor(dealer);
        boolean isBustedPlayer = gameRuleEvaluator.isBustedFor(player);

        int playerValue = player.getCardHolder().getOptimisticValue();

        if (isBustedDealer) {
            processWhenDealerIsBusted(player, isBustedPlayer, playerValue);
            return;
        }

        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.LOSE, playerValue);
            return;
        }

        GameResultType resultOfPlayer = decideResultOfPlayer(player, dealer);
        saveResultWithPlayerResult(player, resultOfPlayer, playerValue);
    }

    private void processWhenDealerIsBusted(Player player, boolean isBustedPlayer, int playerValue) {
        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.TIE, playerValue);
            return;
        }

        saveResultWithPlayerResult(player, GameResultType.WIN, playerValue);
    }

    public GameResultType decideResultOfPlayer(Player player, Dealer dealer) {
        int playerValue = player.getCardHolder().getOptimisticValue();
        int dealerValue = dealer.getCardHolder().getOptimisticValue();

        return GameResultType.find(playerValue, dealerValue);
    }

    public void saveResultWithPlayerResult(Player player, GameResultType gameResultOfPlayer, int playerValue) {
        PlayerResult playerResult = new PlayerResult(player, gameResultOfPlayer, playerValue);
        playersResults.save(playerResult);
    }

    public DealerResult calculateDealerResult(Dealer dealer) {
        int dealerValue = dealer.getCardHolder().getOptimisticValue();
        DealerResult dealerResult = new DealerResult(dealerValue);

        for (PlayerResult playerResult : playersResults.getAllResult()) {
            GameResultType gameResultOfPlayer = playerResult.getGameResultType();
            GameResultType gameResultOfDealer = gameResultOfPlayer.getOppositeType();

            dealerResult.addCountOf(gameResultOfDealer);
        }

        return dealerResult;
    }

    public List<PlayerResult> getPlayersResult() {
        return playersResults.getAllResult();
    }
}
