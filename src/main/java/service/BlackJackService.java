package service;

import java.util.Objects;
import model.BlackJackDeck;
import model.Dealer;
import model.DealerWinning;
import constant.MatchStatus;
import model.Participant;
import model.Player;
import model.Players;
import model.PlayersWinning;
import model.Scorer;
import dto.Card;
import dto.ParticipantWinning;
import dto.PlayerWinning;

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
        participant.draw(card);
    }

    public boolean isBust(Participant participant) {
        return participant.getScore() > BUST_NUMBER;
    }

    public void updateAceScore(Participant participant) {
        Scorer.updateAceScore(participant);
    }

    public ParticipantWinning getGameResult(Players players, Dealer dealer) {
        PlayersWinning playersWinning = getPlayersResult(players, dealer);
        DealerWinning dealerWinning = getDealerResult(playersWinning);

        return new ParticipantWinning(dealerWinning, playersWinning);
    }

    private PlayersWinning getPlayersResult(Players players, Dealer dealer) {
        PlayersWinning playersWinning = new PlayersWinning();

        for(Player player : players.getPlayers()) {
            MatchStatus matchStatus = getPlayerResult(player, dealer);
            playersWinning.add(new PlayerWinning(player.getName(), matchStatus));
        }

        return playersWinning;
    }

    private DealerWinning getDealerResult(PlayersWinning playersWinning) {
        DealerWinning dealerWinning = new DealerWinning();

        for(PlayerWinning playerWinning : playersWinning.getPlayersWinnings()) {
            dealerWinning.increase(reverseMatchResult(playerWinning.matchStatus()));
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
        if((isBust(dealer) && isBust(player)) || (Objects.equals(player.getScore(), dealer.getScore()))) {
            return MatchStatus.DRAW;
        }

        if(isBust(dealer) || (!isBust(player) && (player.getScore() > dealer.getScore()))) {
            return MatchStatus.WIN;
        }

        return MatchStatus.LOSE;
    }
}
