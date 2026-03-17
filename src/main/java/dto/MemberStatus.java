package dto;

import domain.card.Card;

import java.util.List;

import static domain.member.MemberInfo.DEALER_NAME;

public record MemberStatus(
        String memberName,
        List<Card> cards,
        int totalValue
) {
    public boolean isDealer() {
        return memberName.equals(DEALER_NAME);
    }
}
