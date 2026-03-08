package service;

import domain.DrawStrategy;
import domain.GameTable;
import domain.BlackJackFactory;
import java.util.List;
import repository.GameTableRepository;

public class BlackJackCommandService {

    private final GameTableRepository gameTableRepository;
    private final DrawStrategy drawStrategy;

    public BlackJackCommandService(GameTableRepository gameTableRepository, DrawStrategy drawStrategy) {
        this.gameTableRepository = gameTableRepository;
        this.drawStrategy = drawStrategy;
    }

    public void setupGameTable() {
        GameTable gameTable = BlackJackFactory.basedOn(drawStrategy).openGame();
        gameTableRepository.save(gameTable);
    }

    public void setupPlayers(List<String> names) {
        names.forEach(name -> {
            gameTableRepository.addPlayer(name);
        });
    }
}
