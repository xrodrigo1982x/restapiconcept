package movies.view.controller;

import lombok.AllArgsConstructor;
import movies.infra.repository.projection.MovieProjection;
import movies.model.AddReviewToMovie;
import movies.model.Movie;
import movies.model.ReviewInput;
import movies.model.service.CreateService;
import movies.model.service.ListAllService;
import movies.model.service.impl.UpdateService;
import movies.view.dto.DTOMapper;
import movies.view.dto.EntityMapper;
import movies.view.dto.GenericEntityMapper;
import movies.view.dto.MapToDTO;
import movies.view.dto.edit.MovieEditDTO;
import movies.view.dto.edit.ReviewInputDTO;
import movies.view.dto.list.MovieListItemDTO;
import movies.view.exception.DuplicateReviewException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static movies.view.exception.InvalidDTOException.verifyValidationErrors;
import static movies.view.exception.NotFoundException.verifyFound;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("movies")
@AllArgsConstructor
public class MovieController {

    private CreateService<Movie> createService;
    private ListAllService<MovieProjection> listAllService;
    private UpdateService updateService;
    private GenericEntityMapper entityMapper;
    private AddReviewToMovie addReviewToMovie;
    private DTOMapper<ReviewInputDTO, ReviewInput> reviewInputMapper;

    @GetMapping("{movie}")
    @MapToDTO(MovieEditDTO.class)
    public Movie findOne(@PathVariable Movie movie) {
        verifyFound(movie);
        return movie;
    }

    @GetMapping("")
    @MapToDTO(MovieListItemDTO.class)
    public List<MovieProjection> listAll() {
        return listAllService.all();
    }

    @PostMapping("")
    @MapToDTO(MovieEditDTO.class)
    public Movie create(@RequestBody @Valid MovieEditDTO dto, BindingResult result) {
        verifyValidationErrors(result);
        Movie movie = entityMapper.getMapper(new Movie(), dto).get();
        movie = createService.create(movie);
        return movie;
    }

    @PutMapping("{movie}")
    @MapToDTO(MovieEditDTO.class)
    public Movie update(@PathVariable Movie movie, @RequestBody @Valid MovieEditDTO editDTO, BindingResult result) {
        verifyFound(movie);
        verifyValidationErrors(result);
        movie = updateService.update(entityMapper.getMapper(movie, editDTO));
        return movie;
    }

    @PostMapping("{movie}/review")
    @ResponseStatus(OK)
    public void addReview(@PathVariable Movie movie, @RequestBody ReviewInputDTO dto, BindingResult result) {
        verifyFound(movie);
        verifyValidationErrors(result);
        ReviewInput input = reviewInputMapper.map(dto);
        try {
            addReviewToMovie.apply(movie, input);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateReviewException(input.user, movie);
        }
    }

}
