package service;

import java.util.Objects;
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
    private static final Integer BUST_NUMBER = 21;

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

    public boolean isBust(Participant participant) {
        Integer score = participant.getResult().score();

        return score > BUST_NUMBER;
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
            playersWinning.add(new PlayerWinning(player.getName(), matchStatus.getStatus()));
        }

        return playersWinning;
    }

    private DealerWinning getDealerResult(Players players, Dealer dealer) {
        DealerWinning dealerWinning = new DealerWinning();

        for(Player player : players.getPlayers()) {
            MatchStatus matchStatus = getPlayerResult(player, dealer);
            dealerWinning.increase(reverseMatchResult(matchStatus));
        }
        return dealerWinning;
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
        if((dealer.isBust() && player.isBust()) || (Objects.equals(player.getResult().score(),
                dealer.getResult().score()))) {
            return MatchStatus.DRAW;
        }

        if(dealer.isBust() || !player.isBust() && (player.getResult().score() > dealer.getResult().score())) {
            return MatchStatus.WIN;
        }

        return MatchStatus.LOSE;
    }
}
