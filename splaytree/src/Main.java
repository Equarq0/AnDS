import java.util.*;

public class Main {

    public static void main(String[] args) {
        runTest("RANDOM");
        runTest("SORTED");
        runTest("REPEATED");
    }

    public static void runTest(String type) {
        Random rnd = new Random();

        System.out.println("\n" + type);
        System.out.println("Size;Insert;Search;Delete");

        for (int size = 100; size <= 10000; size += 100) {

            List<Integer> data = new ArrayList<>();

            // Генерация данных
            switch (type) {
                case "SORTED":
                    for (int i = 0; i < size; i++) data.add(i);
                    break;

                case "REPEATED":
                    for (int i = 0; i < size; i++) data.add(i % 10);
                    break;

                default:
                    for (int i = 0; i < size; i++) data.add(rnd.nextInt(100000));
            }

            int repeats = 5;

            long insertTime = 0;
            long searchTime = 0;
            long deleteTime = 0;

            for (int r = 0; r < repeats; r++) {
                SplayTree<Integer> tree = new SplayTree<>();

                // Вставка
                long t0 = System.nanoTime();
                for (Integer x : data) tree.insert(x);
                insertTime += (System.nanoTime() - t0) / size;

                // Поиск
                Collections.shuffle(data);
                long t1 = System.nanoTime();
                for (Integer x : data) tree.search(x);
                searchTime += (System.nanoTime() - t1) / size;

                // Удаление
                long t2 = System.nanoTime();
                for (Integer x : data) tree.delete(x);
                deleteTime += (System.nanoTime() - t2) / size;
            }

            insertTime /= repeats;
            searchTime /= repeats;
            deleteTime /= repeats;

            System.out.printf("%d;%d;%d;%d\n",
                    size, insertTime, searchTime, deleteTime);
        }
    }
}
