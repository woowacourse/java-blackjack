package team.blackjack.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Participant;
import team.blackjack.domain.Player;
import team.blackjack.domain.Players;
import team.blackjack.domain.Result;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.ScoreResult;

public class BlackjackService {
    private final BlackjackJudge blackjackJudge;

    public BlackjackService(BlackjackJudge blackjackJudge) {
        this.blackjackJudge = blackjackJudge;
    }

    public void drawInitialCards(Deck deck, Players players, Dealer dealer) {
        players.initPlayerHands(deck);

        dealer.hit(deck.draw());
        dealer.hit(deck.draw());
    }

    public boolean shouldDealerHit(Dealer dealer) {
        return !dealer.isBust();
    }

    public void hit(Deck deck, Participant participant) {
        final Card drawCard = deck.draw();
        participant.hit(drawCard);
    }

    public ScoreResult calculateAllParticipantScore(Players players, Dealer dealer) {
        final Map<String, Integer> playerScores = players.getPlayerList().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getScore
                ));
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
                getAllPlayerNames(players),
                playerCards,
                playerScores
        );
    }

    public List<String> getAllPlayerNames(Players players) {
        return players.getPlayerNames();
    }


    public void batMoney(Player player, int battingMoney) {
        player.bat(battingMoney);
    }

    public DrawResult getDrawResult(Dealer dealer, Players players) {
        final Set<Card> cards = dealer.getCards();
        final Map<String, List<String>> playerCards = players.getCardsByPlayer()
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(Card::getCardName)
                                .toList()
                ));

        final String dealerAnyCardName = cards.stream().findFirst().get().getCardName();
        return new DrawResult(players.getPlayerNames(), dealerAnyCardName, playerCards);
    }

    public Result judge(Player player, Dealer dealer) {
        return blackjackJudge.judge(player, dealer);
    }

}
