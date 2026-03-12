package team.blackjack.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class BlackJackService {
    private BlackjackGame blackjackGame;

    public void initGame(Map<String,Integer> playerStakes) {
        blackjackGame = new BlackjackGame(playerStakes);
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
        final Map<String, PlayerResult> playerResults = calculatePlayersResult();
        final DealerResult dealerResult = calculateDealerResult();

        return new GameResult(dealerResult, playerResults);
    }

    /**
     * 내부 헬퍼 메소드
     * @return
     */
    private Map<String, PlayerResult> calculatePlayersResult() {
        final Map<String, PlayerResult> playerResults = new HashMap<>();

        final Dealer dealer = blackjackGame.getDealer();
        final List<Player> players = blackjackGame.getPlayers();

        for (Player player : players) {
            PlayerResult playerResult = new PlayerResult(blackjackGame.getPlayerResult(player, dealer));
            playerResults.put(player.getName(), playerResult);
        }

        return playerResults;
    }

    private DealerResult calculateDealerResult() {
        List<Result> dealerResults = new ArrayList<>();

        final Dealer dealer = blackjackGame.getDealer();
        final List<Player> players = blackjackGame.getPlayers();

        for (Player player : players) {
            Result dealerResult = blackjackGame.getDealerResult(dealer, player);
            dealerResults.add(dealerResult);
        }

        return new DealerResult(dealerResults);
    }
}
