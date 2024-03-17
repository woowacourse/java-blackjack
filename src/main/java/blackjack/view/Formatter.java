package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import java.util.List;

public class Formatter {

    private static final String SEPARATOR = ", ";

    private Formatter() {
    }

    public static String playerNamesFormat(List<PlayerDto> playerDtos) {
        List<String> playerNames = playerDtos.stream()
                .map(PlayerDto::name)
                .toList();
        return String.join(SEPARATOR, playerNames);
    }

    public static String cardNamesFormat(List<CardDto> cardDtos) {
        List<String> cardNames = cardDtos.stream()
                .map(Formatter::cardNameFormat)
                .toList();

        return String.join(SEPARATOR, cardNames);
    }

    public static String dealerCardInfoFormat(DealerDto dealerDto) {
        String cardNames = cardNamesFormat(dealerDto.cardDtos());
        return String.format("딜러 카드: %s", cardNames);
    }

    public static String playerCardInfoFormat(PlayerDto playerDto) {
        String playerName = playerDto.name();
        String cardNames = cardNamesFormat(playerDto.cardDtos());
        return String.format("%s 카드: %s", playerName, cardNames);
    }

    public static String dealerCardAndScoreInfoFormat(DealerDto dealerDto) {
        int dealerScore = dealerDto.score();
        return dealerCardInfoFormat(dealerDto) + cardScoreFormat(dealerScore);
    }

    public static String playerCardAndScoreInfoFormat(PlayerDto playerDto) {
        int playerScore = playerDto.score();
        return playerCardInfoFormat(playerDto) + cardScoreFormat(playerScore);
    }

    public static String dealerNetProfitFormat(int dealerNetProfit) {
        return String.format("딜러 : %d", dealerNetProfit);
    }

    public static String playerNetProfitFormat(String playerName, int netProfit) {
        return String.format("%s : %d", playerName, netProfit);
    }

    private static String cardNameFormat(CardDto cardDto) {
        String cardNumber = cardDto.cardNumber();
        String cardPattern = cardDto.cardPattern();
        return cardNumber + cardPattern;
    }

    private static String cardScoreFormat(int score) {
        return String.format(" - 결과: %d", score);
    }
}
