package dto;

import static java.util.stream.Collectors.toList;

import domain.MatchResult;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Player;
import java.util.List;

public class ResultDto {
    private static final int SINGLE_SIZE_FOR_GAMBLER_RESULT = 1;
    private static final int FIRST_INDEX_FOR_GAMBLER_RESULT = 0;

    private final CardsAndScoreDto cardsAndScoreDto;
    private final List<MatchResult> matchResults;

    private ResultDto(CardsAndScoreDto cardsAndScoreDto, List<MatchResult> matchResults) {
        this.cardsAndScoreDto = cardsAndScoreDto;
        this.matchResults = matchResults;
    }

    public static ResultDto of(Gambler gambler, Dealer dealer) {
        return new ResultDto(CardsAndScoreDto.from(gambler), List.of(gambler.match(dealer)));
    }

    public static ResultDto of(Dealer dealer, List<Gambler> gamblers) {
        return new ResultDto(CardsAndScoreDto.from(dealer), getDealerResults(dealer, gamblers));
    }

    private static List<MatchResult> getDealerResults(Player dealer, List<Gambler> gamblers) {
        return gamblers.stream()
                .map(dealer::match)
                .collect(toList());
    }

    public boolean isGamblerResult() {
        return matchResults.size() == SINGLE_SIZE_FOR_GAMBLER_RESULT;
    }

    public MatchResult getGamblerResult() {
        return matchResults.get(FIRST_INDEX_FOR_GAMBLER_RESULT);
    }

    public CardsAndScoreDto getCardsAndScoreDto() {
        return cardsAndScoreDto;
    }

    public List<MatchResult> getMatchResults() {
        return matchResults;
    }
}
