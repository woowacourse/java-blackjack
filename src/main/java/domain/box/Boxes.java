package domain.box;

import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class Boxes {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_ABLE = 11;
    private static final BoxStatus INITIAL_BOX_STATUS = new BoxStatus(PlayResult.NOT_BUST, 0);
    private final LinkedHashMap<Player, BoxStatus> boxes = new LinkedHashMap<>();

    private Boxes(List<Player> participants) {
        generateBoxes(participants);
    }

    private void generateBoxes(List<Player> participants) {
        participants.forEach((participant) -> boxes.put(participant, INITIAL_BOX_STATUS));
        boxes.put(new Dealer(), INITIAL_BOX_STATUS);
    }

    public static Boxes of(String playerNamesInput) {
        final String nameDelimiter = ",";
        String[] playerNames = playerNamesInput.split(nameDelimiter);
        List<Player> players = Arrays.stream(playerNames).map(Player::new).collect(Collectors.toList());
        return new Boxes(players);
    }

    public void updatePlayerBox(Player player) {
        int point = player.calculatePoint();
        BoxStatus oldBoxStatus = boxes.get(player);
        BoxStatus newBoxStatus = new BoxStatus(getResultByScore(point), point);
        if (oldBoxStatus.equals(newBoxStatus)) {
            newBoxStatus = new BoxStatus(PlayResult.STAND, point);
        }
        if (point == BLACK_JACK_ABLE && player.hasAce()) {
            boxes.put(player, new BoxStatus(PlayResult.BLACK_JACK, BLACK_JACK));
        }
        boxes.put(player, newBoxStatus);
    }

    public BoxStatus getBoxStatusByParticipant(Player participant) {
        return boxes.get(participant);
    }

    public PlayResult getResultByScore(int score) {
        if (score > BLACK_JACK) {
            return PlayResult.BUST;
        }
        if (score == BLACK_JACK) {
            return PlayResult.BLACK_JACK;
        }
        return PlayResult.NOT_BUST;
    }

    public List<Player> getPlayersAndDealerAtLast() {
        return new ArrayList<>(boxes.keySet());
    }

    public Player getCurrentTurnPlayer() {
        Optional<Entry<Player, BoxStatus>> currentTurnBox = boxes.entrySet()
            .stream()
            .filter(this::isOnTurn)
            .findFirst();
        if (currentTurnBox.isEmpty()) {
            throw new IllegalStateException("더 이상 게임을 진행할 박스가 없습니다.");
        }
        return currentTurnBox.get().getKey();
    }

    private boolean isOnTurn(Entry<Player, BoxStatus> box) {
        return box.getValue().isOnTurn();
    }

    public BoxResult getBoxResult(Player participant) {
        BoxStatus dealerGameStatus = boxes.get(new Dealer());
        BoxStatus playerGameStatus = boxes.get(participant);
        return BoxResult.from(playerGameStatus.compareTo(dealerGameStatus));
    }
}
