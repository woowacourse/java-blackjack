package presentation.dto;

import domain.card.Card;
import domain.member.Dealer;
import domain.member.Player;
import java.util.List;

public record HitInfo(
        String memberName,
        List<String> cards
) {
    public static HitInfo firstCardFrom(Dealer dealer) {
        return new HitInfo(dealer.getName(), getCardNames(dealer.showFirstCards()));
    }

    public static List<HitInfo> firstCardFrom(List<Player> players) {
        return players.stream()
                .map(player ->
                        new HitInfo(
                                player.getName(),
                                getCardNames(player.handCards())
                        )
                ).toList();
    }

    public static HitInfo from(Player player) {
        return new HitInfo(player.getName(), getCardNames(player.handCards()));
    }

    public static List<String> getCardNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCourt() + card.getPattern())
                .toList();
    }
}
