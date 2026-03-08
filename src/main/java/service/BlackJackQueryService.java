package service;

import domain.vo.NameAndCardInfos;
import dto.AllPlayersNameAndCardsResponse;
import dto.NameAndCardsResponse;
import dto.NameResponse;
import dto.PlayerNamesResponse;
import java.util.List;
import repository.GameTableRepository;

public class BlackJackQueryService {

    private final GameTableRepository gameTableRepository;

    public BlackJackQueryService(GameTableRepository gameTableRepository) {
        this.gameTableRepository = gameTableRepository;
    }

    public PlayerNamesResponse allPlayerNames() {
        List<String> allPlayerNames = gameTableRepository.getAllPlayerNames();
        return new PlayerNamesResponse(allPlayerNames);
    }

    public NameAndCardsResponse dealerCards() {
        NameAndCardInfos dealerCards = gameTableRepository.getDealerCards();
        return NameAndCardsResponse.from(dealerCards);
    }

    public AllPlayersNameAndCardsResponse AllPlayersCards() {
        List<NameAndCardInfos> playerCardInfos = gameTableRepository.getAllPlayersCards();
        return AllPlayersNameAndCardsResponse.from(playerCardInfos);
    }

    public NameResponse currentPlayerName() {
        String name = gameTableRepository.getCurrentPlayerName();
        return new NameResponse(name);
    }

    public NameAndCardsResponse currentPlayerCards() {
        NameAndCardInfos dealerCards = gameTableRepository.getCurrentPlayerCards();
        return NameAndCardsResponse.from(dealerCards);
    }

    public boolean isCurrentPlayerPlayable() {
        return gameTableRepository.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return gameTableRepository.hasWaitingPlayers();
    }
}
