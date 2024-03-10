package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.HandDeckDto;
import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.StringJoiner;

public class Formatter {

    private static final String SEPARATOR = ", ";
    private static final String SPACE = " ";

    public static String playerNamesFormat(List<PlayerDto> playerDtos) {
        List<String> playerNames = playerDtos.stream()
                .map(PlayerDto::name)
                .toList();
        return String.join(SEPARATOR, playerNames);
    }

    public static String cardNamesFormat(HandDeckDto handDeckDto) {
        List<String> cardNames = handDeckDto.cardDtos().stream()
                .map(Formatter::cardNameFormat)
                .toList();

        return String.join(SEPARATOR, cardNames);
    }

    public static String dealerCardInfoFormat(DealerDto dealerDto) {
        String cardNames = cardNamesFormat(dealerDto.handDeckDto());
        return String.format("딜러 카드: %s", cardNames);
    }

    public static String playerCardInfoFormat(PlayerDto playerDto) {
        String playerName = playerDto.name();
        String cardNames = cardNamesFormat(playerDto.handDeckDto());
        return String.format("%s 카드: %s", playerName, cardNames);
    }

    public static String dealerCardAndScoreInfoFormat(DealerDto dealerDto) {
        int dealerScore = dealerDto.handDeckDto().score();
        return dealerCardInfoFormat(dealerDto) + cardScoreFormat(dealerScore);
    }

    public static String playerCardAndScoreInfoFormat(PlayerDto playerDto) {
        int playerScore = playerDto.handDeckDto().score();
        return playerCardInfoFormat(playerDto) + cardScoreFormat(playerScore);
    }

    public static String dealerResultFormat(DealerResultDto dealerResultDto) {
        StringBuilder dealerResultFormat = new StringBuilder();

        dealerResultFormat.append("딜러: ");
        int winCount = dealerResultDto.winCount();
        int tieCount = dealerResultDto.tieCount();
        int loseCount = dealerResultDto.loseCount();
        dealerResultFormat.append(dealerResult(winCount, tieCount, loseCount));

        return dealerResultFormat.toString();
    }

    public static String playerResultFormat(String playerName, String playerResult) {
        return String.format("%s : %s", playerName, playerResult);
    }

    private static String cardNameFormat(CardDto cardDto) {
        String cardNumber = cardDto.cardNumber();
        String cardPattern = cardDto.cardPattern();
        return cardNumber + cardPattern;
    }

    private static String cardScoreFormat(int score) {
        return String.format(" - 결과: %d", score);
    }

    private static String dealerResult(int winCount, int tieCount, int loseCount) {
        StringJoiner dealerResultInfo = new StringJoiner(SPACE);

        if (winCount > 0) {
            dealerResultInfo.add(String.format("%d승", winCount));
        }
        if (tieCount > 0) {
            dealerResultInfo.add(String.format("%d무", tieCount));
        }
        if (loseCount > 0) {
            dealerResultInfo.add(String.format("%d패", loseCount));
        }

        return dealerResultInfo.toString();
    }
}
