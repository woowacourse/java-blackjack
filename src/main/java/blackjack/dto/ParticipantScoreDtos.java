package blackjack.dto;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.ArrayList;
import java.util.List;

public record ParticipantScoreDtos(List<ParticipantScoreDto> scoreDtos) {

    public static ParticipantScoreDtos of(Dealer dealer, List<Player> players) {
        List<ParticipantScoreDto> scoreDtos = new ArrayList<>();

        scoreDtos.add(converScore(dealer));
        players.forEach(player -> scoreDtos.add(converScore(player)));

        return new ParticipantScoreDtos(scoreDtos);
    }

    private static ParticipantScoreDto converScore(Dealer dealer) {
        return ParticipantScoreDto.from(dealer, dealer.getScore());
    }

    private static ParticipantScoreDto converScore(Player player) {
        return ParticipantScoreDto.from(player, player.getScore());
    }
}
