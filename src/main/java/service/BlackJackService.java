package service;

import dto.Card;
import dto.ParticipantWinning;
import dto.PlayerProfit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.BlackJackDeck;
import model.CardFactory;
import model.Dealer;
import model.MatchStatus;
import model.Participant;
import model.Player;
import model.Players;

public class BlackJackService {
    private static final int BUST_NUMBER = 21;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_HAND_SIZE = 2;
    private static final int MIN_DEALER_DRAW_SCORE = 16;

    private final BlackJackDeck cards;

    public BlackJackService() {
        this.cards = new BlackJackDeck(CardFactory.createShuffledCards());
    }

    public void draw(Participant participant) {
        Card card = cards.draw();
        participant.draw(card);
    }

    public boolean isDealerDraw(Dealer dealer) {
        return dealer.getScore() <= MIN_DEALER_DRAW_SCORE;
    }

    public boolean isBust(Participant participant) {
        return participant.getScore() > BUST_NUMBER;
    }

    public boolean isBlackJack(Participant participant) {
        return participant.getHandSize() == BLACKJACK_HAND_SIZE && participant.getScore() == BLACKJACK_SCORE;
    }

    public ParticipantWinning getGameResult(Players players, Dealer dealer) {
        List<PlayerProfit> playersWinning = getPlayersResult(players, dealer);

        return new ParticipantWinning(getDealerResult(playersWinning), playersWinning);
    }

    private List<PlayerProfit> getPlayersResult(Players players, Dealer dealer) {
        List<PlayerProfit> playersWinning = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            MatchStatus matchStatus = getPlayerResult(player, dealer);
            BigDecimal profit = BigDecimal.valueOf(player.getBettingMoney().get())
                    .multiply(matchStatus.getMultiplier());
            playersWinning.add(new PlayerProfit(player.getResult().name(), profit));
        }

        return playersWinning;
    }

    private BigDecimal getDealerResult(List<PlayerProfit> playersWinning) {
        return playersWinning.stream()
                .map(PlayerProfit::profit)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate();
    }

    private MatchStatus getPlayerResult(Player player, Dealer dealer) {
        boolean isPlayerBlackJack = isBlackJack(player);
        boolean isDealerBlackJack = isBlackJack(dealer);
        boolean isPlayerBust = isBust(player);
        boolean isDealerBust = isBust(dealer);
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (isPlayerBlackJack && isDealerBlackJack) {
            return MatchStatus.DRAW;
        }
        if (isPlayerBlackJack) {
            return MatchStatus.BLACKJACK;
        }
        if (isDealerBlackJack) {
            return MatchStatus.LOSE;
        }
        if (isPlayerBust) {
            return MatchStatus.LOSE;
        }
        if (isDealerBust) {
            return MatchStatus.WIN;
        }
        if (playerScore > dealerScore) {
            return MatchStatus.WIN;
        }
        if (playerScore < dealerScore) {
            return MatchStatus.LOSE;
        }
        return MatchStatus.DRAW;
    }
}
