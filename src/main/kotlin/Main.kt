import ru.wildbine.polynomial.Lagrange
import ru.wildbine.polynomial.Newton
import ru.wildbine.polynomial.Polynomial
import java.lang.Math.*
import kotlin.math.pow
import kotlin.system.measureTimeMillis

fun main() {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    /*val d1 = DoubleArray(101, {0.0})
    d1[100] = 1.0
    d1[55] = 1.0
    d1[0] = 1.0
    val d2 = DoubleArray(101, {0.0})
    d2[100] = 1.0
    d2[0] = -1.0
    val p1 = Polynomial(2.0, 1.0)
    val p2 = Polynomial(3.0, -1.0, 1.0)
    val p4 = Polynomial(0.0)
    val p5 = Polynomial(1.0)
    val p6 = Polynomial(-1.0)
    val p3 = p1 + p2
    val c1 = Polynomial()
    val c2 = Polynomial(*d1)
    val c3 = Polynomial(*d2)
    println(Polynomial(*d1)) //x^100 + x^55 + 1
    println(Polynomial(*d2)) //x^100 - 1
    println(c2-c3) //x^55 + 2
    println((c2-c3).order) //55
    println(p1) //x + 2
    println(p2) //x^2 -x + 3
    println(p3) //x^2 + 5
    println(-p3) //x^2 - 5
    println(p1*p2)
    println(p1.pow(3)) //(x + 2)^3 = x^3 + 6x^2 + 12x + 8
    println(p1-p1) //0
    println(c1) //0
    println(p4) //0
    println(p5) //1
    println(p6) //-1
    p1 += Polynomial(10.0)
    println(p1) //x + 12
    val p10 = p1
    var p11 = p10
    var p12 = p1
    val p13  = Polynomial(12.0, 1.0)
    println(p1 == p1) //t
    println(p1 == null) //f
    println(p1 == p10) //t
    println(p1 == p11) //t
    println(p1.equals(p12)) //t
    println(p1.equals(p13)) //t
    println(p1.equals(p3)) //f
    println(p1.equals(p2)) //f
    println(p1.equals("Петя")) //f
    var p14 : Polynomial? = null */
    val l1 = Lagrange(mutableMapOf(-1.5 to -14.1014, -0.75 to -0.931596, 0.0 to 0.0, 0.75 to 0.931596, 1.5 to 14.1014))
    println(l1) //пример из википедии
    val l2 = Lagrange(mutableMapOf(0.0 to -1.0, 0.5 to 1.0, 1.0 to 1.0))
    println(l2) //-4x^2 +6x - 1
    println(l2(1.0)) //1
    println(l2(2.0)) //-5
    val n1 = Newton(hashMapOf(0.0 to -1.0, 1.0 to 1.0, 0.5 to 1.0))
    println(n1)
    n1.addNode(3.0, 4.0)
    println(n1)
    val l3 = Lagrange(mutableMapOf(0.0 to -1.0, 1.0 to 1.0, 0.5 to 1.0, 3.0 to 4.0))
    println(l3)
   /* println(measureTimeMillis {
        var k : Double = 1.0
        repeat(1001) {n1.AddNode(k, 1.1); ++k}
    })
    var k : Double = 1.0
    println(measureTimeMillis {
        for (i in 1..1000) { var d = DoubleArray(i, {1.1})
        var x = Polynomial(*d)
        } }) */
    val map = hashMapOf(0.0 to 0.0)
    var k = 1.0
    val t1 = measureTimeMillis {
        (1 .. 100)
            .forEach { _ ->
            map[k]= k
                ++k
        }
    }
    println(t1)
    map[1001.0] = 1001.0
    val n = Newton()
    print(measureTimeMillis { n.addNodes(map) }); println(" - время для Ньютона")
    var time = measureTimeMillis { n.addNode(1001.0, 1001.0) }
    println("$time")
    time = measureTimeMillis { Lagrange(map) }
    println("$time" + " - время для Лагранжа")

    /*
    Сделать вывод записи более корректным:
     -убрать скобки
     -минус или плюс ставится по факту
     -нулевые коэффиценты не прописываются
    Методы:
     -сложение и вычитание полиномов, деление полинома на число, умножение полиномов
     */

    /*
    Функция очистки нулей в конструкторе при старших степенях
    Унарный минус
    Унарный плюс
    +=   - Ничего не возвращает, а меняет значения в this полиноме
    -=
    *=
    /=
    Операторы сравнения
    Полином Лагранжа, функция вычисления Полинома в точке
     */

    /*
    Удаление нулей при старших степенях
    Новые операторы сравнения eq, neq и тд
    Вычисление полинома Лагранжа в точке
    +=
    -=
    *=
    /=
    Метод позволяет добавить еще один узел к полиному ньютона
    Полином Ньютона
    Тест, который считает время для построения полиномов Лагранжа для 1001 узла и для полинома Ньютона
     */
    val w = Polynomial(0.0, 3/2.0, -25/4.0, 35/4.0, -5.0, 1.0)
    println(w)


    val tes = Lagrange(mutableMapOf(0.0 to 1.0, 1/2.0 to 1.7407182808911421, 1.0 to 3.060200266857228, 3/2.0 to 5.3004223435325555, 2.0 to 9.729651346546095))
    println(tes)
    println(tes(0.49))
    println(tes(1.12))
    println(tes(1.76))
    println()
    println(3.0.pow(0.49) * kotlin.math.cosh(0.49 / 5))
    println(3.0.pow(1.12) * kotlin.math.cosh(1.12/5))
    println(3.0.pow(1.76) * kotlin.math.cosh(1.76/5))

    println()
    println(3.0.pow(0.0) * kotlin.math.cosh(0.0))
    println(3.0.pow(1/2.0) * kotlin.math.cosh(0.5/5))
    println(3.0.pow(1.0) * kotlin.math.cosh(1.0/5))
    println(3.0.pow(3/2.0) * kotlin.math.cosh((3/2)/5.0))
    println(3.0.pow(2.0) * kotlin.math.cosh(2.0/5))

    var tes2 = Newton(mutableMapOf(0.0 to 1.0, 1/2.0 to 1.7407182808911421, 1.0 to 3.060200266857228, 3/2.0 to 5.3004223435325555, 2.0 to 9.729651346546095))
    println(tes2)
    println(tes2 + w*0.2874)

    println()
    val wq = Polynomial( 25/16.0, -40*5/16.0, 105/4.0, -20.0, 5.0)
    println(wq(cos(9*kotlin.math.PI/10) + 1))

    println()
    val p1 = Polynomial(-cos(kotlin.math.PI/10)-1, 1.0)
    val p2 = Polynomial(-cos(3*kotlin.math.PI/10)-1, 1.0)
    val p3 = Polynomial(-cos(5*kotlin.math.PI/10)-1, 1.0)
    val p4 = Polynomial(-cos(7*kotlin.math.PI/10)-1, 1.0)
    val p5 = Polynomial(-cos(9*kotlin.math.PI/10)-1, 1.0)

    var tes4 = ((p2*p3*p4*p5/wq(cos(PI/10)+1))!!*9.18645
    + (p1*p3*p4*p5/wq(cos(3*PI/10)+1))!!*6.0132
    + (p1*p2*p4*p5/wq(cos(5*PI/10)+1))!!*3.0602
    + (p1*p2*p3*p5/wq(cos(7*PI/10) + 1))!!*1.57816
    + (p1*p2*p3*p4/wq(cos(9*PI/10) + 1))!!*1.05529)
    var tes5 = Lagrange(mutableMapOf(cos(PI/10)+1 to 9.18645, cos(3*PI/10)+1 to 6.0132, cos(5*PI/10)+1 to 3.0602,
    cos(7*PI/10) + 1 to 1.57816, cos(9*PI/10) + 1 to 1.05529))
    println(tes5)
    println(tes4)
    println(tes4(1.12))
    println(tes5(1.12))
    println(tes(1.12))

    println(p1*p2*p3*p4*p5)
    println(wq(cos(PI / 10) + 1.0))
    println(wq)

    println()
    println((p2*p3*p4*p5/wq(cos(PI/10)+1)))
    println(p1*p3*p4*p5/wq(cos(3*PI/10)+1))
    println((p2*p1*p4*p5/wq(cos(5*PI/10)+1)))
    println((p2*p3*p1*p5/wq(cos(7*PI/10)+1)))
    println((p2*p3*p4*p1/wq(cos(9*PI/10)+1)))

    println()
    println(tes5(0.0))
    println(tes5(1/2.0))
    println(tes5(1.0))
    println(tes5(3/2.0))
    println(tes5(2.0))

    /*
    tes - полином лагранжа
    w - омега
    wq - производная омеги
     */
    var h4 = Polynomial(0.2874, -1.8631916065, 2.651269551, -1.3659311, 0.23396820015)
    var h9 = tes + w*h4
    println(h9(0.49))
    println(h9(1.12))
    println(h9(1.76))
    println(h9)

    val k1 = Polynomial(1.0, kotlin.math.log(E, 3.0))
    val k2 = Polynomial(0.599884, 0.33143084)
    val k3 = Polynomial(0.08712, 0.03396)
    val k4 = Polynomial(-2.7950932, 1.7135289)
    val k5 = Polynomial(-8.71433629629, 3.11237925)

    val c1 = Polynomial(0.0, 1.0)
    val c2 = Polynomial(-1/2.0, 1.0)
    val c3 = Polynomial(-1.0, 1.0)
    val c4 = Polynomial(-3/2.0, 1.0)
    val c5 = Polynomial(-2.0, 1.0)

    val h92 = k1 + c1*c1*k2 + c1*c1*c2*c2*k3 + c1*c1*c2*c2*c3*c3*k4 + c1*c1*c2*c2*c3*c3*c4*c4*k5
    println(h92)
    println(h92(0.49))
    println(h92(1.12))
    println(h92(1.76))

}

