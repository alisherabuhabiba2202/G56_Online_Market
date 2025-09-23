package uz.pdp.g56_online_market.services;

import jakarta.persistence.EntityManager;
import uz.pdp.g56_online_market.config.JpaConfig;
import uz.pdp.g56_online_market.daos.NewsDAO;
import uz.pdp.g56_online_market.dtos.NewsDTO;
import uz.pdp.g56_online_market.entities.News;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class NewsServices {
    private NewsDAO newsDAO = new NewsDAO();
    public List<NewsDTO> getNewsByPageable(int page, int size) {
        List<News> newsDAOAllNews = newsDAO.getAllNews(page, size);
        List<NewsDTO> dtos = new ArrayList<NewsDTO>();
        for (News news :newsDAOAllNews) {
            dtos.add(new NewsDTO().builder()
                    .id(news.getId())
                    .name(news.getTitle())
                    .description(news.getDescription())
                    .filePath(news.getFilePath())
                    .build());
        }
        return dtos;
    }
}
