package blackjack.dto;

import blackjack.domain.participant.Participant;

import java.util.List;

public record ParticipantHandDto(String name, List<String> hands, int score) {

    public static ParticipantHandDto from(Participant participant) {
        String name = participant.getName();
        List<String> hands = participant.getHand().getCards().stream()
                .map(card -> card.getRankText() + card.getSuit())
                .toList();
        int score = participant.getTotalScore();
        return new ParticipantHandDto(name, hands, score);
    }
}
