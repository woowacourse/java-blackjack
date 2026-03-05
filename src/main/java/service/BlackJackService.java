package service;

import java.util.Objects;
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
import model.dto.PlayerWinning;

public class BlackJackService {
    private final Cards cards;

    public BlackJackService(Cards cards) {
        this.cards = cards;
    }

    public void draw(Player player) {
        Card card = cards.draw();
        Integer currentScore = player.getResult().score();

        Integer score = getScore(card, currentScore);

        player.addCard(card);
        player.addScore(score);
    }

    public boolean isBust(Participant participant) {
        Integer score = participant.getResult().score();

        return score > 21;
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
            dealerWinning.increase(playerWinning.matchStatus());
        }
        return dealerWinning;
    }

    private Integer getScore(Card card, Integer score) {
        if(card.cardNumber().equals(CardNumber.ACE)) {
            return Scorer.calculate(card, score);
        }
        return Scorer.calculate(card);
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
