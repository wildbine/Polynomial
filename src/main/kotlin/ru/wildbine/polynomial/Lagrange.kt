package ru.wildbine.polynomial

import ru.wildbine.math.eq
import ru.wildbine.math.neq
import kotlin.math.pow

open class Lagrange(val _dotFoo : MutableMap<Double, Double>) : Polynomial() {

    init {
        val res = Polynomial()
        _dotFoo.forEach { (x,y) -> res += (l(x) * y) }
        _coeff = res.coeff.toMutableList()
        while (_coeff[order] eq 0.0 && order > 0) _coeff.removeLast()
    }

    private fun l(x: Double) : Polynomial {
        val res = Polynomial(1.0)
        _dotFoo.forEach { (_x, _y) ->  if(_x neq x) res *= (Polynomial(-_x, 1.0) /(x-_x))!!}
        return res
    }

    operator fun get(d: Double): Double {
        var res = 0.0
        _coeff.forEachIndexed { index, c -> res += c*d.pow(index)}
        return res
    }
}