package DTO;

import domain.card.Card;
import domain.game.GameResult;
import domain.player.Dealer;
import domain.player.Participant;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.joining;

public class DTOFactory {
    private static final String DELIMITER = ", ";

    public static DealerDTO createDealerDTO(Dealer dealer) {
        return new DealerDTO(convertCardsFormat(dealer.showCard()));
    }

    public static ParticipantDTO createParticipantDTO(Participant participant) {
        return new ParticipantDTO(participant.getName(), convertCardsFormat(participant.getCards()));
    }

    public static List<ParticipantDTO> createParticipantDTOs(List<Participant> participants) {
        return participants.stream()
                .map(DTOFactory::createParticipantDTO)
                .collect(toList());
    }

    public static List<FinalResultDTO> createFinalResultDTOs(GameResult gameResult) {
        List<FinalResultDTO> finalResultDTOs = new ArrayList<>();

        finalResultDTOs.addAll(getWinnerResultDTO(gameResult.getWinners()));
        finalResultDTOs.addAll(getDrawParticipantsResultDTO(gameResult.getDrawParticipants()));
        finalResultDTOs.addAll(getLoserDTO(gameResult.getLosers()));

        return finalResultDTOs;
    }

    public static DealerResultDto createDealerResultDTO(List<FinalResultDTO> finalResultDTOs) {
        final int sum = finalResultDTOs.stream()
                .mapToInt(FinalResultDTO::getProfit)
                .sum();
        return new DealerResultDto(sum * -1);
    }

    private static List<FinalResultDTO> getWinnerResultDTO(List<Participant> participants) {
        return participants.stream()
                .map(participant -> new FinalResultDTO(participant.getName(), participant.winBet()))
                .collect(toList());
    }

    private static List<FinalResultDTO> getDrawParticipantsResultDTO(List<Participant> participants) {
        return participants.stream()
                .map(participant -> new FinalResultDTO(participant.getName(), participant.returnBet()))
                .collect(toList());
    }


    private static List<FinalResultDTO> getLoserDTO(List<Participant> participants) {
        return participants.stream()
                .map(participant -> new FinalResultDTO(participant.getName(), participant.loseBet()))
                .collect(toList());
    }

    private static String convertCardsFormat(final List<Card> cards) {
        return cards.stream().map(card -> String.format("%s%s", card.getRank().getSymbol(), card.getSuit())).collect(joining(DELIMITER));
    }
}
