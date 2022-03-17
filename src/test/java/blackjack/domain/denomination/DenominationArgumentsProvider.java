package blackjack.domain.denomination;

import static blackjack.domain.Denomination.*;
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
                arguments(11, ACE, 22),
                arguments(10, SEVEN, 17)
        );
    }
}
