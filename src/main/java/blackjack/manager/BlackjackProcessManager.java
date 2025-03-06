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
import java.util.Comparator;
import java.util.List;

public class BlackjackProcessManager {
    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final Deck deck;
    private final PlayersResult playersResult;
    private final DealerResult dealerResult;

    public BlackjackProcessManager(Deck deck, PlayersResult playersResult, DealerResult dealerResult) {
        this.deck = deck;
        this.playersResult = playersResult;
        this.dealerResult = dealerResult;
    }

    public void giveStartingCards(CardHolder cardHolder) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);
        cards.forEach(cardHolder::takeCard);
    }

    public void giveCard(CardHolder cardHolder) {
        List<Card> card = deck.takeCards(ADDITIONAL_CARD_SIZE);
        card.forEach(cardHolder::takeCard);
    }

    public void calculateGameResult(Players players, Dealer dealer, GameRuleEvaluator gameRuleEvaluator) {
        boolean isBustedForDealer = gameRuleEvaluator.isBustedFor(dealer);

        for (Player player : players.getPlayers()) {
            boolean isBustedForPlayer = gameRuleEvaluator.isBustedFor(player);

            if (isBustedForDealer) {
                if (isBustedForPlayer) {
                    saveResultWithPlayerResult(player, GameResultType.TIE);
                    continue;
                }

                saveResultWithPlayerResult(player, GameResultType.WIN);
            }

            if (isBustedForPlayer) {
                saveResultWithPlayerResult(player, GameResultType.LOSE);
            }

            GameResultType resultOfPlayer = decideResultOfPlayer(player, dealer);
            saveResultWithPlayerResult(player, resultOfPlayer);
        }
    }

    public GameResultType decideResultOfPlayer(Player player, Dealer dealer) {
        int playerValue = findOptimisticValue(player.getPossibleSums());
        int dealerValue = findOptimisticValue(dealer.getPossibleSums());

        return GameResultType.find(playerValue, dealerValue);
    }

    public void saveResultWithPlayerResult(Player player, GameResultType gameResultOfPlayer) {
        GameResultType gameResultOfDealer = gameResultOfPlayer.getOppositeType();

        playersResult.save(player, gameResultOfPlayer);
        dealerResult.addCountOf(gameResultOfDealer);
    }

    private int findOptimisticValue(List<Integer> possibleSums) {
        return possibleSums.stream().filter(sum -> sum <= 21)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }
}
