package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantCardsScoreDto;
import blackjack.dto.PlayerWinningResultDto;
import blackjack.dto.WinningResultCountDto;
import blackjack.dto.WinningResultDto;
import blackjack.view.format.CardNumberFormat;
import blackjack.view.format.CardShapeFormat;
import blackjack.view.format.DealerFormat;
import blackjack.view.format.WinningStatusFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

    public void printStartCards(final List<ParticipantCardsDto> participantCardsDtos) {
        final List<ParticipantCardsDto> playersCard = getExceptDealer(participantCardsDtos, ParticipantCardsDto::participantName);

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", DealerFormat.DEALER.getFormat(),
                String.join(", ", convertToNames(playersCard)));

        printDealerCardScore(getOnlyDealer(participantCardsDtos, ParticipantCardsDto::participantName));
        playersCard.forEach(this::printPlayerCard);
    }

    private List<String> convertToNames(final List<ParticipantCardsDto> playersCard) {
        return playersCard.stream()
                .map(ParticipantCardsDto::participantName)
                .toList();
    }

    public void printDealerCardScore(ParticipantCardsDto participantCardsDto) {
        System.out.printf("%s카드: %s%n", DealerFormat.DEALER.getFormat(), convertToCardsFormat(participantCardsDto.cardDtos()));
    }
    public void printPlayerCard(ParticipantCardsDto participantCardsDto) {
        System.out.printf("%s카드: %s%n", participantCardsDto.participantName(), convertToCardsFormat(participantCardsDto.cardDtos()));
    }

    private String convertToCardsFormat(final List<CardDto> cards) {
        return String.join(", ", cards.stream().map(this::convertToCardFormat).toList());
    }

    private String convertToCardFormat(final CardDto cardDto) {
        return CardNumberFormat.valueOf(cardDto.number()).getFormat() +
                CardShapeFormat.valueOf(cardDto.shape()).getFormat();
    }

    public void printDealerMoreCard(int count) {
        while (count-- > 0) {
            System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%n", DealerFormat.DEALER.getFormat());
        }
    }

    public void printFinalResult(final List<ParticipantCardsScoreDto> participantCardsScoreDtos,
                                 final WinningResultDto winningResultDto) {
        printCardScore(participantCardsScoreDtos);
        printWinningResult(winningResultDto);
    }

    private void printCardScore(final List<ParticipantCardsScoreDto> participantCardsScoreDtos) {
        printDealerCardScore(getOnlyDealer(participantCardsScoreDtos,
                participantCardsDto -> participantCardsDto.participantCardsDto().participantName()));
        printPlayersCardScore(getExceptDealer(participantCardsScoreDtos,
                playersCardScoreDto -> playersCardScoreDto.participantCardsDto().participantName()));
    }

    private void printDealerCardScore(final ParticipantCardsScoreDto dealerCardScore) {
        System.out.printf("%n%s 카드: %s - 결과 : %d%n",
                DealerFormat.DEALER.getFormat(),
                convertToCardsFormat(dealerCardScore.participantCardsDto().cardDtos()),
                dealerCardScore.score());
    }

    private void printPlayersCardScore(final List<ParticipantCardsScoreDto> playersCardScores) {
        playersCardScores.forEach(this::printPlayerCardScore);
    }

    private void printPlayerCardScore(ParticipantCardsScoreDto playersCardScore) {
        System.out.printf("%s 카드: %s - 결과 : %d%n",
                playersCardScore.participantCardsDto().participantName(),
                convertToCardsFormat(playersCardScore.participantCardsDto().cardDtos()),
                playersCardScore.score());
    }

    private void printWinningResult(final WinningResultDto winningResultDto) {
        System.out.printf("%n## 최종 승패%n");
        printDealerWinningResult(winningResultDto.dealerWinningResult());
        printPlayersWinningResult(winningResultDto.playersWinningResult());
    }

    private void printDealerWinningResult(final List<WinningResultCountDto> dealerWinningResult) {
        System.out.printf("%s: %s%n", DealerFormat.DEALER.getFormat(), convertToWinningResult(dealerWinningResult));

    }

    private String convertToWinningResult(final List<WinningResultCountDto> winningResult) {
        return winningResult.stream()
                .map(winningResultCountDto -> winningResultCountDto.count() + WinningStatusFormat.of(winningResultCountDto.winStatus()).getFormat())
                .collect(Collectors.joining(" "));
    }

    private void printPlayersWinningResult(final List<PlayerWinningResultDto> playersWinningResult) {
        playersWinningResult.forEach(playerWinningResultDto ->
                System.out.printf("%s: %s%n", playerWinningResultDto.playerName(), WinningStatusFormat.of(playerWinningResultDto.winStatus()).getFormat()));

    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }

    private <T> List<T> getExceptDealer(final List<T> resultDtos, Function<T, String> getName) {
        return resultDtos.stream()
                .filter(item -> !getName.apply(item).equals(DealerFormat.DEALER.getSignal()))
                .toList();
    }

    private <T> T getOnlyDealer(final List<T> resultDtos, Function<T, String> getName) {
        return resultDtos.stream()
                .filter(item -> getName.apply(item).equals(DealerFormat.DEALER.getSignal()))
                .findAny()
                .orElseThrow();
    }
}

