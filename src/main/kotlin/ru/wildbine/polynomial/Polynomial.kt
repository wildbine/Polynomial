package ru.wildbine.polynomial


import ru.wildbine.math.eq
import ru.wildbine.math.gt
import ru.wildbine.math.lt
import ru.wildbine.math.neq
import java.lang.StringBuilder


open class Polynomial(vararg coeffs: Double) {

    protected var _coeff: MutableList<Double>

    val coeff: List<Double>
        get() = _coeff.toList()

    val order: Int
        get() = _coeff.size - 1

    init{
        _coeff = coeffs.toMutableList()
        this.clear()
    }

    constructor() : this(0.0)

    protected fun clear()
    {
        while (_coeff[order] eq 0.0 && order > 0) _coeff.removeLast()
    }

    override fun toString(): String {
        val sb = StringBuilder()
        _coeff.asReversed().forEachIndexed{ index, d ->
            if (d neq 0.0){
                sb.append(if(index > 0 && d gt 0.0) " + " else " ")
                if ((d neq 1.0 && d neq -1.0) || index == order) {
                    sb.append(if (d lt 0.0) "- " + "${-d}" else d)
                }
                else if(d lt -0.0) sb.append("-")
                when{
                    _coeff.size - index - 1 > 1 -> sb.append("x^" + "${_coeff.size - index - 1}")
                    _coeff.size - index - 1 == 1 -> sb.append("x")
                }
            }
        }
        sb.append(if (sb.isEmpty()) " 0.0" else "")
        return sb.toString()
    }

    operator fun plus(other: Polynomial) : Polynomial {
        val (min, max) =
            if (order < other.order) arrayOf(coeff, other.coeff)
            else arrayOf(other.coeff, coeff)
        val res = max.toDoubleArray()
        min.forEachIndexed {i, v -> res[i] += v }
        return Polynomial(*res)
    }

    operator fun unaryMinus() : Polynomial {
        val res = coeff.toDoubleArray()
        for(i in order downTo 0) if(res[i] neq 0.0) res[i] = -res[i]
        return Polynomial(*res)
    }

    operator fun unaryPlus() : Polynomial = Polynomial(*coeff.toDoubleArray())

    operator fun plusAssign(other: Polynomial) : Unit {
        other.coeff.forEachIndexed{i, d ->
            if (i <= order) _coeff[i] += d
            else _coeff.add(d)
        }
    }

    operator fun minus(other: Polynomial) : Polynomial = this + (-other)

    operator fun minusAssign(other: Polynomial) : Unit {this += (-other)}

    operator fun times(scalar: Double) : Polynomial = this* Polynomial(scalar)

    //operator fun times(scalar: Double) : Polynomial = Polynomial(*_coeff.map { it * scalar }.toDoubleArray())

    operator fun times(other: Polynomial) : Polynomial {
        val tmp = DoubleArray(this.order+other.order+1)
        for (k in 0..this.order+other.order)
            for (i in 0..this.order) {
                val tmpc = when{
                    k-i < 0 -> 0.0
                    k-i > other.order -> 0.0
                    else -> other.coeff[k-i]
                }
                tmp[k] += coeff[i] * tmpc
            }
        return Polynomial(*tmp)
    }

    operator fun timesAssign(other: Polynomial) : Unit { this._coeff = (this * other)._coeff }

    operator fun timesAssign(scalar : Double) : Unit { this._coeff = (this * scalar)._coeff  }

    operator fun div(scalar: Double) : Polynomial? = if (scalar eq 0.0) null else this * (1/scalar)

    operator fun divAssign(scalar: Double) { this._coeff = if (scalar neq 0.0) (this / scalar)!!._coeff else this._coeff }

    override operator fun equals(other : Any?): Boolean {
        other?.let { if (other is Polynomial && this.order == other.order) {
            for (i in 0 .. this.order) {
                if (this.coeff[i] neq (other.coeff[i])) return false
            }
            return true
        }
        }
        return false
    }

    override fun hashCode(): Int {
        return _coeff.hashCode()
    }

    operator fun invoke(x : Double) : Double {
        var p = 1.0
        return _coeff.reduce{acc, d -> p*=x; acc + d * p} //перебор начинается с первого элемента (d[1], а acc[0] = d[0])
        //или _coeff.fold
        //reduce и fold возвращают acc
    }

    fun pow(times : Int) : Polynomial {
        var res = this
        repeat(times-1) {
            res = res * this
        }
        return res
    }

}