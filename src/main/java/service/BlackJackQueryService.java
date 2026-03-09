package service;

import domain.vo.DealerWinningScore;
import domain.PlayerWinningInfo;
import domain.vo.NameAndCardInfos;
import dto.response.AllPlayerWinningInfoResponse;
import dto.response.AllPlayersNameAndCardsResponse;
import dto.response.DealerWinningStatisticsResponse;
import dto.response.NameAndCardsResponse;
import dto.response.NameResponse;
import dto.response.PlayedGameResultResponse;
import dto.response.PlayerGameResultsResponse;
import dto.request.PlayerNamesResponse;
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

    public boolean isDealerPlayable() {
        return gameTableRepository.isDealerPlayable();
    }

    public PlayedGameResultResponse dealerResult() {
        return PlayedGameResultResponse.from(gameTableRepository.dealerResult());
    }

    public PlayerGameResultsResponse playerResult() {
        return PlayerGameResultsResponse.from(gameTableRepository.playerResults());
    }

    public DealerWinningStatisticsResponse dealerWinningStatistics() {
        DealerWinningScore winningStatistics = gameTableRepository.getDealerWinningStatistics();
        return new DealerWinningStatisticsResponse(
                winningStatistics.winCount(),
                winningStatistics.drawCount(),
                winningStatistics.loseCount()
        );
    }

    public AllPlayerWinningInfoResponse playerWinningInfos() {
        List<PlayerWinningInfo> playerWinningInfos = gameTableRepository.getPlayerWinningInfos();
        return AllPlayerWinningInfoResponse.of(playerWinningInfos);
    }
}
