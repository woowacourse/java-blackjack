import blackjack.controller.GameMachine;

public class Main {
    public static void main(String[] args) {
        try {
            GameMachine.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
