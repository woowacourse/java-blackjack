package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.view.dto.PlayerStatusDto;
import blackjack.view.dto.RoundStatusDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static final String TWO_CARDS_DEAL_OUT_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    public static final String PARTICIPANT_STATUS_MESSAGE = "%s: %s";

    public static void showPlayCardStatus(String name, List<Card> cards) {
        String text = String.format("%s: %s", name, cards
                .stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.joining(", ")));
        System.out.println(text);
    }

    public static void showDealerAddCard(int turnOverCount) {
        System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", turnOverCount));
    }

    public static void showFinalStatus(RoundStatusDto statusDto) {
        List<PlayerStatusDto> playerStatusDto = statusDto.getPlayerStatusDto();
        System.out.println(String.format("%s카드 : %s - 결과: %d", statusDto.getDealerName(), statusDto.getDealerCardStatus().stream().collect(Collectors.joining(",")), statusDto.getDealerScore()));
        playerStatusDto.forEach(dto -> System.out.println(String.format("%s카드 : %s - 결과: %d", dto.getPlayerName(), dto.getPlayerCardStatus().stream().collect(Collectors.joining(",")), dto.getPlayerScore())));
    }

    public static void showInitialStatus(RoundStatusDto roundStatusDto) {
        String dealerName = roundStatusDto.getDealerName();
        List<String> dealerCardStatus = roundStatusDto.getDealerCardStatus();
        List<PlayerStatusDto> playerStatusDto = roundStatusDto.getPlayerStatusDto();

        String playerNames = playerStatusDto.stream()
                .map(dto -> dto.getPlayerName())
                .collect(Collectors.joining(", "));
        System.out.println(String.format(TWO_CARDS_DEAL_OUT_MESSAGE, dealerName, playerNames));
        String dealerStatus = String.format(PARTICIPANT_STATUS_MESSAGE, dealerName, dealerCardStatus.stream().collect(Collectors.joining(",")));
        System.out.println(dealerStatus);
        playerStatusDto.forEach(dto -> System.out.println(String.format(PARTICIPANT_STATUS_MESSAGE, dto.getPlayerName(), dto.getPlayerCardStatus().stream().collect(Collectors.joining(", ")))));
    }

    public static void showOutComes(Map<String, List<Outcome>> outcomes) {
        System.out.println("## 최종 승패");
        outcomes.keySet().forEach(name -> System.out.println(String.format("%s : %s", name, outcomes.get(name))));
    }
}
