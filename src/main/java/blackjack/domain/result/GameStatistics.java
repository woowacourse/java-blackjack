package blackjack.domain.result;

import blackjack.domain.GameRule;
import blackjack.domain.gamer.GameParticipant;
import blackjack.domain.gamer.GameParticipants;
import blackjack.domain.gamer.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistics {

    private final Map<Player, Integer> playerToSumOfCards;
    private final int dealerSumOfCards;

    private GameStatistics(Map<Player, Integer> playerToSumOfCards, int dealerSumOfCards) {
        this.playerToSumOfCards = playerToSumOfCards;
        this.dealerSumOfCards = dealerSumOfCards;
    }

    public static GameStatistics from(GameParticipants participants) {
        Map<Player, Integer> playerToSumOfCards = new LinkedHashMap<>();

        participants.getPlayers().forEach(player ->
                playerToSumOfCards.put(player, player.calculateSumOfCards())
        );

        int dealerSumOfCards = participants.getDealer().calculateSumOfCards();

        return new GameStatistics(playerToSumOfCards, dealerSumOfCards);
    }

    public int findOriginSumOfCards(GameParticipant participant) {
        if (participant.isPlayer()) {
            return findOriginSumOfCardsByPlayer((Player) participant);
        }

        return findOriginSumOfCardsOfDealer();
    }

    public int applyRule(int originalSumOfCards) {
        return GameRule.applyBustRule(originalSumOfCards);
    }

    public Map<Player, GameResult> decidePlayerResults() {
        Map<Player, GameResult> playerToGameResult = new LinkedHashMap<>();

        playerToSumOfCards.keySet().forEach(player ->
                playerToGameResult.put(player,
                        GameResult.of(
                                applyRule(dealerSumOfCards),
                                applyRule(findOriginSumOfCards(player)))));

        return playerToGameResult;
    }

    public Map<GameResult, Integer> calculateDealerResult(Map<Player, GameResult> playerToGameResult) {
        Map<GameResult, Integer> dealerResult = GameResult.getDealerFormat();

        playerToGameResult.keySet().forEach(player ->
                dealerResult.merge(playerToGameResult.get(player).oppose(), 1, Integer::sum)
        );

        return dealerResult;
    }

    private int findOriginSumOfCardsByPlayer(Player player) {
        if (playerToSumOfCards.containsKey(player)) {
            return playerToSumOfCards.get(player);
        }

        throw new IllegalArgumentException(String.format("해당 플레이어가 없습니다: %s", player));
    }

    private int findOriginSumOfCardsOfDealer() {
        return dealerSumOfCards;
    }
}
