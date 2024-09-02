package pack;

import java.util.*;

public class Dijkstra {

    static int[][] matrix = new int[32][32];
    static int size = 0;

    public static void main(String[] args) {
        int[] points = user_input();
        matrix_out();
        int[] path = dijkstra(points[0], points[1]);
        output(path);
        
    }
    
    public static int[] user_input() {
    	Scanner sc = new Scanner(System.in);
    	while (true) {
    		String str = sc.nextLine();
    		if (str == "") {
    			break;
    		}
    		String[] pair = str.split(" ");
    		int j = Integer.parseInt(pair[0]) - 1;
    		int i = Integer.parseInt(pair[1]) - 1;
    		int weight = Integer.parseInt(pair[2]);
    		if (i > j && i > size - 1) {
    			size = i + 1;
    		}
    		else if (j >= i && j > size - 1) {
    			size = j + 1;
    		}
    		matrix[i][j] = weight;
    		matrix[j][i] = weight;
    	}
    	
    	System.out.print("Введите вершины, между которымми будет найден кратчайший путь:\t");
    	int start_point = sc.nextInt() - 1;
        int end_point = sc.nextInt() - 1;
        sc.close();
        return new int[] {start_point, end_point};
    }
    
    public static void matrix_out() {
    	System.out.print("\nSize:\t" + size + "\n\t");
    	for (int i = 1; i <= size; i++) {
    		System.out.print(i + "\t");
    	}
    	System.out.print("\n");
    	for(int i = 0; i < size; i++) {
    		System.out.print((i + 1) + "\t");
    		for(int j = 0; j < size; j++) {
    			System.out.print(matrix[i][j] + "\t");
    		}
    		System.out.print("\n");
    	}
    }
    
    public static void output(int[] arr) {
    	int sum = 0;
    	System.out.print("\nКратчайший путь:\t");
    	for (int i = 0; i < arr.length - 1; i++) {
    		System.out.printf("%d ", arr[i]);
    		sum += matrix[arr[i]][arr[i + 1]];
    	}
    	System.out.printf("%d ", arr[arr.length - 1]);
    	System.out.print("\nСтоимость: " + sum);
    }

    public static int[] dijkstra(int start_point, int end_point) {
        int[] distance = new int[size]; // Расстояние до каждой вершины
        boolean[] visited = new boolean[size]; // Отмечены ли вершины
        int[] previous = new int[size]; // Предыдущая вершина на пути

        // Инициализация
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start_point] = 0; // Расстояние до начальной вершины = 0
        previous[start_point] = -1; // У начальной вершины нет предыдущей

        // Основной цикл Дейкстры
        for (int i = 0; i < size; i++) {
            int minDistance = Integer.MAX_VALUE;
            int minVertex = -1;

            // Находим вершину с минимальным расстоянием
            for (int j = 0; j < size; j++) {
                if (!visited[j] && distance[j] < minDistance) {
                    minDistance = distance[j];
                    minVertex = j;
                }
            }

            if (minVertex == -1) {
                break; // Все вершины были посещены
            }

            visited[minVertex] = true;

            // Обновляем расстояния до соседних вершин
            for (int j = 0; j < size; j++) {
                if (matrix[minVertex][j] != 0 && distance[minVertex] + matrix[minVertex][j] < distance[j]) {
                    distance[j] = distance[minVertex] + matrix[minVertex][j];
                    previous[j] = minVertex; // Обновляем предыдущую вершину
                }
            }
        }

        // Восстанавливаем путь
        List<Integer> pathList = new ArrayList<>();
        int current = end_point;
        while (current != -1) {
            pathList.add(current);
            current = previous[current];
        }

        Collections.reverse(pathList); // Переворачиваем список

        int[] pathArray = new int[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            pathArray[i] = pathList.get(i);
        }

        return pathArray;
    }
}

//1 2 2
//1 3 10
//2 3 3
//3 4 4

//1 2 1
//1 3 5
//2 3 2
//2 8 22
//3 4 3
//4 6 6
//4 5 12
//6 5 4
//5 8 2
//5 9 7
//8 7 13
//9 10 1
//10 7 2


//1. **Инициализация:**
//   - `distance` - массив, который будет хранить кратчайшее расстояние от начальной вершины до каждой другой вершины.
//   - `visited` - массив, который отмечает, была ли уже посещена вершина в процессе поиска кратчайшего пути.
//   - `previous` - массив, который хранит предыдущую вершину на кратчайшем пути до данной вершины.
//
//2. **Основной цикл Дейкстры:**
//   - В цикле `for` мы перебираем все вершины графа.
//   - На каждом шаге цикла мы ищем вершину с минимальным расстоянием, которая еще не была посещена (`visited`).
//   - Мы обновляем расстояния до соседних вершин текущей вершины, используя формулу: `distance[j] = distance[minVertex] + matrix[minVertex][j]`.
//   - Если расстояние до соседней вершины меньше, чем текущее расстояние до нее, то мы обновляем расстояние и устанавливаем `previous[j] = minVertex`, чтобы запомнить, через какую вершину мы попали к данной вершине.
//
//3. **Восстановление пути:**
//   - После выполнения цикла Дейкстры мы восстанавливаем путь от конечной вершины `end_point` до начальной вершины `start_point` с помощью массива `previous`.
//   - Мы начинаем с `current = end_point` и идем по пути, двигаясь от `current` к `previous[current]`, пока не достигнем начальной вершины.
//
//4. **Возврат результата:**
//   - Мы возвращаем массив `pathArray`, содержащий путь от `start_point` до `end_point`.
//



