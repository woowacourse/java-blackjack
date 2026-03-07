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
import team.blackjack.domain.Deck;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class BlackJackService {
    private BlackjackGame blackjackGame;

    public void initGame(List<String> playerNames) {
        blackjackGame = new BlackjackGame(playerNames);
    }

    public void drawInitialCards() {
        final Deck deck = blackjackGame.getDeck();
        final Dealer dealer = blackjackGame.getDealer();

        // 플레이어 카드 초기화
        for (Player player : blackjackGame.getPlayers()) {
            player.hit(dealer.draw(deck));
            player.hit(dealer.draw(deck));
        }

        // 딜러 카드 초기화
        dealer.hit(dealer.draw(deck));
        dealer.hit(dealer.draw(deck));
    }

    public List<Player> getPlayer() {
        return this.blackjackGame.getPlayers();
    }

    public void hitPlayer(Player player) {
        player.hit(blackjackGame.getDeck().draw());
    }

    public boolean shouldDealerHit() {
        final int score = blackjackGame.getDealer().getScore();

        return DefaultBlackjackRule.isDealerMustDraw(score);
    }

    public void hitDealer() {
        final Dealer dealer = blackjackGame.getDealer();

        dealer.hit(blackjackGame.getDeck().draw());
    }

    public ScoreResult calculateAllParticipantScore() {
        final List<String> playerNames = blackjackGame.getPlayers().stream()
                .map(Player::getName)
                .toList();

        final Map<String, List<String>> playerCards = blackjackGame.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> player.getHands().getFirst().getCardNames()
                ));

        final Map<String, Integer> playerScores = blackjackGame.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getScore)
                );

        final Dealer dealer = blackjackGame.getDealer();
        final int dealerScore = dealer.getScore();

        return new ScoreResult(
                dealer.getHand().getCardNames(),
                dealerScore,
                playerNames,
                playerCards,
                playerScores
        );
    }

    public DrawResult getHandResult() {
        final List<String> playerNames = blackjackGame.getPlayers().stream()
                .map(Player::getName)
                .toList();
        final List<Card> cards = blackjackGame.getDealer().getHand().getCards();
        final Map<String, List<String>> playerCards = blackjackGame.getAllPlayerCards();

        return new DrawResult(playerNames, cards.getFirst().getCardName(), playerCards);
    }

    public GameResult getGameResult() {
        final Map<String, PlayerResult> playerResults = calculatePlayersResultMap(getDealerScore());
        final DealerResult dealerResult = calculateDealerResult(playerResults);

        return new GameResult(dealerResult, playerResults);
    }

    private int getDealerScore(){
        return blackjackGame.getDealer().getScore();
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
