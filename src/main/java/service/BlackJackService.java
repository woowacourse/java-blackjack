package service;

import java.math.BigDecimal;
import model.BlackJackDeck;
import model.Dealer;
import model.DealerWinning;
import model.MatchStatus;
import model.Participant;
import model.Player;
import model.Players;
import model.PlayersWinning;
import model.dto.Card;
import model.dto.ParticipantWinning;
import model.dto.PlayerWinning;

public class BlackJackService {
    private final BlackJackDeck cards;

    public BlackJackService(BlackJackDeck cards) {
        this.cards = cards;
    }

    public void shuffle() {
        cards.shuffle();
    }

    public void draw(Participant participant) {
        Card card = cards.draw();
        participant.addCard(card);
    }

    public ParticipantWinning getGameResult(Players players, Dealer dealer) {
        PlayersWinning playersWinning = getPlayersResult(players, dealer);
        DealerWinning dealerWinning = getDealerResult(players, dealer);

        return new ParticipantWinning(dealerWinning, playersWinning);
    }

    private PlayersWinning getPlayersResult(Players players, Dealer dealer) {
        PlayersWinning playersWinning = new PlayersWinning();

        for(Player player : players.getPlayers()) {
            MatchStatus matchStatus = getPlayerResult(player, dealer);
            Integer profit = calculateBetAmount(player, dealer);
            playersWinning.add(new PlayerWinning(player.getName(), matchStatus.getStatus(), profit));
        }

        return playersWinning;
    }

    private DealerWinning getDealerResult(Players players, Dealer dealer) {
        DealerWinning dealerWinning = new DealerWinning();

        for(Player player : players.getPlayers()) {
            MatchStatus matchStatus = getPlayerResult(player, dealer);
            Integer betAmount = calculateBetAmount(player, dealer);
            dealerWinning.increase(reverseMatchResult(matchStatus), -betAmount);
        }
        return dealerWinning;
    }

    private Integer calculateBetAmount(Player player, Dealer dealer) {
        return calculateBustBetAmount(player, dealer).intValue();
    }

    private BigDecimal calculateBustBetAmount(Player player, Dealer dealer) {
        if(player.isBust()) {
            return new BigDecimal(-player.getBetAmount());
        }

        return calculateBlackJackBetAmount(player, dealer);
    }

    private BigDecimal calculateBlackJackBetAmount(Player player, Dealer dealer) {
        if(player.isBlackJack() && dealer.isBlackJack()) {
            return BigDecimal.ZERO;
        }

        if(player.isBlackJack()) {
            return BigDecimal.valueOf(player.getBetAmount()).multiply(BigDecimal.valueOf(1.5));
        }

        return calculateRegularBetAmount(player, dealer);
    }

    private BigDecimal calculateRegularBetAmount(Player player, Dealer dealer) {
        if(player.isMoreScore(dealer)) {
            return BigDecimal.valueOf(player.getBetAmount());
        }

        if(dealer.isMoreScore(dealer)) {
            return BigDecimal.valueOf(-player.getBetAmount());
        }
        return BigDecimal.ZERO;
    }

    private MatchStatus reverseMatchResult(MatchStatus matchStatus) {
        if(matchStatus.equals(MatchStatus.WIN)) {
            return MatchStatus.LOSE;
        }

        if(matchStatus.equals(MatchStatus.LOSE)) {
            return MatchStatus.WIN;
        }

        return matchStatus;
    }

    private MatchStatus getPlayerResult(Player player, Dealer dealer) {
        if (player.isBust() || (!dealer.isBust() && dealer.isMoreScore(player))) {
            return MatchStatus.LOSE;
        }

        if(!player.isBust() && (player.isMoreScore(dealer) || dealer.isBust())) {
            return MatchStatus.WIN;
        }

        return MatchStatus.DRAW;
    }
}
