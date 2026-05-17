import java.util.*;

public class TopologicalSortAnalyse {

    // Класс для хранения результатов замера
    public static class Metrics {
        public long durationNano;
        public long iterations;
        public List<Integer> result;

        public Metrics(long durationNano, long iterations, List<Integer> result) {
            this.durationNano = durationNano;
            this.iterations = iterations;
            this.result = result;
        }
    }


     // numVertices Количество вершин (от 0 до V-1)
     // param adjList Список смежности графа

    public static Metrics topologicalSort(int numVertices, List<List<Integer>> adjList) {
        long iterations = 0;

        long startTime = System.nanoTime();

        // 1. Подсчет полустепени захода (in-degree) для каждой вершины
        int[] inDegree = new int[numVertices];
        for (int u = 0; u < numVertices; u++) {
            for (int v : adjList.get(u)) {
                inDegree[v]++;
                iterations++; // Самый вложенный цикл подсчета ребер
            }
        }

        // 2. Инициализация очереди вершинами с in-degree = 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topOrder = new ArrayList<>();

        // 3. Основной цикл извлечения вершин
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topOrder.add(u);

            // Уменьшаем in-degree соседей
            for (int v : adjList.get(u)) {
                inDegree[v]--;
                iterations++;

                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // Выключаем таймер
        long stopTime = System.nanoTime();
        long durationNano = (stopTime - startTime);

        // Проверка на наличие циклов
        if (topOrder.size() != numVertices) {
            throw new IllegalArgumentException("Граф содержит цикл! Топологическая сортировка невозможна.");
        }

        return new Metrics(durationNano, iterations, topOrder);
    }
    public static List<List<Integer>> generateDAG(int numVertices, int edgesPerVertex) {
        List<List<Integer>> adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        Random random = new Random();
        // Вместо перебора всех пар, мы просто добавляем фиксированное число ребер для каждой вершины
        for (int i = 0; i < numVertices; i++) {
            for (int k = 0; k < edgesPerVertex; k++) {
                // Ребро может идти только вперед (к вершине с большим индексом), чтобы не было циклов
                if (i < numVertices - 1) {
                    int nextVertex = i + 1 + random.nextInt(numVertices - i - 1);
                    adjList.get(i).add(nextVertex);
                }
            }
        }
        return adjList;
    }

    public static void main(String[] args) {
        int minSize = 100;
        int maxSize = 10000;
        int steps = 75; // 75 наборов данных

        System.out.println("Vertices,Edges,Time(ns),Iterations");

        for (int i = 0; i < steps; i++) {
            // Равномерно распределяем размеры от 100 до 10000
            int numVertices = minSize + (i * (maxSize - minSize) / (steps - 1));

            // Генерируем граф, где из каждой вершины гарантированно выходит по 3 ребра
            // Это обеспечит линейный рост количества ребер (E = 3 * V)
            List<List<Integer>> graph = generateDAG(numVertices, 3);

            // Считаем точное количество ребер для вывода
            int numEdges = 0;
            for (List<Integer> list : graph) numEdges += list.size();

            // Прогрев JVM (важно для точности наносекунд)
            if (i == 0) {
                for (int k = 0; k < 10; k++) TopologicalSortAnalyse.topologicalSort(numVertices, graph);
            }

            // Замер
            TopologicalSortAnalyse.Metrics metrics = TopologicalSortAnalyse.topologicalSort(numVertices, graph);

            System.out.println(numVertices + "," + numEdges + "," + metrics.durationNano + "," + metrics.iterations);
        }
    }
}