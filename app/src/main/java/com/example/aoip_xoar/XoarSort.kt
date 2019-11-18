package com.example.aoip_xoar

class XoarSort(val array : Array<Int>) {

    fun sort () : Array<Int>{
        qsort(0,array.size - 1)
        return array
    }

    private fun qsort (begin : Int, end : Int){
        var l = begin
        var r = end
        val piv  = array[(l + r) / 2]
        while (l <= r)
        {
            while (array[l] < piv) l++
            while (array[r] > piv) r--
            if (l <= r) swap(l++, r--)
            if (begin < r) qsort(begin, r)
            if (end > l) qsort(l, end)
        }
    }

    private fun swap(first : Int, second : Int) {
        val buff = array[first]
        array[first] = array[second]
        array[second] = buff
    }
}