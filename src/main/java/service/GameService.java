package service;

import domain.card.CardDeck;
import domain.player.Player;
import domain.player.PlayerGroups;
import domain.player.WinStatus;
import domain.vo.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {
    private static final int CARDS_PER_PLAYER_AT_START = 2;
    private static final int MAX_SCORE = 21;
    private static final int DEALER_STAY_THRESHOLD = 17;
    private PlayerGroups playerGroups;
    private final CardDeck cardDeck = new CardDeck();

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
        for (int i = 0; i < CARDS_PER_PLAYER_AT_START; i++) {
            giveAllPlayerCard();
        }
    }

    private void giveAllPlayerCard() {
        while (playerGroups.hasNextPlayer()) {
            playerGroups.drawCard(cardDeck.draw());
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
        playerGroups.drawCard(cardDeck.draw());
    }

    public boolean isCurrentPlayerBust() {
        if (playerGroups.getCurrentPlayerCardSum() > MAX_SCORE) {
            return true;
        }

        return false;
    }

    // 이번 라운드 다음 플레이어로
    public void nextPlayer() {
        playerGroups.nextPlayer();
    }

    // 딜러 히트
    public boolean isDealerHit(){
        if (playerGroups.getDealer().getCardSum() < DEALER_STAY_THRESHOLD) {
            playerGroups.getDealer().addCard(cardDeck.draw());
            return true;
        }
        return false;
    }

    public Map<String, Integer> getPlayersTotalScore() {
        return playerGroups.getPlayerTotalScore();
    }

    public Map<String, WinStatus> result() {
        return playerGroups.getGameResult();
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }

}
