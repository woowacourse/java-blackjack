package blackjackgame;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import deck.Deck;
import dto.DealerFirstOpenDto;
import dto.DealerWinningDto;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;
import player.Dealer;
import player.Name;
import player.Player;
import player.Players;

public class BlackjackGame {
    private static final int FIRST_DRAW_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void addPlayers(List<String> names) {
        validateDuplicatedName(names);
        for (String name : names) {
            addPlayer(new Player(new Name(name)));
        }
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> namesWithoutDuplication = new HashSet<>(names);
        if (namesWithoutDuplication.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void supplyCardsToDealer() {
        for (int i = 0; i < FIRST_DRAW_COUNT; i++) {
            dealer.hit(deck.drawCard());
        }
    }

    public void supplyCardsToPlayers() {
        int playerCount = players.count();
        for (int i = 0; i < playerCount; i++) {
            supplyCardToPlayer(i);
        }
    }

    private void supplyCardToPlayer(int i) {
        for (int j = 0; j < FIRST_DRAW_COUNT; j++) {
            players.takeCard(i, deck.drawCard());
        }
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
}
