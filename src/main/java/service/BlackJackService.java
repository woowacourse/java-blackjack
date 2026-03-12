package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.BlackJackDeck;
import model.CardFactory;
import model.Dealer;
import model.MatchStatus;
import model.Participant;
import model.Player;
import model.Players;
import dto.Card;
import dto.ParticipantWinning;
import dto.PlayerProfit;

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
            int profit = (int) (player.getBettingMoney().get() * matchStatus.getMultiplier());
            playersWinning.add(new PlayerProfit(player.getResult().name(), profit));
        }

        return playersWinning;
    }

    private Integer getDealerResult(List<PlayerProfit> playersWinning) {
        return Math.negateExact(playersWinning.stream().mapToInt(PlayerProfit::profit).sum());
    }

    private MatchStatus getPlayerResult(Player player, Dealer dealer) {
        MatchStatus blackJackResult = checkBlackJack(player, dealer);
        if (blackJackResult != null) {
            return blackJackResult;
        }

        MatchStatus bustResult = checkBust(player, dealer);
        if (bustResult != null) {
            return bustResult;
        }

        return compareScore(player, dealer);
    }

    private MatchStatus checkBlackJack(Player player, Dealer dealer) {
        if (isBlackJack(player) && isBlackJack(dealer)) {
            return MatchStatus.DRAW;
        }
        if (isBlackJack(player)) {
            return MatchStatus.BLACKJACK;
        }
        if (isBlackJack(dealer)) {
            return MatchStatus.LOSE;
        }
        return null;
    }

    private MatchStatus checkBust(Player player, Dealer dealer) {
        if (isBust(player)) {
            return MatchStatus.LOSE;
        }
        if (isBust(dealer)) {
            return MatchStatus.WIN;
        }
        return null;
    }

    private MatchStatus compareScore(Player player, Dealer dealer) {
        if (Objects.equals(player.getScore(), dealer.getScore())) {
            return MatchStatus.DRAW;
        }
        if (player.getScore() > dealer.getScore()) {
            return MatchStatus.WIN;
        }
        return MatchStatus.LOSE;
    }
}
