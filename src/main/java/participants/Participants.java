package participants;

import java.util.List;

import blackjackgame.Result;
import card.Card;
import dto.DealerFirstOpenDto;
import dto.DealerWinningDto;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void giveCardToDealer(Card card) {
        dealer.hit(card);
    }

    public void giveCardToPlayerByIndex(int playerIndex, Card card) {
        players.takeCard(playerIndex, card);
    }

    public boolean isBustPlayerByIndex(int playerIndex) {
        return players.isBust(playerIndex);
    }

    public int getPlayersCount() {
        return players.count();
    }

    public boolean canDealerHit() {
        return !dealer.isBust() && dealer.isUnderScore();
    }

    public PlayerResultDto getDealerResult() {
        return PlayerResultDto.from(dealer);
    }

    public List<PlayerResultDto> getPlayerResults() {
        return players.getPlayerResults();
    }

    public void calculateWinning() {
        players.calculateWinning(dealer);
    }

    public DealerWinningDto getDealerWinningResult() {
        return DealerWinningDto.from(dealer);
    }

    public List<PlayerWinningDto> getPlayerWinningResults() {
        return players.getWinningResults();
    }

    public DealerFirstOpenDto getDealerFirstOpen() {
        return DealerFirstOpenDto.from(dealer);
    }

    public List<PlayerOpenDto> getPlayersCards() {
        return players.getPlayerCards();
    }

    public PlayerOpenDto getPlayerCardsByIndex(int playerIndex) {
        return getPlayersCards().get(playerIndex);
    }

    public Name findUserNameByIndex(int playerIndex) {
        return players.findPlayer(playerIndex);
    }

    public Player findPlayerByName(Name name) {
        return players.findPlayer(name);
    }

    public Result findPlayerResultByName(Name name) {
        return findPlayerByName(name).getResult();
    }
}
