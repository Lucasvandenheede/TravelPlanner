package fact.it.weatherservice.controller;

import fact.it.weatherservice.dto.WeatherRequest;
import fact.it.weatherservice.dto.WeatherResponse;
import fact.it.weatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    // POST: Create new weather data
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWeather(@RequestBody WeatherRequest weatherRequest) {
        weatherService.createWeather(weatherRequest);
    }

    // GET: Fetch weather by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WeatherResponse getWeatherById(@PathVariable Long id) {
        return weatherService.getWeatherById(id);

    }

    // GET: Fetch all weather data
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<WeatherResponse> getAllWeather() {
        return weatherService.getAllWeather();
    }

    // PUT: Update weather data by ID
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateWeather(@PathVariable Long id, @RequestBody WeatherRequest weatherRequest) {
        weatherService.updateWeather(id, weatherRequest);
    }

    // DELETE: Delete weather data by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWeather(@PathVariable Long id) {
        weatherService.deleteWeather(id);
    }
}