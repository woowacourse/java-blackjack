package service;

import domain.participant.Dealer;
import domain.card.CardDeck;
import domain.participant.HandCards;
import domain.participant.player.Player;
import domain.participant.player.PlayerGroups;
import domain.participant.WinStatus;
import domain.vo.Name;
import dto.DealerInfoDto;
import dto.PlayerInfoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {
    private PlayerGroups playerGroups;
    private Dealer dealer = new Dealer(new HandCards());
    private CardDeck cardDeck = new CardDeck();
    private static final int START_CARD_COUNT = 2;

    public void registerPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName), new HandCards());
            players.add(player);
        }

        playerGroups = new PlayerGroups(players);
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
        while (playerGroups.hasNextPlayer()) {
            playerGroups.onePlayerDrawCard(cardDeck.getCard());
            playerGroups.nextPlayer();
        }
    }

    public DealerInfoDto getDealerInfo() {
        return new DealerInfoDto(dealer.getName(), dealer.getCards(), dealer.getScore(), dealer.getRecord());
    }

    public List<PlayerInfoDto> getAllPlayersInfo() {
        List<PlayerInfoDto> playerInfoDtos = new ArrayList<>();

        for (Player player : playerGroups.getPlayers()) {
            playerInfoDtos.add(new PlayerInfoDto(player.getName(), player.getCards(), player.getScore(), player.getWinStatus()));
        }

        return playerInfoDtos;
    }

    public boolean isRemainPlayer() {
        return playerGroups.hasNextPlayer();
    }

    public String getCurrentPlayerName() {
        return playerGroups.getCurrentPlayer()
                .getName();
    }

    public List<String> getCurrentPlayerCards(){
        return playerGroups.getCurrentPlayerCards();
    }

    public void currentPlayerHit(){
        playerGroups.onePlayerDrawCard(cardDeck.getCard());
    }

    public boolean isCurrentPlayerBust() {
        return playerGroups.getCurrentPlayer().isBust();
    }

    public void nextPlayer() {
        playerGroups.nextPlayer();
    }

    public boolean isDealerHit(){
        if (dealer.isDealerHit()) {
            dealer.drawCard(cardDeck.getCard());
            return true;
        }
        return false;
    }

    public void finalizeGameResult() {
        for (Player player : playerGroups.getPlayers()) {
            player.finalizeResult(dealer.getScore());
            dealer.finalizeResult(player.getScore());
        }
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }

}
