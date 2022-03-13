package blackjack.domain;

import static blackjack.domain.card.Denomination.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class DenominationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                arguments(10, ACE, 21),
                arguments(11, ACE, 12),
                arguments(10, SEVEN, 17),
                arguments(22, ACE, 23),
                arguments(21, ACE, 22)
        );
    }
}
