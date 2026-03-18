package team.blackjack.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Player;
import team.blackjack.domain.Players;
import team.blackjack.domain.Result;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.RevenueResult;
import team.blackjack.service.dto.ScoreResult;

public class BlackjackService {
    private final BlackjackJudge blackjackJudge;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackService(BlackjackJudge blackjackJudge,
                            Players players,
                            Dealer dealer,
                            Deck deck) {
        this.blackjackJudge = blackjackJudge;
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public BlackjackService() {
        this(new BlackjackJudge(),
                new Players(),
                new Dealer(),
                new Deck());
    }

    public void addPlayer(String playerName) {
        players.addPlayer(playerName);
    }

    public void initParticipantCard() {
        players.initPlayerHands(deck);

        dealer.hit(deck.draw());
        dealer.hit(deck.draw());
    }

    public boolean canPlayerHit(String name) {
        return !players.getPlayerByName(name).isBust();
    }

    public boolean shouldDealerHit() {
        return !dealer.isBust();
    }

    public void hitPlayer(String name) {
        final Card drawCard = deck.draw();
        players.getPlayerByName(name).hit(drawCard);
    }

    public void hitDealer() {
        final Card drawCard = deck.draw();
        dealer.hit(drawCard);
    }

    public List<String> getAllPlayerNames() {
        return players.getPlayerNames();
    }

    public List<String> getPlayerCardNames(String name) {
        return players.getPlayerByName(name).getCards().stream()
                .map(Card::getCardName)
                .toList();
    }

    public void batMoney(String name, int battingMoney) {
        players.getPlayerByName(name).bat(battingMoney);
    }

    public ScoreResult getParticipantScoreResult() {
        final Map<String, Integer> playerScores = players.getScoresByPlayer();
        final Map<String, List<String>> playerCards = players.getCardsByPlayer()
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(Card::getCardName)
                                .toList()
                ));

        final int dealerScore = dealer.getScore();
        final List<String> dealerCardNames = dealer.getCards().stream()
                .map(Card::getCardName)
                .toList();

        return new ScoreResult(
                dealerCardNames,
                dealerScore,
                getAllPlayerNames(),
                playerCards,
                playerScores
        );
    }

    public DrawResult getDrawResult() {
        final Set<Card> cards = dealer.getCards();
        final LinkedHashMap<String, List<String>> playerCards = players.getCardsByPlayer()
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(Card::getCardName)
                                .toList(),
                        (result1, result2) -> result1,
                        LinkedHashMap::new
                ));

        final String dealerFirstCardName = cards.stream().findFirst().get().getCardName();
        return new DrawResult(players.getPlayerNames(), dealerFirstCardName, playerCards);
    }

    public RevenueResult getRevenueResult() {
        final LinkedHashMap<Player, Result> playerResultMap = players.getPlayers().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> blackjackJudge.judge(player, dealer),
                        (result1, result2) -> result1,
                        LinkedHashMap::new
                ));
        final double dealerRevenue = blackjackJudge.calculateDealerRevenue(playerResultMap);

        final LinkedHashMap<String, Double> allPlayerRevenue = playerResultMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> blackjackJudge.calculatePlayerRevenue(entry.getKey(), entry.getValue()),
                        (result1, result2) -> result1,
                        LinkedHashMap::new
                ));

        return new RevenueResult(dealerRevenue, allPlayerRevenue);
    }
}
