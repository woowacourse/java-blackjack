package service;

import java.util.List;
import java.util.Objects;
import model.BlackJackDeck;
import model.CardNumber;
import model.Cards;
import model.Dealer;
import model.DealerWinning;
import model.MatchStatus;
import model.Participant;
import model.Player;
import model.Players;
import model.PlayersWinning;
import model.Scorer;
import model.dto.Card;
import model.dto.ParticipantWinning;
import model.dto.PlayerResult;
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

        Integer score = getScore(card);

        participant.addCard(card);
        participant.addScore(score);
    }

    public boolean isBust(Participant participant) {
        Integer score = participant.getResult().score();

        return score > 21;
    }

    public void updateFinalScore(Participant participant) {
        Scorer.updateFinalScore(participant);
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
            playersWinning.add(new PlayerWinning(player.getResult().name(), matchStatus));
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

    private Integer getScore(Card card) {
        return Scorer.calculate(card);
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
        if((isBust(dealer) && isBust(player)) || (Objects.equals(player.getResult().score(),
                dealer.getResult().score()))) {
            return MatchStatus.DRAW;
        }

        if(isBust(dealer) || (!isBust(player) && (player.getResult().score() > dealer.getResult().score()))) {
            return MatchStatus.WIN;
        }

        return MatchStatus.LOSE;
    }
}
