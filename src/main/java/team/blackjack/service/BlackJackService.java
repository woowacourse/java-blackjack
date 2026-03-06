package team.blackjack.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.controler.dto.GameResult;
import team.blackjack.controler.dto.GameResult.DealerResult;
import team.blackjack.controler.dto.GameResult.PlayerResult;
import team.blackjack.controler.dto.ScoreResult;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Card;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Hand;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class BlackJackService {
    private BlackjackGame blackjackGame;

    public void initGame(List<String> playerNames) {
        final Dealer dealer = new Dealer();
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        blackjackGame = new BlackjackGame(dealer, players);
    }


    /**
     * TODO: Draw 위치에 대한 고민 다시 해보기
     */
    public void drawInitialCards() {
        final Deck deck = blackjackGame.getDeck();
        final Dealer dealer = blackjackGame.getDealer();

        // 플레이어 카드 초기화
        for (Player player : blackjackGame.getPlayers()) {
            Hand hand = new Hand();
            hand.addCard(dealer.draw(deck));
            hand.addCard(dealer.draw(deck));

            player.addHand(hand);
        }

        // 딜러 카드 초기화
        Hand dealerHand = dealer.getHand();
        dealerHand.addCard(dealer.draw(deck));
        dealerHand.addCard(dealer.draw(deck));
    }

    public BlackjackGame getBlackjackGame() {
        return this.blackjackGame;
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
        final int dealerScore = calculateSum(dealer.getHand().getCards());

        return new ScoreResult(
                dealer.getHand().getCardNames(),
                dealerScore,
                playerNames,
                playerCards,
                playerScores
        );
    }

    /**
     * 모든 카드를 발급한 이후에, 최종 점수 계산시에 사용하는 함수
     */
    private int calculateSum(List<Card> cards) {
        if (existAceInCards(cards)) {
            var result = cards.stream()
                    .collect(Collectors.partitioningBy(Card::isAce));

            final List<Card> aceCards = result.get(true);
            final List<Card> nonAceCards = result.get(false);
            return calculateBestSumWithAce(nonAceCards, aceCards);
        }
        return calucateBestSumWithoutAce(cards);

    }

    private int calculateBestSumWithAce(List<Card> cardsWithoutAces, List<Card> aceCards) {
        int currentSum = calucateBestSumWithoutAce(cardsWithoutAces);

        for (Card card : aceCards) {
            // 카드가 ace 이고, 11을 사용하기에 적합한경우 11을 더한다.
            if (DefaultBlackjackRule.canUseAceAsEleven(currentSum)) {
                currentSum += card.getScore().getLast();
            } else {
                currentSum += card.getScore().getFirst();
            }
        }

        return currentSum;
    }

    private int calucateBestSumWithoutAce(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.getScore().getFirst())
                .sum();
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
