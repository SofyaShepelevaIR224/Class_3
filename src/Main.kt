import kotlin.math.sqrt

// Класс Точка
data class Point(val x: Double, val y: Double)

// Функция для вычисления расстояния между двумя точками
fun distance(p1: Point, p2: Point): Double {
    return sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y))
}

// Функция для безопасного ввода числового значения
fun getDoubleInput(prompt: String): Double {
    while (true) {
        print(prompt)
        val input = readLine()
        try {
            return input?.toDouble() ?: throw IllegalArgumentException("Некорректный ввод")
        } catch (e: NumberFormatException) {
            println("Ошибка: введите числовое значение.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

// Функция для безопасного ввода целого числа
fun getIntInput(prompt: String): Int {
    while (true) {
        print(prompt)
        val input = readLine()
        try {
            val value = input?.toInt() ?: throw IllegalArgumentException("Некорректный ввод")
            if (value <= 2) throw IllegalArgumentException("Количество точек должно быть больше двух.")
            return value
        } catch (e: NumberFormatException) {
            println("Ошибка: введите целое число.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun main() {
    // Ввод количества точек
    val n = getIntInput("Введите количество точек (больше двух): ")

    // Ввод координат точек
    val points = mutableListOf<Point>()
    for (i in 1..n) {
        println("Введите координаты точки $i:")
        val x = getDoubleInput("x$i: ")
        val y = getDoubleInput("y$i: ")
        points.add(Point(x, y))
    }

    // Инициализация переменных для хранения минимального и максимального расстояния
    var minDistance = Double.MAX_VALUE
    var maxDistance = Double.MIN_VALUE

    // Поиск минимального и максимального расстояний
    for (i in 0 until points.size - 1) {
        for (j in i + 1 until points.size) {
            val dist = distance(points[i], points[j])
            if (dist < minDistance) {
                minDistance = dist
            }
            if (dist > maxDistance) {
                maxDistance = dist
            }
        }
    }

    // Вывод результатов
    println("Минимальное расстояние между точками: $minDistance")
    println("Максимальное расстояние между точками: $maxDistance")
}
