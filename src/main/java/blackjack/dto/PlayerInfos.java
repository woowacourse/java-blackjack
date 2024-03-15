package blackjack.dto;

import blackjack.domain.participant.Betting;
import blackjack.domain.participant.Name;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record PlayerInfos(Map<Name, Betting> infos) {

    public static PlayerInfos of(final NamesInput names, final List<Betting> bettings) {
        Map<Name, Betting> infos = IntStream.range(0, names.size())
                .boxed()
                .collect(Collectors.toMap(names.names()::get, bettings::get));

        return new PlayerInfos(infos);
    }
}
