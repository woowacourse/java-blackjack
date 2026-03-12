package service;

import domain.common.NameAndCardInfos;
import domain.common.PlayedGameResult;
import domain.result.DealerWinningScore;
import domain.result.PlayerWinningInfo;
import dto.request.PlayerNamesResponse;
import dto.response.AllPlayerWinningInfoResponse;
import dto.response.AllPlayersNameAndCardsResponse;
import dto.response.DealerWinningStatisticsResponse;
import dto.response.NameAndCardsResponse;
import dto.response.NameResponse;
import dto.response.PlayedGameResultResponse;
import dto.response.PlayerGameResultsResponse;
import java.util.List;
import repository.ParticipantRepository;
import repository.ScoreRepository;

public class BlackJackQueryService {

    private final ParticipantRepository participantRepository;
    private final ScoreRepository scoreRepository;

    public BlackJackQueryService(ParticipantRepository participantRepository,
                                 ScoreRepository scoreRepository) {
        this.participantRepository = participantRepository;
        this.scoreRepository = scoreRepository;
    }

    public PlayerNamesResponse allPlayerNames() {
        List<String> allPlayerNames = participantRepository.getAllPlayerNames();
        return new PlayerNamesResponse(allPlayerNames);
    }

    public NameAndCardsResponse dealerCards() {
        NameAndCardInfos dealerCards = participantRepository.getDealerCards();
        return NameAndCardsResponse.from(dealerCards);
    }

    public AllPlayersNameAndCardsResponse AllPlayersCards() {
        List<NameAndCardInfos> playerCardInfos = participantRepository.getAllPlayersCards();
        return AllPlayersNameAndCardsResponse.from(playerCardInfos);
    }

    public NameResponse currentPlayerName() {
        String name = participantRepository.getCurrentPlayerName();
        return new NameResponse(name);
    }

    public NameAndCardsResponse currentPlayerCards() {
        NameAndCardInfos dealerCards = participantRepository.getCurrentPlayerCards();
        return NameAndCardsResponse.from(dealerCards);
    }

    public boolean isCurrentPlayerPlayable() {
        return participantRepository.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return participantRepository.hasWaitingPlayers();
    }

    public boolean isDealerPlayable() {
        return participantRepository.isDealerPlayable();
    }

    public PlayedGameResultResponse dealerResult() {
        PlayedGameResult dealerResult = participantRepository.getDealerResult();
        return PlayedGameResultResponse.from(dealerResult);
    }

    public PlayerGameResultsResponse playerResults() {
        List<PlayedGameResult> playerGameResults = scoreRepository.getPlayerGameResults();
        return PlayerGameResultsResponse.from(playerGameResults);
    }

    public DealerWinningStatisticsResponse dealerWinningStatistics() {
        DealerWinningScore winningStatistics = scoreRepository.getDealerWinningScore();
        return new DealerWinningStatisticsResponse(
                winningStatistics.winCount(),
                winningStatistics.drawCount(),
                winningStatistics.loseCount()
        );
    }

    public AllPlayerWinningInfoResponse playerWinningInfos() {
        List<PlayerWinningInfo> playerWinningInfos = scoreRepository.getPlayerWinningInfos();
        return AllPlayerWinningInfoResponse.of(playerWinningInfos);
    }
}
