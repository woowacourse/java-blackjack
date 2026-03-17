package service;

import domain.ProfitInfo;
import domain.PlayedGameResult;
import domain.gameplaying.Participants;
import domain.result.ScoreBoard;
import dto.response.ProfitResponse;
import dto.response.PlayerNamesResponse;
import dto.response.AllPlayersNameAndCardsResponse;
import dto.response.NameAndCardsResponse;
import dto.response.NameResponse;
import dto.response.PlayedGameResultResponse;
import dto.response.PlayerGameResultsResponse;
import java.util.ArrayList;
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
        PlayedGameResult dealerCards = participants.dealerCardInfos();
        return NameAndCardsResponse.from(dealerCards);
    }

    public AllPlayersNameAndCardsResponse AllPlayersCards() {
        List<PlayedGameResult> playerCardInfos = participants.allPlayerCardInfos();
        return AllPlayersNameAndCardsResponse.from(playerCardInfos);
    }

    public NameResponse currentPlayerName() {
        String name = participants.currentPlayerName();
        return new NameResponse(name);
    }

    public NameAndCardsResponse currentPlayerCards() {
        PlayedGameResult playerInfos = participants.currentPlayerCardInfos();
        return NameAndCardsResponse.from(playerInfos);
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

    public List<ProfitResponse> allProfits() {
        List<ProfitResponse> playedGameResults = new ArrayList<>();

        playedGameResults.add(this.dealerPayout());
        playedGameResults.addAll(playerPayouts());

        return playedGameResults;
    }

    private ProfitResponse dealerPayout() {
        PlayedGameResult dealerResult = participants.dealerResult();
        ProfitInfo dealerProfitInfo = scoreBoard.evaluateDealerProfitInfo(dealerResult);
        return payoutResponseFrom(dealerProfitInfo);
    }

    private List<ProfitResponse> playerPayouts() {
        PlayedGameResult dealerResult = participants.dealerResult();
        return scoreBoard.evaluatePlayerProfitInfosWith(dealerResult)
                .stream()
                .map(this::payoutResponseFrom)
                .toList();
    }

    private ProfitResponse payoutResponseFrom(ProfitInfo info) {
        return ProfitResponse.from(info.name(), info.money());
    }
}
