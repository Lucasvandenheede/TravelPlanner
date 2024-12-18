package fact.it.weatherservice.service;

import fact.it.weatherservice.dto.WeatherRequest;
import fact.it.weatherservice.dto.WeatherResponse;
import fact.it.weatherservice.model.Weather;
import fact.it.weatherservice.repository.WeatherRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    // Create new weather
    public void createWeather(WeatherRequest weatherRequest) {
        Weather weather = Weather.builder()
                .city(weatherRequest.getCity())
                .country(weatherRequest.getCountry())
                .weatherDescription(weatherRequest.getWeatherDescription())
                .temperature(weatherRequest.getTemperature())
                .precipitationChance(weatherRequest.getPrecipitationChance())
                .humidity(weatherRequest.getHumidity())
                .windSpeed(weatherRequest.getWindSpeed())
                .timestamp(weatherRequest.getTimestamp())
                .build();

        weatherRepository.save(weather);
    }

    // Get all weather data
    public List<WeatherResponse> getAllWeather() {
        List<Weather> weathers = weatherRepository.findAll();
        return weathers.stream().map(this::mapToWeatherResponse).toList();
    }

    // Get weather by ID
    public WeatherResponse getWeatherById(Long id) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weather not found with id: " + id));
        return mapToWeatherResponse(weather);
    }

    // Update weather data by ID
    public void updateWeather(Long id, WeatherRequest weatherRequest) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weather not found with id: " + id));

        weather.setCity(weatherRequest.getCity());
        weather.setCountry(weatherRequest.getCountry());
        weather.setWeatherDescription(weatherRequest.getWeatherDescription());
        weather.setTemperature(weatherRequest.getTemperature());
        weather.setPrecipitationChance(weatherRequest.getPrecipitationChance());
        weather.setHumidity(weatherRequest.getHumidity());
        weather.setWindSpeed(weatherRequest.getWindSpeed());
        weather.setTimestamp(weatherRequest.getTimestamp());

        weatherRepository.save(weather);
    }

    // Delete weather data by ID
    public void deleteWeather(Long id) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weather not found with id: " + id));
        weatherRepository.delete(weather);
    }

    // Helper method to map Weather entity to WeatherResponse
    private WeatherResponse mapToWeatherResponse(Weather weather) {
        return WeatherResponse.builder()
                .id(weather.getId())
                .city(weather.getCity())
                .country(weather.getCountry())
                .weatherDescription(weather.getWeatherDescription())
                .temperature(weather.getTemperature())
                .precipitationChance(weather.getPrecipitationChance())
                .humidity(weather.getHumidity())
                .windSpeed(weather.getWindSpeed())
                .timestamp(weather.getTimestamp())
                .build();
    }

    // Method to load data into the database at startup
    @PostConstruct
    public void seedData() {
        if (weatherRepository.count() == 0) {
            Weather weather1 = new Weather();
            weather1.setCity("Paris");
            weather1.setCountry("France");
            weather1.setWeatherDescription("Mostly cloudy");
            weather1.setTemperature(4.0);
            weather1.setPrecipitationChance(10.0);
            weather1.setHumidity(90.0);
            weather1.setWindSpeed(13.0);
            weather1.setTimestamp(LocalDateTime.parse("2024-12-14T15:30:25"));

            weatherRepository.save(weather1);
        }
    }
}
