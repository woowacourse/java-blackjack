package team.blackjack.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.GameResult;
import team.blackjack.service.dto.GameResult.DealerResult;
import team.blackjack.service.dto.GameResult.PlayerResult;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class BlackJackService {
    private BlackjackGame blackjackGame;

    public void initGame(List<String> playerNames) {
        blackjackGame = new BlackjackGame(playerNames);
    }

    /**
     * 각 게임(라운드)에 앞서 기본 카드를 발급하는 로직
     */
    public void dealInitialCards() {
        for (Player player : blackjackGame.getPlayers()) {
            blackjackGame.dealInitialCardsTo(player);
        }

        final Dealer dealer = blackjackGame.getDealer();
        blackjackGame.dealInitialCardsTo(dealer);
    }

    public DrawResult getHandResult() {
        final List<String> playerNames = blackjackGame.getPlayerNames();
        final List<Card> cards = blackjackGame.getDealer().getHand().getCards();
        final Map<String, List<String>> playerCards = blackjackGame.getAllPlayerCards();

        return new DrawResult(playerNames, cards.getFirst().getCardName(), playerCards);
    }

    public List<Player> getPlayer() {
        return this.blackjackGame.getPlayers();
    }

    public void playerHit(Player player) {
        blackjackGame.dealCardTo(player);
    }

    public void dealerHit() {
        final Dealer dealer = blackjackGame.getDealer();
        blackjackGame.dealCardTo(dealer);
    }

    public boolean shouldDealerHit() {
        return blackjackGame.getDealer().shouldHit();
    }

    public ScoreResult calculateAllParticipantScore() {
        final List<String> playerNames = blackjackGame.getPlayerNames();
        final Map<String, List<String>> playerCards = blackjackGame.getAllPlayerCards();
        final Map<String, Integer> playerScores = blackjackGame.getAllPlayerScores();
        final List<String> dealerCards = blackjackGame.getDealerCards();
        final int dealerScore = blackjackGame.getDealerScore();

        return new ScoreResult(
                dealerCards,
                dealerScore,
                playerNames,
                playerCards,
                playerScores
        );
    }

    public GameResult getGameResult() {
        final int dealerScore = blackjackGame.getDealerScore();
        final Map<String, PlayerResult> playerResults = calculatePlayersResultMap(dealerScore);
        final DealerResult dealerResult = calculateDealerResult(playerResults);

        return new GameResult(dealerResult, playerResults);
    }

    private Map<String, PlayerResult> calculatePlayersResultMap(int dealerScore) {
        return blackjackGame.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> new PlayerResult(DefaultBlackjackRule.judgeResult(player.getScore(), dealerScore)),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private DealerResult calculateDealerResult(Map<String, PlayerResult> playerResults) {
        final List<Result> dealerResults = new ArrayList<>();

        for (PlayerResult playerResult : playerResults.values()) {
            dealerResults.add(playerResult.result().reverse());
        }

        return new DealerResult(dealerResults);
    }
}
