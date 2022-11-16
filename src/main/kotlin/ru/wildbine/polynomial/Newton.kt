package ru.wildbine.polynomial

import ru.wildbine.math.eq

class Newton(dotFoo: MutableMap<Double, Double>) : Polynomial() {
    private val _poly: Polynomial
    fun getPolynomial(): Polynomial = this._poly

    private val _newMap: MutableMap<Double, Double>
    init {
        val result = Polynomial()
        this._newMap = dotFoo.toMutableMap()

        var j = 0
        dotFoo.forEach {_ ->
            result += omegaPoly(j) * f(j + 1)
            ++j
        }
        _poly = result
        _coeff = result.coeff as MutableList<Double>
        while (_coeff[order] eq 0.0 && order > 0) _coeff.removeLast()
    }

    constructor() : this(mutableMapOf(0.0 to 0.0))

    private fun omegaPoly(j: Int): Polynomial {
        val result = Polynomial(1.0)
        val dots = _newMap.keys.take(j)

        var i = 0
        while (i < j) {
            result *= Polynomial(-dots[i], 1.0)
            i++
        }
        return result
    }

    private fun f(n: Int): Double {

        val dots = _newMap.keys.take(n)
        val values = _newMap.values.take(n)

        if (n == 1)
            return values.first()

        var i = 0
        var result = 0.0

        val product = fun(): Double {
            var tmp = 1.0
            var k = 0
            while (k < n) {
                if (k != i)
                    tmp *= dots[i] - dots[k]
                ++k
            }
            return tmp
        }
        
        while (i < n) {
            result += values[i] / product()
            ++i
        }
        return result
    }

    fun addNode(key: Double, value: Double) {
        this._newMap[key] = value
        val n = _newMap.size

        this._poly += omegaPoly(n - 1) *
                f(n)
        _coeff = _poly.coeff as MutableList<Double>
        this.clear()
    }

    fun addNodes(map : MutableMap<Double, Double>){
        map.forEach{ (key, value) -> addNode(key, value)}
    }
}