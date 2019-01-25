package movies.view.dto.edit.mapper;

import lombok.AllArgsConstructor;
import movies.infra.repository.CurrentUserRepository;
import movies.model.ReviewInput;
import movies.view.dto.DTOMapper;
import movies.view.dto.edit.ReviewInputDTO;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewInputMapper implements DTOMapper<ReviewInputDTO, ReviewInput> {

    private CurrentUserRepository currentUserRepository;

    @Override
    public ReviewInput map(ReviewInputDTO dto) {
        return ReviewInput.builder()
                .rating(dto.rating)
                .comment(dto.comment)
                .user(currentUserRepository.get())
                .build();
    }

}
