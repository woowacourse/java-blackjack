package service;

import domain.Dealer;
import domain.Player;
import domain.Players;
import dto.BlackJackResult;
import dto.DrawnCardsInfo;
import dto.ParticipantResult;
import java.util.ArrayList;
import java.util.List;

public class BlackjackResultService {

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

    public List<ParticipantResult> getParticipantsCardsResults(final Players players, final Dealer dealer) {
        List<ParticipantResult> participantResults = new ArrayList<>();
        participantResults.add(ParticipantResult.toDto(dealer));

        players.stream()
                .forEach(player -> participantResults.add(ParticipantResult.toDto(player)));

        return participantResults;
    }

    public List<BlackJackResult> getParticipantAccountResults(final Players players, final Dealer dealer) {
        List<BlackJackResult> blackJackResults = new ArrayList<>();
        blackJackResults.add(BlackJackResult.toDto(dealer));

        players.stream()
                .forEach(player -> blackJackResults.add(BlackJackResult.toDto(player)));

        return blackJackResults;
    }
}
