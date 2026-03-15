package dto;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;

import java.util.List;

public record ResultDto(
        List<String> cards,
        int score
) {
    public static ResultDto from(Participant participant) {
        List<String> cardInfo = participant.getHandCards().stream()
                .map(Card::getDisplayName)
                .toList();

        return new ResultDto(cardInfo, participant.getScoreValue());
    }

    public static ResultDto fromDealerInitial(Dealer dealer) {
        List<String> cardInfo = dealer.getHandCards().stream()
                .limit(1)
                .map(Card::getDisplayName)
                .toList();

        return new ResultDto(cardInfo, dealer.getScoreValue());
    }

}
