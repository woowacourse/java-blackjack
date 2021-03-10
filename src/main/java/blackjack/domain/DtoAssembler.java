package blackjack.domain;

import blackjack.domain.carddeck.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.ResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class DtoAssembler {

    private static final String DELIMITER = ", ";

    public static ParticipantDto createDealerInitStatusDto(final Dealer dealer) {
        return new ParticipantDto(
            createCardDtos(dealer.getInitCard()),
            dealer.getScoreToInt()
        );
    }

    public static ParticipantDto createDealerStatusDto(Dealer dealer) {
        return new ParticipantDto(
            createCardDtos(dealer.getCards()),
            dealer.getScoreToInt()
        );
    }

    public static List<ParticipantDto> createPlayerStatusDtos(final Players players) {
        return players.toList()
            .stream()
            .map(DtoAssembler::createPlayerStatusDto)
            .collect(Collectors.toList())
            ;
    }

    public static ParticipantDto createPlayerStatusDto(final Player player) {
        return new ParticipantDto(
            player.getName(),
            createCardDtos(player.getCards()),
            player.getScoreToInt()
        );
    }

    private static List<CardDto> createCardDtos(final List<Card> cards) {
        return cards.stream()
            .map(card -> new CardDto(card.getNumberName() + card.getPatternName()))
            .collect(Collectors.toList())
            ;
    }

    public static ResultDto createDealerResultDto(final Dealer dealer, Players players) {
        List<Result> results = getResults(dealer, players);
        return new ResultDto(
            getResultString(results, Result.WIN) + DELIMITER
                + getResultString(results, Result.LOSE) + DELIMITER
                + getResultString(results, Result.DRAW)
        );
    }

    private static List<Result> getResults(final Dealer dealer, final Players players) {
        return players.toList()
            .stream()
            .map(player -> player.judgeByDealerState(dealer))
            .map(Result::reverse)
            .collect(Collectors.toList());
    }

    private static String getResultString(final List<Result> results, final Result result) {
        return results.stream()
            .filter(compareResult -> compareResult.equals(result))
            .count() + result.getResult();
    }

    public static List<ResultDto> createPlayerResultDtos(final Dealer dealer, final Players players) {
        return players.toList()
            .stream()
            .map(player -> new ResultDto(player.getName(), player.judgeByDealerState(dealer).getResult()))
            .collect(Collectors.toList());
    }
}
