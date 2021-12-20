package com.tsato.mobile.sqldelighttest.data

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun bigDecimalToLong(input: BigDecimal): Long {
        return input.multiply(BigDecimal(1000)).toLong()
    }

    @TypeConverter
    fun longToBigDecimal(input: Long): BigDecimal {
        return BigDecimal.valueOf(input)
            .divide(
                BigDecimal.valueOf(1000),
                BigDecimal.ROUND_HALF_UP
            )
    }

}