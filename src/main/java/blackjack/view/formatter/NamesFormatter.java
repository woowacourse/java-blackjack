package blackjack.view.formatter;

import blackjack.model.participant.Name;
import java.util.List;
import java.util.stream.Collectors;

public class NamesFormatter {
    public static String format(final List<Name> names) {
        return names.stream()
                .map(Name::getValue)
                .collect(Collectors.joining(", "));
    }
}
