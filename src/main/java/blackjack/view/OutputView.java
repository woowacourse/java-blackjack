package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.player.Player;
import blackjack.dto.BlackJackResultDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import java.util.List;

public class OutputView {

    private static final String WITH_DEALER_NAME = "딜러와 ";
    private static final String COMMA_DELIMITER = ", ";
    private static final String SEPARATE_TWO_CARD = "에게 2장을 나누었습니다.";
    private static final String COLON_DELIMITER = ": ";
    private static final String UNDER_SIXTEEN_INSTRUCTION = "는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PREFIX_CARD = "카드: ";
    private static final String PREFIX_RESULT = " - 결과: ";
    private static final String BURST_INSTRUCTION = "님 버스트로 패배하였습니다.";
    private static final String FINAL_RESULT_INSTRUCTION = "## 최종 승패";
    private static final String DEALER_NAME = "딜러";
    private static final String BLANK_DELIMITER = " ";
    private static final String NEWLINE_DELIMITER = "\n";
    private static final String BURST = "BURST";

    public String getCardNames(List<PlayingCard> playingCards) {
        return playingCards.stream()
            .map(PlayingCard::getCardName)
            .collect(joining(COMMA_DELIMITER));
    }

    public void printSpreadInstruction(PlayersDto PlayersDto) {
        System.out.println();
        System.out.println(PlayersDto.getValue()
            .stream()
            .map(PlayerDto::getName)
            .collect(joining(COMMA_DELIMITER)) + SEPARATE_TWO_CARD);
    }

    public void printSingleCardForDealer(PlayerDto dealer) {
        System.out.println(dealer.getName() + COLON_DELIMITER + dealer.getPlayingCards().get(0).getCardName());
    }

    public void printCardsForGambler(final PlayersDto players) {
        players.getValue()
            .forEach(this::printCards);
    }

    public void printCards(final PlayerDto playerDto) {
        System.out.println(playerDto.getName() + COLON_DELIMITER + getCardNames(playerDto.getPlayingCards()));
    }

    public void printDealerAddCard(Player dealer) {
        System.out.println();
        System.out.println(dealer.getName() + UNDER_SIXTEEN_INSTRUCTION);
    }

    public void printCardAndScore(PlayersDto playersDto) {
        playersDto.getValue()
            .forEach(playerDto -> System.out.println(getCardAndScoreMessage(playerDto)));
    }

    private String getCardAndScoreMessage(final PlayerDto playerDto) {
        return playerDto.getName() + PREFIX_CARD + getCardNames(playerDto.getPlayingCards()) + PREFIX_RESULT
            + getScoreOrBurst(playerDto);
    }

    private String getScoreOrBurst(final PlayerDto playerDto) {
        if (playerDto.getScore() > 21) {
            return BURST;
        }
        return String.valueOf(playerDto.getScore());
    }

    public void printBurst(final PlayerDto playerDto) {
        System.out.println(playerDto.getName() + BURST_INSTRUCTION);
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printResult(final BlackJackResultDto blackJackResultDto) {
        System.out.println();
        System.out.println(FINAL_RESULT_INSTRUCTION);
        System.out.println(
            DEALER_NAME + COLON_DELIMITER + String.join(BLANK_DELIMITER, blackJackResultDto.getDealerResult()));
        System.out.println(String.join(NEWLINE_DELIMITER, blackJackResultDto.getGamblerResult()));
    }

}
