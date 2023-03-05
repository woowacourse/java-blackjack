package blackjackGame;

import java.util.List;

import deck.Deck;
import player.Dealer;
import player.DealerFirstOpenDto;
import player.DealerWinningDto;
import player.Name;
import player.Player;
import player.PlayerOpenDto;
import player.PlayerResultDto;
import player.PlayerWinningDto;
import player.Players;

public class BlackjackGame {
    public static final int FIRST_DRAW_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void supplyCardsToDealer() {
        dealer.hit(deck.drawCards(FIRST_DRAW_COUNT));
    }

    public void supplyCardsToPlayers() {
        players.takeCard(deck, FIRST_DRAW_COUNT);
    }

    public void supplyAdditionalCard(int playerIndex) {
        players.takeCard(playerIndex, deck.drawCard());
    }

    public boolean isBust(int playerIndex) {
        return players.isBust(playerIndex);
    }

    public int countPlayer() {
        return players.count();
    }

    public boolean canDealerHit() {
        return !dealer.isBust() && dealer.isUnderScore();
    }

    public void supplyAdditionalCardToDealer() {
        dealer.hit(deck.drawCard());
    }

    public PlayerResultDto getDealerResult() {
        return PlayerResultDto.fromDealer(dealer);
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
}
