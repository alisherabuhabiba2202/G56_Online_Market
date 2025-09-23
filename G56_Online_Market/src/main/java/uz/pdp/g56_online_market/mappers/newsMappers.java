package uz.pdp.g56_online_market.mappers;

import uz.pdp.g56_online_market.dtos.NewsDTO;

public class newsMappers {
    public static NewsDTO mapNewsDTO(NewsDTO newsDTO) {
        return new NewsDTO().builder()
                .id(newsDTO.getId())
                .name(newsDTO.getName())
                .description(newsDTO.getDescription())
                .build();
    }
}
