package presentation.dto;

import domain.card.Card;
import domain.member.Dealer;
import domain.member.Player;
import java.util.List;

public record MemberStatus(
        String memberName,
        List<String> cards,
        int totalValue
) {
    public static MemberStatus from(Dealer dealer) {
        return new MemberStatus(
                dealer.getName(),
                getCardNames(dealer.handCards()),
                dealer.handValue()
        );
    }

    public static List<MemberStatus> from(List<Player> players) {
        return players.stream()
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
