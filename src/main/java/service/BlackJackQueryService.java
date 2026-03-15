package service;

import domain.common.NameAndCardInfos;
import domain.common.PlayedGameResult;
import domain.gameplaying.Participants;
import domain.result.DealerWinningScore;
import domain.result.PlayerWinningInfo;
import domain.result.ScoreBoard;
import dto.request.PlayerNamesResponse;
import dto.response.AllPlayerWinningInfoResponse;
import dto.response.AllPlayersNameAndCardsResponse;
import dto.response.DealerWinningStatisticsResponse;
import dto.response.NameAndCardsResponse;
import dto.response.NameResponse;
import dto.response.PlayedGameResultResponse;
import dto.response.PlayerGameResultsResponse;
import java.util.List;

public class BlackJackQueryService {

    private final Participants participants;
    private final ScoreBoard scoreBoard;

    public BlackJackQueryService(Participants participants,
                                 ScoreBoard scoreBoard) {
        this.participants = participants;
        this.scoreBoard = scoreBoard;
    }

    public PlayerNamesResponse allPlayerNames() {
        List<String> allPlayerNames = participants.allPlayerNames();
        return new PlayerNamesResponse(allPlayerNames);
    }

    public NameAndCardsResponse dealerCards() {
        NameAndCardInfos dealerCards = participants.dealerCardInfos();
        return NameAndCardsResponse.from(dealerCards);
    }

    public AllPlayersNameAndCardsResponse AllPlayersCards() {
        List<NameAndCardInfos> playerCardInfos = participants.allPlayerCardInfos();
        return AllPlayersNameAndCardsResponse.from(playerCardInfos);
    }

    public NameResponse currentPlayerName() {
        String name = participants.currentPlayerName();
        return new NameResponse(name);
    }

    public NameAndCardsResponse currentPlayerCards() {
        NameAndCardInfos dealerCards = participants.currentPlayerCardInfos();
        return NameAndCardsResponse.from(dealerCards);
    }

    public boolean isCurrentPlayerPlayable() {
        return participants.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return participants.hasWaitingPlayers();
    }

    public boolean isDealerPlayable() {
        return participants.isDealerPlayable();
    }

    public PlayedGameResultResponse dealerResult() {
        PlayedGameResult dealerResult = participants.dealerResult();
        return PlayedGameResultResponse.from(dealerResult);
    }

    public PlayerGameResultsResponse playerResults() {
        List<PlayedGameResult> playerGameResults = scoreBoard.playerGameResults();
        return PlayerGameResultsResponse.from(playerGameResults);
    }

    public DealerWinningStatisticsResponse dealerWinningStatistics() {
        DealerWinningScore winningStatistics = scoreBoard.dealerWinningScore();
        return new DealerWinningStatisticsResponse(
                winningStatistics.winCount(),
                winningStatistics.drawCount(),
                winningStatistics.loseCount()
        );
    }

    public AllPlayerWinningInfoResponse playerWinningInfos() {
        List<PlayerWinningInfo> playerWinningInfos = scoreBoard.playerWinningInfos();
        return AllPlayerWinningInfoResponse.of(playerWinningInfos);
    }
}
