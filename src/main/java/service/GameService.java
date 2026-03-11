package service;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.player.Player;
import domain.participant.player.PlayerGroup;
import domain.vo.Name;
import dto.DealerInfoDto;
import dto.PlayerInfoDto;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static final int START_CARD_COUNT = 2;
    private PlayerGroup playerGroup;
    private final Dealer dealer = new Dealer(new HandCards());
    private final CardDeck cardDeck = new CardDeck();

    public void registerPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName), new HandCards());
            players.add(player);
        }

        playerGroup = new PlayerGroup(players);
    }

    public void initAllParticipantsCard() {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            dealInitialDealerCards();
            dealInitialPlayersCards();
        }
    }

    private void dealInitialDealerCards() {
        dealer.drawCard(cardDeck.getCard());
    }

    private void dealInitialPlayersCards() {
        while (playerGroup.hasNextPlayer()) {
            playerGroup.onePlayerDrawCard(cardDeck.getCard());
            playerGroup.nextPlayer();
        }
    }

    public DealerInfoDto getDealerInfo() {
        return new DealerInfoDto(dealer.getName(), dealer.getCards(), dealer.getScore(), dealer.getRecord());
    }

    public List<PlayerInfoDto> getAllPlayersInfo() {
        List<PlayerInfoDto> playerInfoDtos = new ArrayList<>();

        for (Player player : playerGroup.getPlayers()) {
            playerInfoDtos.add(new PlayerInfoDto(player.getName(), player.getCards(), player.getScore(), player.getWinStatus()));
        }

        return playerInfoDtos;
    }

    public boolean isRemainPlayer() {
        return playerGroup.hasNextPlayer();
    }

    public String getCurrentPlayerName() {
        return playerGroup.getCurrentPlayer()
                .getName();
    }

    public List<String> getCurrentPlayerCards(){
        return playerGroup.getCurrentPlayerCards();
    }

    public void currentPlayerHit(){
        playerGroup.onePlayerDrawCard(cardDeck.getCard());
    }

    public boolean isCurrentPlayerBust() {
        return playerGroup.getCurrentPlayer().isBust();
    }

    public void nextPlayer() {
        playerGroup.nextPlayer();
    }

    public boolean isDealerHit(){
        if (dealer.isDealerHit()) {
            dealer.drawCard(cardDeck.getCard());
            return true;
        }
        return false;
    }

    public void finalizeGameResult() {
        for (Player player : playerGroup.getPlayers()) {
            player.finalizeResult(dealer.getScore());
            dealer.finalizeResult(player.getScore());
        }
    }

    public int getPlayerGroupSize() {
        return playerGroup.getPlayerGroupSize();
    }

}
