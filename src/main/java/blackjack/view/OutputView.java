package blackjack.view;

import static java.util.stream.Collectors.toMap;

import blackjack.dto.CardDTO;
import blackjack.dto.FinalHandsScoreDTO;
import blackjack.dto.HandsScoreDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import blackjack.view.format.CardNumberFormat;
import blackjack.view.format.CardShapeFormat;
import blackjack.view.format.DealerFormat;
import blackjack.view.format.WinningStatusFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void printStartCards(final StartCardsDTO startCardsDTO) {
        final Map<String, List<CardDTO>> participantsCards = startCardsDTO.participantsCard();
        final Map<String, List<CardDTO>> playersCard = getExceptDealer(participantsCards);

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", DealerFormat.DEALER.getFormat(),
                String.join(DELIMITER, playersCard.keySet()));
        System.out.printf("%s: %s%n", DealerFormat.DEALER.getFormat(),
                convertToCardsFormat(getOnlyDealer(participantsCards)));
        playersCard.forEach(this::printPlayerCard);
    }

    private String convertToCardFormat(final CardDTO cardDTO) {
        return CardNumberFormat.valueOf(cardDTO.number()).getFormat() +
                CardShapeFormat.valueOf(cardDTO.shape()).getFormat();
    }

    private String convertToCardsFormat(final List<CardDTO> cards) {
        return String.join(DELIMITER, cards.stream().map(this::convertToCardFormat).toList());
    }

    public void printPlayerCard(final String name, final List<CardDTO> cards) {
        System.out.printf("%s카드: %s%n", name, convertToCardsFormat(cards));
    }

    public void printFinalResult(final FinalHandsScoreDTO finalHandsScoreDTO, final WinningResultDTO winningResultDTO) {
        printCardScore(finalHandsScoreDTO);
        printWinningResult(winningResultDTO);
    }

    private void printCardScore(final FinalHandsScoreDTO finalHandsScoreDTO) {
        final Map<String, HandsScoreDTO> playersWithScore = finalHandsScoreDTO.finalCards();

        printDealerCardScore(getOnlyDealer(playersWithScore));
        printPlayersCardScore(getExceptDealer(playersWithScore));
    }

    private void printDealerCardScore(final HandsScoreDTO dealerCardScore) {
        System.out.printf("%n%s 카드: %s - 결과 : %d%n",
                DealerFormat.DEALER.getFormat(),
                convertToCardsFormat(dealerCardScore.cards()),
                dealerCardScore.score());
    }

    private void printPlayersCardScore(final Map<String, HandsScoreDTO> playersCardScore) {
        playersCardScore.forEach(this::printPlayerCardScore);
    }

    private void printPlayerCardScore(final String name, final HandsScoreDTO handsScore) {
        System.out.printf("%s 카드: %s - 결과 : %d%n", name, convertToCardsFormat(handsScore.cards()), handsScore.score());
    }

    private void printWinningResult(final WinningResultDTO winningResultDTO) {
        System.out.printf("%n## 최종 승패%n");
        printDealerWinningResult(winningResultDTO.dealerWinningResult());
        printPlayersWinningResult(winningResultDTO.playerWinningResult());
    }

    private void printDealerWinningResult(final Map<String, Long> dealerWinningResult) {
        System.out.printf("%s: %s%n", DealerFormat.DEALER.getFormat(), convertToWinningResult(dealerWinningResult));

    }

    private String convertToWinningResult(final Map<String, Long> winningResult) {
        return winningResult.entrySet().stream()
                .map(entry -> entry.getValue() + WinningStatusFormat.valueOf(entry.getKey()).getFormat())
                .collect(Collectors.joining(" "));
    }

    private void printPlayersWinningResult(final Map<String, String> playersWinningResult) {
        playersWinningResult.forEach((key, value) ->
                System.out.printf("%s: %s%n", key, WinningStatusFormat.valueOf(value).getFormat()));

    }

    public void printDealerMoreCard(int count) {
        while (count-- > 0) {
            System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%n", DealerFormat.DEALER.getFormat());
        }
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }

    private <T> T getOnlyDealer(final Map<String, T> cards) {
        return cards.get(DealerFormat.DEALER.name());
    }

    private <T> Map<String, T> getExceptDealer(final Map<String, T> cards) {
        return cards.entrySet().stream()
                .filter(entry -> DealerFormat.DEALER.isNot(entry.getKey()))
                .collect(toMap(Entry::getKey, Entry::getValue,
                        (v1, v2) -> v1, LinkedHashMap::new));
    }
}
