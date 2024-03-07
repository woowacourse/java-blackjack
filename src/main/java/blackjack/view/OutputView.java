package blackjack.view;

import static java.util.stream.Collectors.toMap;

import blackjack.dto.CardDTO;
import blackjack.dto.FinalResultDTO;
import blackjack.dto.HandsScoreDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    public void printStartCards(final StartCardsDTO startCardsDTO) {
        final Map<String, List<CardDTO>> participantsCards = startCardsDTO.playersCard();
        Map<String, List<CardDTO>> playersCard = getExceptDealer(participantsCards);

        printStartPreMessage(playersCard.keySet());
        printDealerCard(getOnlyDealer(participantsCards));
        printPlayersCard(playersCard);
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

    private void printStartPreMessage(final Set<String> names) {
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", DealerFormat.DEALER.getFormat(), String.join(", ", names));
    }

    private void printDealerCard(final List<CardDTO> cards) {
        System.out.printf("%s: %s%n", DealerFormat.DEALER.getFormat(), convertToCardsFormat(cards));
    }

    private String convertToCardFormat(final CardDTO cardDTO) {
        return CardNumberFormat.valueOf(cardDTO.number()).getFormat() +
                CardShapeFormat.valueOf(cardDTO.shape()).getFormat();
    }

    private void printPlayersCard(final Map<String, List<CardDTO>> playersCard) {
        playersCard.forEach(this::printPlayerCard);
    }

    public void printPlayerCard(final String name, final List<CardDTO> cards) {
        System.out.printf("%s카드: %s%n", name, convertToCardsFormat(cards));
    }

    private String convertToCardsFormat(final List<CardDTO> cards) {
        return String.join(", ", cards.stream().map(this::convertToCardFormat).toList());
    }

    public void printFinalResult(final FinalResultDTO finalResultDTO, final WinningResultDTO winningResultDTO) {
        printCardScore(finalResultDTO);
        printWinningResult(winningResultDTO);
    }

    private void printCardScore(final FinalResultDTO finalResultDTO) {
        final Map<String, HandsScoreDTO> playersWithScore = finalResultDTO.finalCards();

        printDealerCardScore(getOnlyDealer(playersWithScore));
        printPlayersCardScore(getExceptDealer(playersWithScore));
    }

    private void printPlayersCardScore(final Map<String, HandsScoreDTO> playersCardScore) {
        playersCardScore.entrySet()
                .forEach(this::printPlayerCardScore);

    }

    // TODO: 키밸루
    private void printPlayerCardScore(final Entry<String, HandsScoreDTO> entry) {
        System.out.printf("%s 카드: %s - 결과 : %d%n", entry.getKey(), convertToCardsFormat(entry.getValue().cards()),
                entry.getValue().score());
    }

    private void printDealerCardScore(final HandsScoreDTO dealerCardScore) {
        System.out.printf("%n%s 카드: %s - 결과 : %d%n",
                DealerFormat.DEALER.getFormat(),
                convertToCardsFormat(dealerCardScore.cards()),
                dealerCardScore.score());
    }

    private void printWinningResult(final WinningResultDTO winningResultDTO) {
        System.out.printf("%n## 최종 승패%n");
        printDealerWinningResult(winningResultDTO.dealerWinningResult());
        printPlayersWinningResult(winningResultDTO.playerWinningResult());
    }

    private void printDealerWinningResult(final Map<String, Integer> dealerWinningResult) {
        System.out.printf("%s: %s%n", DealerFormat.DEALER.getFormat(), convertToWinningResult(dealerWinningResult));

    }

    private String convertToWinningResult(final Map<String, Integer> winningResult) {
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
}
