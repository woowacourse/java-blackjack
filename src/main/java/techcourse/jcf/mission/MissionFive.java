package techcourse.jcf.mission;

class Printer {
}

class LaserPrinter extends Printer {
}

public class MissionFive {
    public static void main(String[] args) {

        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter); // true
    }
}
