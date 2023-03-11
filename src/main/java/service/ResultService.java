package service;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;
import dto.CardsResultsWithScore;
import dto.DrawnCardsInfo;
import dto.ParticipantAccountResult;
import java.util.ArrayList;
import java.util.List;

public class ResultService {

    public List<DrawnCardsInfo> makeAfterSplitsInfos(final Players players, final Dealer dealer) {
        return getDrawnCardsInfos(players, dealer);
    }

    private List<DrawnCardsInfo> getDrawnCardsInfos(final Players players, final Dealer dealer) {
        List<DrawnCardsInfo> cardInfos = new ArrayList<>();
        addDealerCardInfo(dealer, cardInfos);

        players.stream()
                .forEach(player -> cardInfos.add(DrawnCardsInfo.toDto(player)));

        return cardInfos;
    }

    private void addDealerCardInfo(final Dealer dealer,
                                   final List<DrawnCardsInfo> cardInfos) {
        cardInfos.add(DrawnCardsInfo.toDto(dealer));
    }

    public DrawnCardsInfo drawCards(final Player player) {
        return DrawnCardsInfo.toDto(player);
    }

    public List<CardsResultsWithScore> getParticipantsCardResultsWithScore(final Players players, final Dealer dealer) {
        List<CardsResultsWithScore> cardsResultWithScores = new ArrayList<>();
        cardsResultWithScores.add(CardsResultsWithScore.toDto(dealer));

        players.stream()
                .forEach(player -> cardsResultWithScores.add(CardsResultsWithScore.toDto(player)));

        return cardsResultWithScores;
    }

    public List<ParticipantAccountResult> getParticipantAccountResults(final Players players, final Dealer dealer) {
        List<ParticipantAccountResult> participantAccountResults = new ArrayList<>();
        participantAccountResults.add(ParticipantAccountResult.toDto(dealer));

        players.stream()
                .forEach(player -> participantAccountResults.add(ParticipantAccountResult.toDto(player)));

        return participantAccountResults;
    }
}
