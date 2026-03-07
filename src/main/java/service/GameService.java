package service;

import domain.card.CardDeck;
import domain.player.Player;
import domain.player.PlayerGroups;
import domain.vo.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {
    private PlayerGroups playerGroups;
    private CardDeck cardDeck = new CardDeck();

    public void joinPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        players.add(new Player(new Name("딜러")));

        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName));
            players.add(player);
        }

        playerGroups = new PlayerGroups(players);
    }

    public void initAllPlayerCard() {
        for (int i = 0; i < 2; i++) {
            giveAllPlayerCard();
        }
    }

    private void giveAllPlayerCard() {
        while (playerGroups.hasNextPlayer()) {
            playerGroups.drawCard(cardDeck.getCard());
            playerGroups.nextPlayer();
        }
    }

    // 플레이어들 정보 전달
    public Map<String, List<String>> getPlayersStatus() {
        return playerGroups.playersStatus();
    }

    // 이번 라운드에 남은 플레이어가 있는지
    public boolean isRemainPlayer() {
        return playerGroups.hasNextPlayer();
    }

    // 현재 진행 중인 플레이어 이름 보내기 (컨트롤러 -> 뷰)
    public String getCurrentPlayerName() {
        return playerGroups.getCurrentPlayer()
                .getName();
    }

    // 현재 진행 중인 플레이어의 카드 목록 보내기
    public List<String> getCurrentPlayerCards(){
        return playerGroups.getCurrentPlayerCards();
    }

    // 히트
    public void selectHit(){
        playerGroups.drawCard(cardDeck.getCard());
    }

    public boolean isCurrentPlayerBust() {
        if (playerGroups.getCurrentPlayerCardSum() > 21){
            return true;
        }

        return false;
    }

    // 이번 라운드 다음 플레이어로
    public void nextPlayer() {
        playerGroups.nextPlayer();
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }

}
