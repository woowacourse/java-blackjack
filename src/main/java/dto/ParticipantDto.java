package dto;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public record ParticipantDto(
        String name,
        List<String> hand,
        int score
) {

    public static ParticipantDto from(Dealer dealer) {
        return new ParticipantDto(dealer.getName(), convertHandString(dealer.getHand()), dealer.calculateScore());
    }

    public static ParticipantDto from(Dealer dealer, boolean isOnlyFirst) {
        List<String> hand = convertHandString(dealer.getHand());
        if (isOnlyFirst) {
            hand = convertHandString(dealer.getOnlyFirstHand());
        }
        return new ParticipantDto(dealer.getName(), hand, dealer.calculateScore());
    }

    public static ParticipantDto from(Player player) {
        return new ParticipantDto(player.getName(), convertHandString(player.getHand()), player.calculateScore());
    }

    public static List<ParticipantDto> from(Players players) {
        List<ParticipantDto> playersDto = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            List<String> hand = convertHandString(player.getHand());
            playersDto.add(new ParticipantDto(player.getName(), hand, player.calculateScore()));
        }
        return playersDto;
    }

    private static List<String> convertHandString(List<Card> cards) {
        return cards.stream()
                .map(card -> card.rank().getRank() + card.suit().getSuit())
                .toList();
    }
}
