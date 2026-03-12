package service;

import java.math.BigDecimal;
import model.BlackJackDeck;
import model.Dealer;
import model.DealerWinning;
import model.Participant;
import model.Player;
import model.Players;
import model.PlayersWinning;
import model.Card;
import model.dto.ParticipantWinning;
import model.dto.PlayerWinning;

public class BlackJackService {
    private static final Integer INITIAL_DRAW_QUANTITY = 2;

    private final BlackJackDeck cards;

    public BlackJackService(BlackJackDeck cards) {
        this.cards = cards;
    }

    public void initGame(Dealer dealer, Players players) {
        shuffle();
        initDraw(dealer, players);
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

    private void shuffle() {
        cards.shuffle();
    }

    private void initDraw(Dealer dealer, Players players) {
        for(int i  = 0; i < INITIAL_DRAW_QUANTITY; i++) {
            draw(dealer);
            initPlayersDraw(players);
        }
    }

    private void initPlayersDraw(Players players) {
        for(Player player : players.getPlayers()) {
            draw(player);
        }
    }

    private PlayersWinning getPlayersResult(Players players, Dealer dealer) {
        PlayersWinning playersWinning = new PlayersWinning();

        for(Player player : players.getPlayers()) {
            Integer profit = calculateBetAmount(player, dealer);
            playersWinning.add(new PlayerWinning(player.getName(), profit));
        }

        return playersWinning;
    }

    private DealerWinning getDealerResult(Players players, Dealer dealer) {
        DealerWinning dealerWinning = new DealerWinning();

        for(Player player : players.getPlayers()) {
            Integer betAmount = calculateBetAmount(player, dealer);
            dealerWinning.increase(-betAmount);
        }
        return dealerWinning;
    }

    private Integer calculateBetAmount(Player player, Dealer dealer) {
        return calculateBustBetAmount(player, dealer).intValue();
    }

    private BigDecimal calculateBustBetAmount(Player player, Dealer dealer) {
        if(player.isBust() && dealer.isBust()) {
            return BigDecimal.ZERO;
        }

        if(player.isBust()) {
            return new BigDecimal(-player.getBetAmount());
        }

        if(dealer.isBust()) {
            return new BigDecimal(player.getBetAmount());
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

        if(dealer.isMoreScore(player)) {
            return BigDecimal.valueOf(-player.getBetAmount());
        }
        return BigDecimal.ZERO;
    }
}
