package presentation.dto;

import domain.card.Card;
import domain.member.Member;
import java.util.List;

public record MemberStatus(
        String memberName,
        List<String> cards,
        int totalValue
) {
    public static MemberStatus from(Member member) {
        return new MemberStatus(
                member.getName(),
                getCardNames(member.handCards()),
                member.handValue()
        );
    }

    public static List<MemberStatus> from(List<Member> members) {
        return members.stream()
                .map(member ->
                     new MemberStatus(
                            member.getName(),
                            getCardNames(member.handCards()),
                            member.handValue())
                ).toList();
    }

    public static List<String> getCardNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCourt() + card.getPattern())
                .toList();
    }
}
