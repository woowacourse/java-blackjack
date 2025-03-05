package blackjack.domain;

import java.util.List;

public class GameManager {

    private final CardManager cardManager;

    public GameManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public void registerPlayers(List<Nickname> nicknames) {
        nicknames.add(Nickname.createDealer());
        cardManager.initialize(nicknames);
    }

    public void distributeCards() {
        cardManager.distributeCards();
    }
}
