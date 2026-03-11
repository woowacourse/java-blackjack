package dto;

import domain.Dealer;
import domain.Participant;

import java.util.List;

public record ResultDto(
        List<String> cards,
        int score
) {
    public static ResultDto from(Participant participant) {
        List<String> cardInfo = participant.getHandCards().stream()
                .map(card -> card.getCardNumber().getSymbol() + card.getCardShape().getName())
                .toList();

        return new ResultDto(cardInfo, participant.getHand().getScore().value());
    }

    public static ResultDto fromDealerInitial(Dealer dealer) {
        List<String> cardInfo = dealer.getHandCards().stream()
                .limit(1)
                .map(card -> card.getCardNumber().getSymbol() + card.getCardShape().getName())
                .toList();

        return new ResultDto(cardInfo, dealer.getHand().getScore().value());
    }

}
