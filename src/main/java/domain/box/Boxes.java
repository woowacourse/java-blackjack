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

    private static final int BLACK_JACK_POINT = 21;
    private static final int NEED_TEN_POINT = 11;
    private static final BoxStatus INITIAL_BOX_STATUS = new BoxStatus(PlayResult.NOT_BUST, 0);
    private final LinkedHashMap<Player, BoxStatus> boxes;

    private Boxes(LinkedHashMap<Player, BoxStatus> boxes) {
        this.boxes = boxes;
    }

    public static Boxes of(String playerNamesInput) {
        final String nameDelimiter = ",";
        String[] playerNames = playerNamesInput.split(nameDelimiter);
        List<Player> players = Arrays.stream(playerNames).map(Player::new).collect(Collectors.toList());
        return of(players);
    }

    private static Boxes of(List<Player> participants) {
        LinkedHashMap<Player, BoxStatus> boxes = new LinkedHashMap<>();
        participants.forEach((participant) -> boxes.put(participant, INITIAL_BOX_STATUS));
        boxes.put(new Dealer(), INITIAL_BOX_STATUS);
        return new Boxes(boxes);
    }

    public void updatePlayerBox(Player player) {
        int point = player.calculatePoint();
        BoxStatus newBoxStatus = new BoxStatus(getResultByScore(point), point);
        if (boxes.get(player).equals(newBoxStatus)) {
            newBoxStatus = new BoxStatus(PlayResult.STAND, point);
        }
        if (point == NEED_TEN_POINT && player.hasAce()) {
            newBoxStatus = new BoxStatus(PlayResult.BLACK_JACK, BLACK_JACK_POINT);
        }
        boxes.put(player, newBoxStatus);
    }

    public BoxStatus getBoxStatusByParticipant(Player participant) {
        return boxes.get(participant);
    }

    public PlayResult getResultByScore(int score) {
        if (score > BLACK_JACK_POINT) {
            return PlayResult.BUST;
        }
        if (score == BLACK_JACK_POINT) {
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
            .filter(this::isOnTurnPlayer)
            .filter((entry) -> entry.getKey().isPlayer())
            .findFirst();
        if (currentTurnBox.isEmpty()) {
            throw new IllegalStateException("더 이상 게임을 진행할 박스가 없습니다.");
        }
        return currentTurnBox.get().getKey();
    }

    private boolean isOnTurnPlayer(Entry<Player, BoxStatus> box) {
        return box.getValue().isOnTurn() && box.getKey().isPlayer();
    }

    public BoxResult getBoxResult(Player participant) {
        BoxStatus dealerBoxStatus = boxes.get(new Dealer());
        BoxStatus playerBoxStatus = boxes.get(participant);
        return BoxResult.from(playerBoxStatus.compareTo(dealerBoxStatus));
    }
}
