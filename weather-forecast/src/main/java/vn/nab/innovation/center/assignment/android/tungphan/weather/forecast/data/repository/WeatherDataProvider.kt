package vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import vn.nab.innovation.center.assignment.android.tungphan.core.model.CallResult
import vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.data.datasource.local.WeatherLocalDataSource
import vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.data.datasource.remote.WeatherRemoteDataSource
import vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.domain.entity.DEFAULT_DAILY_WEATHER
import vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.domain.entity.WeatherData
import vn.nab.innovation.center.assignment.android.tungphan.weather.forecast.domain.repository.WeatherDataRepository

class WeatherDataProvider(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherDataRepository {

    private val allWeatherData = MutableStateFlow(DEFAULT_DAILY_WEATHER)

    override suspend fun getDailyWeatherData(
        location: String,
        cnt: String
    ): CallResult<WeatherData> {
        val result = weatherRemoteDataSource.getDailyWeatherData(
            location = location,
            cnt = cnt
        )
        if (result.isSuccess) {
            result.getOrNull()?.let {
                this@WeatherDataProvider.allWeatherData.value = it
            }
        }
        return result
    }

    override suspend fun getThreeHoursStepWeatherData(
        location: String
    ): CallResult<WeatherData> {
        val result = weatherRemoteDataSource.getThreeHoursStepWeatherData(location)
        if (result.isSuccess) {
            result.getOrNull()?.let {
                this@WeatherDataProvider.allWeatherData.value = it
            }
        }
        return result
    }
}
