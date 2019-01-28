package movies.view.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movies.infra.repository.projection.GenreProjection;
import movies.model.Genre;
import movies.model.service.CreateService;
import movies.model.service.ListAllService;
import movies.model.service.impl.UpdateService;
import movies.view.dto.EntityMapper;
import movies.view.dto.GenericEntityMapper;
import movies.view.dto.MapToDTO;
import movies.view.dto.edit.GenreEditDTO;
import movies.view.dto.list.GenreListItemDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Supplier;

import static movies.view.exception.InvalidDTOException.verifyValidationErrors;
import static movies.view.exception.NotFoundException.verifyFound;

@RestController
@AllArgsConstructor
@RequestMapping("genres")
@Slf4j
public class GenreController {

    private ListAllService<GenreProjection> listAllService;
    private CreateService<Genre> createService;
    private UpdateService updateService;
    private GenericEntityMapper entityMapper;

    @GetMapping("{genre}")
    @MapToDTO(GenreEditDTO.class)
    public Genre findOne(@PathVariable Genre genre) {
        verifyFound(genre);
        return genre;
    }

    @GetMapping("")
    @MapToDTO(GenreListItemDTO.class)
    public List<GenreProjection> listAll() {
        return listAllService.all();
    }

    @PostMapping("")
    @MapToDTO(GenreEditDTO.class)
    public Genre create(@RequestBody @Valid GenreEditDTO editDTO, BindingResult result) {
        verifyValidationErrors(result);
        Genre genre = entityMapper.getMapper(new Genre(), editDTO).get();
        genre = createService.create(genre);
        return genre;
    }

    @PutMapping("{genre}")
    @MapToDTO(GenreEditDTO.class)
    public Genre update(@PathVariable Genre genre, @RequestBody @Valid GenreEditDTO editDTO, BindingResult result) {
        verifyFound(genre);
        verifyValidationErrors(result);
        genre = updateService.update(entityMapper.getMapper(genre, editDTO));
        return genre;
    }

}
