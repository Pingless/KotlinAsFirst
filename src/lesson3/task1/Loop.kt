@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import java.lang.Math.abs
import java.lang.StrictMath.*

/**
 * Вспомогательная
 *
 * Наибольший Общий Делитель
 */
fun gcd(x: Int, y: Int): Int {
    var a = x
    var b = y
    while (a != 0 && b != 0) {
        if (a > b) a %= b
        else b %= a
    }
    return a + b
}

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int = abs(n).toString().length
/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n in 1..2) return 1
    var result = 0
    var f1 = 1
    var f2 = 1
    for (i in 3..n) {
        result = f1 + f2
        f1 = f2
        f2 = result
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
/**
 * Комментарии неактульны.
 */
fun lcm(m: Int, n: Int): Int = n * m / gcd(n, m)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..floor(sqrt(n.toDouble())).toInt()) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int):Boolean {
    var a: Int = Math.min(m, n)
    for (i in 2..a)
        if ( m % i == 0 && n % i == 0) return false
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val rootM = Math.sqrt(m.toDouble())
    val rootN = Math.sqrt(n.toDouble())
    val difference = Math.floor(rootN) - Math.ceil(rootM)
    return difference >= 0
}
/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var xClean = x
    var counter = 1
    var dif = if(xClean > 2* Math.PI) -2.0 else 2.0
    while (xClean > 2 * Math.PI || xClean < 0)
        xClean += dif * Math.PI
    var res = xClean
    dif = xClean
    var mn = -1
    while (abs(dif) > eps) {
        dif = mn * pow(xClean,2.0*counter+1)/ factorial(2*counter+1)
        res += dif
        counter++
        mn *= -1
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var curN = n
    var revertN = 0
    while (curN > 0) {
        revertN = curN % 10 + revertN * 10
        curN /= 10
    }
    return revertN
}


/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = Math.abs(n)
    if (num < 10) return false
    val p = num % 10
    num /= 10
    while (num > 0) {
        if (num % 10 != p) return true
        num /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var x = 1
    var m = 1
    var t: Int
    var b = n
    var count = 1
    while (b > 0) {
        m = x * x
        t = m
        while (t >= 10) {
            count++
            t /= 10
        }
        b -= count
        count = 1
        x++
    }
    b = Math.abs(b)
    m = m / Math.pow(10.0, b * 1.0).toInt() % 10
    return m
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var x = 1
    var m = 1
    var t: Int
    var b = n
    var count = 1
    while (b > 0) {
        m = fib(x)
        t = m
        while (t >= 10) {
            count++
            t /= 10
        }
        b -= count
        count = 1
        x++
    }
    b = Math.abs(b)
    m = m / Math.pow(10.0, b * 1.0).toInt() % 10
    return m
}