package blackjack.domain;

import blackjack.view.dto.PlayerResultDto;

import java.util.*;

public class Result {
    private static final Map<String, Queue<Outcome>> RESULTS = new LinkedHashMap<>();

    public static Map<String, Queue<Outcome>> findResults(Round round) {
        Queue<Outcome> gameOutComes = round.makeRoundOutComes();
        RESULTS.put(round.getDealerName(), new ArrayDeque<>(gameOutComes));

        List<PlayerResultDto> playerResultDtos = round.makePlayerResults(gameOutComes);
        playerResultDtos.forEach(dto ->
                RESULTS.put(dto.getPlayerName(), Outcome.getPlayerOutcomes(dto.getOutcome()))
        );
        return new LinkedHashMap<>(RESULTS);
    }
}
