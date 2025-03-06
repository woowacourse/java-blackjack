package blackjack.domain;

import java.util.List;

public class GameManager {

    private final CardManager cardManager;
    private Players players;

    public GameManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public void registerPlayers(List<Nickname> nicknames) {
        nicknames.add(Nickname.createDealerNickname());
        players = new Players(nicknames);
        cardManager.initialize(nicknames);
    }

    public void distributeCards() {
        cardManager.distributeCards();
    }

    public void hit(Player player) {
        cardManager.addCardByNickname(player.getNickname());
    }

    public int drawDealerCards() {
        int count = 0;
        Player dealer = players.getDealer();
        while (GameRule.shouldDrawCardToDealer(cardManager.calculateSumByNickname(dealer.getNickname()))) {
            cardManager.addCardByNickname(players.getDealer().getNickname());
            count++;
        }
        return count;
    }

    public boolean isBustPlayer(Player player) {
        return GameRule.isBurst(cardManager.calculateSumByNickname(player.getNickname()));
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

}
