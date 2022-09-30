package vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.data.datasource.remote.mappers

import vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.data.datasource.remote.dto.TempDTO
import vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.domain.entities.Temperature

fun TempDTO.toEntityObject() = Temperature(
    day = this.day,
    min = this.min,
    max = this.max,
    night = this.night,
    eve = this.eve,
    morn = this.morn
)