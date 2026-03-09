package service;

import domain.GameTable;
import domain.BlackJackFactory;
import java.util.List;
import repository.GameTableRepository;

public class BlackJackCommandService {

    private final GameTableRepository gameTableRepository;
    private final BlackJackFactory blackJackFactory;

    public BlackJackCommandService(GameTableRepository gameTableRepository,
                                   BlackJackFactory blackJackFactory) {
        this.gameTableRepository = gameTableRepository;
        this.blackJackFactory = blackJackFactory;
    }

    public void setupGameTable(List<String> playerNames) {
        GameTable gameTable = blackJackFactory.openGame(playerNames);
        gameTableRepository.save(gameTable);
    }

    public void distributeInitialCards() {
        gameTableRepository.distributeInitialCards();
    }

    public void currentPlayerDrawCard() {
        gameTableRepository.currentPlayerDrawCard();
    }

    public void recordCurrentGameResult() {
        gameTableRepository.recordCurrentGameResult();
    }

    public void dealerDrawCard() {
        gameTableRepository.dealerDrawCard();
    }
}
