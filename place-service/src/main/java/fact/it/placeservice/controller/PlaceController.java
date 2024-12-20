package fact.it.placeservice.controller;

import fact.it.placeservice.dto.PlaceRequest;
import fact.it.placeservice.dto.PlaceResponse;
import fact.it.placeservice.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor

public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlace(@RequestBody PlaceRequest placeRequest) {
        placeService.createPlace(placeRequest);
    }

    // GET: Fetch a place by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaceResponse getPlaceById(@PathVariable String id) {
        return placeService.getPlaceById(id);
    }

    // GET: Fetch all places
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaceResponse> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    // GET: Fetch places by type
    @GetMapping("/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaceResponse> getPlacesByType(@PathVariable String type) {
        return placeService.getPlacesByType(type);
    }

    @PutMapping("/{id}")
    public void updatePlace(@PathVariable String id, @RequestBody PlaceRequest placeRequest) {
        placeService.updatePlace(id, placeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable String id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
}