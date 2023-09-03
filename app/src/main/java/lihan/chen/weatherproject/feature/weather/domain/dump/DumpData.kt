package lihan.chen.weatherproject.feature.weather.domain.dump

import lihan.chen.weatherproject.feature.weather.data.remote.model.Location
import lihan.chen.weatherproject.feature.weather.data.remote.model.Parameter
import lihan.chen.weatherproject.feature.weather.data.remote.model.Records
import lihan.chen.weatherproject.feature.weather.data.remote.model.Time
import lihan.chen.weatherproject.feature.weather.data.remote.model.WeatherElement

object DumpData {
    val weatherDump = Records(
        datasetDescription = "三十六小時天氣預報",
        location = listOf(
            Location("台北市",
                weatherElement = listOf(
                    WeatherElement(
                        elementName = "Wx",
                        time = listOf(
                            Time(
                                startTime = "2023-08-18 18:00:00",
                                endTime = "2023-08-19 06:00:00",
                                parameter = Parameter(
                                    parameterName = "多雲時陰短暫陣雨或雷雨",
                                    parameterValue = "16",
                                    parameterUnit = ""
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 06:00:00",
                                endTime = "2023-08-19 18:00:00",
                                parameter = Parameter(
                                    parameterName = "陰時多雲短暫陣雨或雷雨",
                                    parameterValue = "17",
                                    parameterUnit = ""
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 18:00:00",
                                endTime = "2023-08-20 06:00:00",
                                parameter = Parameter(
                                    parameterName = "多雲時晴",
                                    parameterValue = "3",
                                    parameterUnit = ""
                                )
                            )
                        )
                    ),
                    WeatherElement(
                        elementName = "PoP",
                        time = listOf(
                            Time(
                                startTime = "2023-08-18 18:00:00",
                                endTime = "2023-08-19 06:00:00",
                                parameter = Parameter(
                                    parameterName = "20",
                                    parameterUnit = "%"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 06:00:00",
                                endTime = "2023-08-19 18:00:00",
                                parameter = Parameter(
                                    parameterName = "30",
                                    parameterUnit = "%"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 18:00:00",
                                endTime = "2023-08-20 06:00:00",
                                parameter = Parameter(
                                    parameterName = "90",
                                    parameterUnit = "%"
                                )
                            )
                        )
                    ),
                    WeatherElement(
                        elementName = "MinT",
                        time = listOf(
                            Time(
                                startTime = "2023-08-18 18:00:00",
                                endTime = "2023-08-19 06:00:00",
                                parameter = Parameter(
                                    parameterName = "27",
                                    parameterUnit = "C"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 06:00:00",
                                endTime = "2023-08-19 18:00:00",
                                parameter = Parameter(
                                    parameterName = "25",
                                    parameterUnit = "C"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 18:00:00",
                                endTime = "2023-08-20 06:00:00",
                                parameter = Parameter(
                                    parameterName = "22",
                                    parameterUnit = "C"
                                )
                            )
                        )
                    ),
                    WeatherElement(
                        elementName = "CI",
                        time = listOf(
                            Time(
                                startTime = "2023-08-18 18:00:00",
                                endTime = "2023-08-19 06:00:00",
                                parameter = Parameter(
                                    parameterName = "舒適至悶熱"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 06:00:00",
                                endTime = "2023-08-19 18:00:00",
                                parameter = Parameter(
                                    parameterName = "舒適"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 18:00:00",
                                endTime = "2023-08-20 06:00:00",
                                parameter = Parameter(
                                    parameterName = "舒適:("
                                )
                            )
                        )
                    ),
                    WeatherElement(
                        elementName = "MaxT",
                        time = listOf(
                            Time(
                                startTime = "2023-08-18 18:00:00",
                                endTime = "2023-08-19 06:00:00",
                                parameter = Parameter(
                                    parameterName = "28",
                                    parameterUnit = "C"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 06:00:00",
                                endTime = "2023-08-19 18:00:00",
                                parameter = Parameter(
                                    parameterName = "30",
                                    parameterUnit = "C"
                                )
                            ),
                            Time(
                                startTime = "2023-08-19 18:00:00",
                                endTime = "2023-08-20 06:00:00",
                                parameter = Parameter(
                                    parameterName = "37",
                                    parameterUnit = "C"
                                )
                            )
                        )
                    )

                )
            )
        )
    )
}