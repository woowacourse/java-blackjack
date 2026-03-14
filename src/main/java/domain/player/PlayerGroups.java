package domain.player;

import domain.card.Card;
import domain.vo.Name;
import dto.ParticipantResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerGroups {
    private static final int MAX_PLAYER_NUMBER = 4;

    private final List<Player> players;
    private final Dealer dealer = new Dealer();

    public PlayerGroups(List<String> playerNames) {
        validatePlayerNumber(playerNames);
        this.players = createPlayers(playerNames);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public void drawDealerCard(Card card) {
        dealer.addCard(card);
    }

    public List<ParticipantResult> playersStatus() {
        List<ParticipantResult> playerResult = new ArrayList<>();
        for (Player player : players) {
            playerResult.add(new ParticipantResult(player.getName(), player.getCards(), player.getCardSum()));
        }
        return playerResult;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Map<String, WinStatus> getGameResult() {
        Map<String, WinStatus> result = new LinkedHashMap<>();

        for (Player player : players) {
            result.put(player.getName(), getResultOf(player));
        }

        return result;
    }

    private WinStatus getResultOf(Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return WinStatus.BLACKJACK_WIN;
        }

        if (player.isBust()) {
            return WinStatus.LOSE;
        }

        if (dealer.isBust()) {
            return WinStatus.WIN;
        }

        return player.matchResult(dealer.getCardSum());
    }

    public int getPlayerGroupSize() {
        return players.size();
    }

    public ParticipantResult getDealerResult() {
        return new ParticipantResult(dealer.getName(), dealer.getCards(), dealer.getCardSum());
    }

    private List<Player> createPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            players.add(new Player(new Name(playerName)));
        }

        return players;
    }

    private void validatePlayerNumber(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 최대 플레이어 인원은 " + MAX_PLAYER_NUMBER + "명 입니다.");
        }
    }
}
