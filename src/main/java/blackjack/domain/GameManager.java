package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final CardManager cardManager;
    private Players players;

    public GameManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public void registerPlayers(List<Nickname> nicknames) {
        players = new Players(new ArrayList<>(nicknames));
        nicknames.add(Nickname.createDealerNickname());
        cardManager.initialize(nicknames);
        // TODO 카드 매니저에는 딜러가 있고 플레이어즈에는 없는 상황
    }

    public void distributeCards() {
        cardManager.distributeCards();
    }

    public void drawAdditionalCard(Player player) {
        cardManager.addCardByNickname(player.getNickname());
    }

    public boolean isBurstPlayer(Player player) {
        return GameRule.isBurst(cardManager.calculateSumByNickname(player.getNickname()));
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

}
