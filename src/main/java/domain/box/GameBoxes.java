package domain.box;

import domain.GameResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerStatus;
import dto.ParticipantDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameBoxes {

    private static final int BLACK_JACK_POINT = 21;
    private static final int NEED_TEN_POINT = 11;
    private final LinkedHashMap<Player, GameBoxInfo> boxes;

    private GameBoxes(LinkedHashMap<Player, GameBoxInfo> boxes) {
        this.boxes = boxes;
    }

    public static GameBoxes of(String playerNamesInput) {
        final String nameDelimiter = ",";
        String[] playerNames = playerNamesInput.split(nameDelimiter);
        List<Player> players = Arrays.stream(playerNames).map(Player::new).collect(Collectors.toList());
        return of(players);
    }

    private static GameBoxes of(List<Player> participants) {
        final GameBoxInfo initialGameBoxInfo = new GameBoxInfo(PlayerStatus.HIT_ABLE, 0);
        LinkedHashMap<Player, GameBoxInfo> boxes = new LinkedHashMap<>();
        participants.forEach((participant) -> boxes.put(participant, initialGameBoxInfo));
        boxes.put(new Dealer(), initialGameBoxInfo);
        return new GameBoxes(boxes);
    }

    public void updatePlayerBox(Player player) {
        int point = player.calculatePoint();
        GameBoxInfo newBoxStatus = new GameBoxInfo(getResultByScore(point), point);
        if (boxes.get(player).equals(newBoxStatus)) {
            newBoxStatus = new GameBoxInfo(PlayerStatus.STAND, point);
        }
        if (point == NEED_TEN_POINT && player.hasAce()) {
            newBoxStatus = new GameBoxInfo(PlayerStatus.BLACK_JACK, BLACK_JACK_POINT);
        }
        boxes.put(player, newBoxStatus);
    }

    public GameBoxInfo getGameBoxInfoBy(Player participant) {
        return boxes.get(participant);
    }

    public PlayerStatus getResultByScore(int score) {
        if (score > BLACK_JACK_POINT) {
            return PlayerStatus.BUST;
        }
        if (score == BLACK_JACK_POINT) {
            return PlayerStatus.BLACK_JACK;
        }
        return PlayerStatus.HIT_ABLE;
    }

    public void setParticipantDTO(ParticipantDTO participantDTO) {
        List<Player> playersAndDealerAtLast = getPlayersAndDealerAtLast();
        participantDTO.setDealer(playersAndDealerAtLast.get(boxes.size() - 1));
        participantDTO.setPlayers(playersAndDealerAtLast.subList(0, boxes.size() - 1));
    }

    public List<Player> getPlayersAndDealerAtLast() {
        return new ArrayList<>(boxes.keySet());
    }

    public Player getCurrentTurnPlayer() {
        ArrayList<Player> players = new ArrayList<>(boxes.keySet());
        Optional<Player> currentTurnPlayer = players.stream()
            .filter((player -> boxes.get(player).isOnTurn()))
            .findFirst();
        if (currentTurnPlayer.isEmpty()) {
            throw new IllegalStateException("더 이상 게임을 진행할 박스가 없습니다.");
        }
        return currentTurnPlayer.get();
    }

    public GameResult getGameResult(Player participant) {
        GameBoxInfo dealerBoxStatus = boxes.get(new Dealer());
        GameBoxInfo playerBoxStatus = boxes.get(participant);
        return GameResult.from(playerBoxStatus.compareTo(dealerBoxStatus));
    }
}
