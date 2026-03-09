package team.blackjack.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.domain.Players;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.MatchResult;
import team.blackjack.service.dto.MatchResult.DealerResult;
import team.blackjack.service.dto.MatchResult.PlayerResult;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Player;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class BlackJackService {
    private BlackjackGame blackjackGame;

    public void initGame(List<String> playerNames) {
        final Dealer dealer = new Dealer();
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        blackjackGame = new BlackjackGame(dealer, new Players(players));
    }

    public void drawInitialCards() {
        final Deck deck = blackjackGame.getDeck();
        final Dealer dealer = blackjackGame.getDealer();

        // 플레이어 카드 초기화
        getPlayers().initPlayerHands(deck);

        // 딜러 카드 초기화
        dealer.hit(dealer.draw(deck));
        dealer.hit(dealer.draw(deck));
    }

    public boolean shouldPlayerHit(String name) {
        final Player player = getPlayers().getPlayerByName(name);

        return !DefaultBlackjackRule.isBust(player.getScore());
    }

    public void hitPlayer(String name) {
        getPlayers()
                .getPlayerByName(name)
                .hit(blackjackGame.getDeck().draw());
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
        final List<String> playerNames = getAllPlayerNames();
        final Map<String, List<String>> playerCards = getPlayers().getCardsByPlayer();
        final Map<String, Integer> playerScores = getPlayers().getPlayerScoresByPlayer();

        final Dealer dealer = blackjackGame.getDealer();

        return new ScoreResult(
                dealer.getHand().getCardNames(),
                dealer.getScore(),
                playerNames,
                playerCards,
                playerScores
        );
    }

    public List<String> getAllPlayerNames() {
        return getPlayers().getPlayerNames();
    }

    public List<String> findPlayerCardNamesByName(String name) {
        return getPlayers().getPlayerByName(name).getCardInAllHand();
    }

    public DrawResult getDrawResult() {
        final List<String> playerNames = getAllPlayerNames();
        final List<Card> cards = blackjackGame.getDealer().getHand().getCards();
        final Map<String, List<String>> playerCards = getPlayers().getCardsByPlayer();

        return new DrawResult(playerNames, cards.getFirst().getCardName(), playerCards);
    }

    public MatchResult getGameResult() {
        final Map<String, PlayerResult> playerResults = calculatePlayersResultMap(blackjackGame.getDealer().getScore());
        final DealerResult dealerResult = DealerResult.from(playerResults.values());

        return new MatchResult(dealerResult, playerResults);
    }

    private Map<String, PlayerResult> calculatePlayersResultMap(int dealerScore) {
        return getPlayers().getPlayerList().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> new PlayerResult(DefaultBlackjackRule.judgeResult(player.getScore(), dealerScore)),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }


    private Players getPlayers() {
        return this.blackjackGame.getPlayers();
    }
}
