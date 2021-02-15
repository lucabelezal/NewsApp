package com.lucabelezal.newsapp.db

import androidx.room.TypeConverter
import com.lucabelezal.newsapp.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String = source.name

    @TypeConverter
    fun toSource(name: String): Source = Source(name, name)
}