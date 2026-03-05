package team.blackjack.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.control.dto.ScoreResult;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Card;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Hand;
import team.blackjack.domain.Player;
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

    public void draw(BlackjackGame blackjackGame, Player player) {
        final Deck deck = blackjackGame.getDeck();
        final Card drawedCard = deck.draw();
        player.getHands().getFirst().addCard(drawedCard);
    }

    public boolean shouldDealerHit() {
        final Dealer dealer = blackjackGame.getDealer();
        final Hand hand = dealer.getHand();
        final int score = calculateSum(hand.getCards());

        return DefaultBlackjackRule.isDealerMustDraw(score);
    }

    public int hitDealer() {
        Dealer dealer = blackjackGame.getDealer();
        Hand hand = dealer.getHand();
        hand.addCard(dealer.draw(blackjackGame.getDeck()));
        return calculateSum(hand.getCards());
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
                        player -> calculateSum(player.getHands().getFirst().getCards())
                ));

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

    private boolean existAceInCards(List<Card> cards) {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }
}
