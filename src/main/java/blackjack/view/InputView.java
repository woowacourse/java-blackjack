package blackjack.view;


import blackjack.view.reader.Reader;
import blackjack.view.writer.Writer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InputView {
    
    private final Writer writer;
    private final Reader reader;
    
    public InputView(final Writer writer, final Reader reader) {
        this.writer = writer;
        this.reader = reader;
    }
    
    public List<String> getPlayerNames() {
        writer.write("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = reader.readLine();
        return parseToNames(input);
    }
    
    private List<String> parseToNames(final String input) {
        return Arrays.stream(input.split(","))
                .map(name -> name.replaceAll(" ", ""))
                .toList();
    }
    
    public boolean getAddingCardDecision(String name) {
        writer.write("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".formatted(name));
        final String decision = reader.readLine();
        if (decision.equals("y")) {
            return true;
        }
        if (decision.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y나 n을 입력하세요.");
    }
    
    public Map<String, Integer> getBettingAmounts(List<String> names) {
        final Map<String, Integer> bettingAmounts = new HashMap<>();
        for (String name : names) {
            writer.write("\n%s의 배팅 금액은?".formatted(name));
            String input = reader.readLine();
            bettingAmounts.put(name, Integer.parseInt(input));
        }
        return bettingAmounts;
    }
}
